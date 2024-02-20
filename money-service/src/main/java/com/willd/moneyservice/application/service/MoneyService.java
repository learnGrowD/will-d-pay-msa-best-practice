package com.willd.moneyservice.application.service;

import com.willd.common.UserCase;
import com.willd.domain.bank.FirmbankingRequest;
import com.willd.domain.bank.RegisteredBankAccount;
import com.willd.domain.bank.enums.FirmbankingRequestStatus;
import com.willd.domain.membership.Membership;
import com.willd.domain.money.enums.MoneyChargingRequestStatus;
import com.willd.moneyservice.adapter.out.persistence.MemberMoneyJpaEntity;
import com.willd.moneyservice.adapter.out.persistence.MoneyChargingRequestJpaEntity;
import com.willd.moneyservice.adapter.out.persistence.MoneyMapper;
import com.willd.moneyservice.application.port.in.command.DecreaseMemberMoneyCommand;
import com.willd.moneyservice.application.port.in.command.IncreaseMemberMoneyCommand;
import com.willd.moneyservice.application.port.in.MoneyUserCase;
import com.willd.moneyservice.application.port.out.BankingPort;
import com.willd.moneyservice.application.port.out.MemberMoneyPort;
import com.willd.moneyservice.application.port.out.MembershipPort;
import com.willd.moneyservice.application.port.out.MoneyChargingRequestPort;
import com.willd.domain.money.MemberMoney;
import com.willd.domain.money.enums.ChangingType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

@UserCase
@Transactional
public class MoneyService implements MoneyUserCase {

    private final BankingPort bankingPort;
    private final MembershipPort membershipPort;
    private final MoneyChargingRequestPort moneyChargingRequestPort;
    private final MemberMoneyPort memberMoneyPort;
    private final MoneyMapper moneyMapper;
    private final String myCorpBankAccountNumber;

    public MoneyService(
            BankingPort bankingPort,
            MembershipPort membershipPort,
            MoneyChargingRequestPort moneyChargingRequestPort,
            MemberMoneyPort memberMoneyPort,
            MoneyMapper moneyMapper,
            @Value("${my.corp.bank.account.number}") String myCorpBankAccountNumber
    ) {
        this.bankingPort = bankingPort;
        this.membershipPort = membershipPort;
        this.moneyChargingRequestPort = moneyChargingRequestPort;
        this.memberMoneyPort = memberMoneyPort;
        this.moneyMapper = moneyMapper;
        this.myCorpBankAccountNumber = myCorpBankAccountNumber;
    }

    @Override
    public MemberMoney getMoneyInfo(Long membershipId) {
        MemberMoneyJpaEntity entity = memberMoneyPort.getMemberMoney(membershipId);
        return moneyMapper.mapToDomainEntity(entity);
    }

    @Override
    public MemberMoney increaseMemberMoney(IncreaseMemberMoneyCommand command) {
        MoneyChargingRequestJpaEntity moneyChargingRequestJpaEntity = moneyChargingRequestPort.createMoneyChargingRequest(
                command.getTargetMembershipId(),
                ChangingType.INCREASE,
                command.getIncreaseMoneyAmount(),
                MoneyChargingRequestStatus.REQUEST
        );

        try {
            // membership 유효한지 check
            Membership membership = membershipPort.getMembership(command.getTargetMembershipId());
            if (!membership.getIsValid()) throw new RuntimeException("a");

            RegisteredBankAccount targetMemberRegisteredBankAccount = bankingPort.getBankingStatus(command.getTargetMembershipId());
            if (!targetMemberRegisteredBankAccount.getLinkedStatusIsValid()) throw new RuntimeException("b");

            RegisteredBankAccount myCorpRegisteredBankAccount = bankingPort.getBankingStatus(myCorpBankAccountNumber);
            if (!myCorpRegisteredBankAccount.getLinkedStatusIsValid()) throw new RuntimeException("c");

            // 가장 큰 비지니스 효용 -> 비지니스 로직의 핵심을 찾아야 한다.
            FirmbankingRequest firmbankingResult = bankingPort.requestFirmbanking(
                    targetMemberRegisteredBankAccount.getBankName(),
                    targetMemberRegisteredBankAccount.getBankAccountNumber(),
                    myCorpRegisteredBankAccount.getBankName(),
                    myCorpRegisteredBankAccount.getBankAccountNumber(),
                    command.getIncreaseMoneyAmount()
            );
            // *** targetmembershipId의 은행계좌에서 -> 우리 법인 계좌에 firmbanking

            if (firmbankingResult.getFirmbankingStatus().equals(FirmbankingRequestStatus.SUCCESS)) {
                moneyChargingRequestJpaEntity.setChangingMoneyStatus(MoneyChargingRequestStatus.SUCCESS);
                moneyChargingRequestPort.modifyMoneyChargingRequest(moneyChargingRequestJpaEntity);
                MemberMoneyJpaEntity memberMoneyJpaEntity = memberMoneyPort.increaseMemberMoney(
                        command.getTargetMembershipId(),
                        command.getIncreaseMoneyAmount()
                );
                return moneyMapper.mapToDomainEntity(memberMoneyJpaEntity);
            }
            throw new RuntimeException("");
        } catch (Exception e) {
            moneyChargingRequestJpaEntity.setChangingMoneyStatus(MoneyChargingRequestStatus.FAIL);
            moneyChargingRequestPort.modifyMoneyChargingRequest(moneyChargingRequestJpaEntity);
            return null;
        }
    }

