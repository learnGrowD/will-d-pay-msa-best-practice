package com.willd.bankingservice.adapter.in;

import com.willd.bankingservice.application.port.in.FirmbankingUseCase;
import com.willd.bankingservice.application.port.in.RequestFirmbankingCommand;
import com.willd.domain.bank.FirmbankingRequest;
import com.willd.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestFirmbankingController {

    private final FirmbankingUseCase firmbankingUseCase;

    @PostMapping(path = "/banking/firmbanking/request")
    public FirmbankingRequest requestFirmbanking(@RequestBody RequestFirmbankingRequest request) {
        RequestFirmbankingCommand command = RequestFirmbankingCommand.builder()
                .fromBankName(request.getFromBankName())
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .toBankName(request.getToBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .moneyAmount(request.getMoneyAmount())
                .build();
        return firmbankingUseCase.requestFirmbanking(command);
    }
}
