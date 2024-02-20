package com.willd.bankingservice.adapter.out.persistence;

import com.willd.bankingservice.application.port.out.FirmbankingRequestPort;
import com.willd.common.PersistenceAdapter;
import com.willd.domain.bank.enums.FirmbankingRequestStatus;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class FirmbankingRequestPersistenceAdapter implements FirmbankingRequestPort {
    private final SpringDataFirmbankingRequestRepository firmbankingRequestRepository;

    @Override
    public FirmbankingRequestJpaEntity createFirmbanking(
            String fromBankName,
            String fromBankAccountNumber,
            String toBankName,
            String toBankAccountNumber,
            Integer moneyAmount,
            FirmbankingRequestStatus firmbankingStatus
    ) {
        return firmbankingRequestRepository.save(
                FirmbankingRequestJpaEntity.register(
                        fromBankName,
                        fromBankAccountNumber,
                        toBankName,
                        toBankAccountNumber,
                        moneyAmount,
                        firmbankingStatus
                )
        );
    }

    @Override
    public FirmbankingRequestJpaEntity modifyFirmbanking(FirmbankingRequestJpaEntity entity) {
        return firmbankingRequestRepository.save(entity);
    }
}
