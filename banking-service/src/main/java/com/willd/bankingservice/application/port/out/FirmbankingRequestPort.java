package com.willd.bankingservice.application.port.out;

import com.willd.bankingservice.domain.FirmbankingRequestJpaEntity;
import com.willd.domain.bank.enums.FirmbankingRequestStatus;

public interface FirmbankingRequestPort {
    FirmbankingRequestJpaEntity createFirmbanking(
            String fromBankName,
            String fromBankAccountNumber,
            String toBankName,
            String toBankAccountNumber,
            Integer moneyAmount,
            FirmbankingRequestStatus firmbankingStatus
    );

    FirmbankingRequestJpaEntity modifyFirmbanking(FirmbankingRequestJpaEntity entity);
}
