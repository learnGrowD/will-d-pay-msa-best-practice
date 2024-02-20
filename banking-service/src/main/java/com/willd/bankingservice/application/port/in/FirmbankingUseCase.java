package com.willd.bankingservice.application.port.in;

import com.willd.domain.bank.FirmbankingRequest;

public interface FirmbankingUseCase {
    FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command);
}
