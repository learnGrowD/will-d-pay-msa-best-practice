package com.willd.remittance.application.service;

import com.willd.common.UserCase;
import com.willd.domain.remittance.enums.RemittanceRequestStatus;
import com.willd.remittance.adapter.out.persistence.RemittanceRequestMapper;
import com.willd.remittance.application.port.in.RemittanceRequestUseCase;
import com.willd.remittance.application.port.in.RequestRemittanceCommand;
import com.willd.remittance.application.port.out.BankPort;
import com.willd.remittance.application.port.out.MembershipPort;
import com.willd.remittance.application.port.out.MoneyPort;
import com.willd.remittance.application.port.out.RemittanceRequestPort;
import com.willd.domain.remittance.RemittanceRequest;
import com.willd.domain.remittance.enums.RemittanceRequestType;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@UserCase
@Transactional
@RequiredArgsConstructor
public class RemittanceRequestService implements RemittanceRequestUseCase {
    private final RemittanceRequestMapper remittanceRequestMapper;
    private final RemittanceRequestPort remittancePort;
    private final MembershipPort membershipPort;
    private final BankPort bankPort;
    private final MoneyPort moneyPort;

    @Override
    public Mono<RemittanceRequest> requestRemittance(RequestRemittanceCommand command) {
        UUID transactionUUID = UUID.randomUUID();
        return remittancePort.create(
                        command.getFromMembershipId(),
                        command.getToMembershipId(),
                        command.getAmount(),
                        command.getType(),
                        RemittanceRequestStatus.REQUEST,
                        transactionUUID.toString()
                )
                .flatMap(entity ->
                        membershipPort.checkMembershipValidity(command.getFromMembershipId())
                                .flatMap(it -> moneyPort.checkBalanceAndRechargeIfInsufficientFunds(command.getFromMembershipId(), command.getAmount()))
                                .flatMap(it -> membershipPort.checkMembershipValidity(command.getToMembershipId()))
                                .flatMap(it -> {
                                    if (command.getType().equals(RemittanceRequestType.MEMBERSHIP)) {
                                        return moneyPort.remittanceToInternalCustomer(command.getFromMembershipId(), command.getToMembershipId(), command.getAmount());
                                    } else if (command.getType().equals(RemittanceRequestType.BANK)) {
                                        return bankPort.remittanceToExternalCustomer(command.getFromMembershipId(), command.getToMembershipId(), command.getAmount());
                                    } else {
                                        return Mono.empty();
                                    }
                                })
                                .switchIfEmpty(Mono.error(new RuntimeException("")))
                                .onErrorResume(throwable -> {
                                    entity.setRemittanceRequestStatus(RemittanceRequestStatus.FAIL.name());
                                    remittancePort.modify(entity).map(remittanceRequestMapper::mapToDomainEntity);
                                    return Mono.error(throwable);
                                })
                                .thenReturn(entity)
                )
                .flatMap(entity -> {
                    entity.setRemittanceRequestStatus(RemittanceRequestStatus.SUCCESS.name());
                    return remittancePort.modify(entity).map(remittanceRequestMapper::mapToDomainEntity);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("")));
    }
}
