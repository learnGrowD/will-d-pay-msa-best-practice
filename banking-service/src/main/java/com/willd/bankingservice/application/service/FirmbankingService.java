package com.willd.bankingservice.application.service;

import com.willd.bankingservice.adapter.out.service.firmbanking.RequestFirmbanking;
import com.willd.bankingservice.adapter.out.service.firmbanking.RequestFirmbankingStatus;
import com.willd.bankingservice.domain.FirmbankingRequestJpaEntity;
import com.willd.bankingservice.adapter.out.persistence.FirmbankingRequestMapper;
import com.willd.bankingservice.application.port.in.FirmbankingUseCase;
import com.willd.bankingservice.application.port.in.RequestFirmbankingCommand;
import com.willd.bankingservice.application.port.out.BankAccountPort;
import com.willd.bankingservice.application.port.out.FirmbankingRequestPort;
import com.willd.domain.bank.FirmbankingRequest;
import com.willd.common.UserCase;
import com.willd.domain.bank.enums.FirmbankingRequestStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@UserCase
@RequiredArgsConstructor
@Transactional
public class FirmbankingService implements FirmbankingUseCase {

    private final FirmbankingRequestPort firmbankingRequestPort;
    private final BankAccountPort bankAccountPort;
    private final FirmbankingRequestMapper firmbankingRequestMapper;

    @Override
    public FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command) {
        // 생성
        FirmbankingRequestJpaEntity firmbankingRequestJpaEntity = firmbankingRequestPort.createFirmbanking(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount(),
                FirmbankingRequestStatus.REQUEST
        );

        // 요청
        RequestFirmbanking externalFirmBankingResponse = bankAccountPort.requestFirmbanking(command.getFromBankName(), command.getFromBankAccountNumber(), command.getToBankName(), command.getToBankAccountNumber());

        //Transaction UUID
        String uuidString = UUID.randomUUID().toString();
        firmbankingRequestJpaEntity.assignUUID(uuidString);

        //Update
        if (externalFirmBankingResponse.getResultCode().equals(RequestFirmbankingStatus.SUCCESS))
            firmbankingRequestJpaEntity.assignFirmbankingStatus(FirmbankingRequestStatus.SUCCESS);
        else
            firmbankingRequestJpaEntity.assignFirmbankingStatus(FirmbankingRequestStatus.FAIL);

        return firmbankingRequestMapper.mapToDomainEntity(firmbankingRequestPort.modifyFirmbanking(firmbankingRequestJpaEntity));
    }
}
