package com.willd.bankingservice.application.service;

import com.willd.bankingservice.adapter.out.service.firmbanking.Firmbanking;
import com.willd.bankingservice.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.willd.bankingservice.adapter.out.persistence.RegisteredBankAccountMapper;
import com.willd.bankingservice.application.port.in.GetBankAccountByBankAccountNumberCommand;
import com.willd.bankingservice.application.port.in.GetBankAccountByMembershipIdCommand;
import com.willd.bankingservice.application.port.in.RegisterBankAccountCommand;
import com.willd.bankingservice.application.port.in.RegisteredUseCase;
import com.willd.bankingservice.application.port.out.BankAccountPort;
import com.willd.bankingservice.application.port.out.MembershipPort;
import com.willd.bankingservice.application.port.out.RegisteredBankAccountPort;
import com.willd.domain.bank.RegisteredBankAccount;
import com.willd.common.UserCase;
import com.willd.domain.membership.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UserCase
@RequiredArgsConstructor
@Transactional
public class RegisteredBankAccountService implements RegisteredUseCase {
    private final RegisteredBankAccountPort registeredBankAccountPort;
    private final BankAccountPort bankAccountPort;
    private final RegisteredBankAccountMapper registeredBankAccountMapper;
    private final MembershipPort membershipPort;

    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
        Membership membership = membershipPort.getMemberStatus(command.getMembershipId());
        if (!membership.getIsValid()) {
            return null;
        }
        Firmbanking bankAccountResponse = bankAccountPort.getBankAccount(command.getBankName(), command.getBankAccountNumber());
        Boolean isValid = bankAccountResponse.getIsValid();

        if (isValid) {
            RegisteredBankAccountJpaEntity entity = registeredBankAccountPort.createBankAccount(
                    command.getMembershipId(),
                    command.getBankName(),
                    command.getBankAccountNumber(),
                    true
            );
            return registeredBankAccountMapper.mapToDomainEntity(entity);
        }
        else {
            return null;
        }
    }

    @Override
    public RegisteredBankAccount getRegisterBankAccount(GetBankAccountByMembershipIdCommand command) {
        RegisteredBankAccountJpaEntity entity = registeredBankAccountPort.getRegisteredBankAccount(command.getMembershipId());
        return registeredBankAccountMapper.mapToDomainEntity(entity);
    }

    @Override
    public RegisteredBankAccount getRegisteredBankAccount(GetBankAccountByBankAccountNumberCommand command) {
        RegisteredBankAccountJpaEntity entity = registeredBankAccountPort.getRegisteredBankAccount(command.getBankAccountNumber());
        return registeredBankAccountMapper.mapToDomainEntity(entity);
    }
}
