package com.willd.bankingservice.adapter.out.persistence;

import com.willd.bankingservice.domain.FirmbankingRequestJpaEntity;
import com.willd.domain.bank.FirmbankingRequest;
import org.springframework.stereotype.Component;

@Component
public class FirmbankingRequestMapper {

    public FirmbankingRequest mapToDomainEntity(FirmbankingRequestJpaEntity entity) {
        return FirmbankingRequest.generate(
                entity.getFirmbankingRequestId(),
                entity.getFromBankName(),
                entity.getFromBankAccountNumber(),
                entity.getToBankName(),
                entity.getToBankAccountNumber(),
                entity.getMoneyAmount(),
                entity.getFirmbankingStatus(),
                entity.getUuid()
        );
    }
}