    public MemberMoney increaseMemberMoneyByAsync() {
        return null;
    }

    @Override
    public MemberMoney decreaseMemberMoney(DecreaseMemberMoneyCommand command) {
        MoneyChargingRequestJpaEntity moneyChargingRequestJpaEntity = moneyChargingRequestPort.createMoneyChargingRequest(
                command.getTargetMembershipId(),
                ChangingType.DECREASE,
                command.getDecreaseMoneyAmount(),
                MoneyChargingRequestStatus.REQUEST
        );

        try {
            // membership 유효한지 check
            Membership membership = membershipPort.getMembership(command.getTargetMembershipId());
            if (!membership.getIsValid()) throw new RuntimeException("a");

            RegisteredBankAccount targetMemberRegisteredBankAccount = bankingPort.getBankingStatus(command.getTargetMembershipId());
            if (!targetMemberRegisteredBankAccount.getLinkedStatusIsValid()) throw new RuntimeException("b");

            RegisteredBankAccount myCorpRegisteredBankAccount = bankingPort.getBankingStatus(myCorpBankAccountNumber);
            if (!myCorpRegisteredBankAccount.getLinkedStatusIsValid()) throw new RuntimeException("c");

            // *** targetmembershipId의 은행계좌에서 -> 우리 법인 계좌에 firmbanking
            MemberMoneyJpaEntity memberMoneyJpaEntity = memberMoneyPort.decreaseMemberMoney(
                    command.getTargetMembershipId(),
                    command.getDecreaseMoneyAmount()
            );

            // 가장 큰 비지니스 효용 -> 비지니스 로직의 핵심을 찾아야 한다.
            FirmbankingRequest firmbankingResult = bankingPort.requestFirmbanking(
                    myCorpRegisteredBankAccount.getBankName(),
                    myCorpRegisteredBankAccount.getBankAccountNumber(),
                    targetMemberRegisteredBankAccount.getBankName(),
                    targetMemberRegisteredBankAccount.getBankAccountNumber(),
                    command.getDecreaseMoneyAmount()
            );

            if (firmbankingResult.getFirmbankingStatus().equals(FirmbankingRequestStatus.SUCCESS)) {
                moneyChargingRequestJpaEntity.setChangingMoneyStatus(MoneyChargingRequestStatus.SUCCESS);
                moneyChargingRequestPort.modifyMoneyChargingRequest(moneyChargingRequestJpaEntity);
                return moneyMapper.mapToDomainEntity(memberMoneyJpaEntity);
            }
            throw new RuntimeException("");

        } catch (Exception e) {
            moneyChargingRequestJpaEntity.setChangingMoneyStatus(MoneyChargingRequestStatus.FAIL);
            moneyChargingRequestPort.modifyMoneyChargingRequest(moneyChargingRequestJpaEntity);
            return null;
        }
    }
}
