package com.willd.bankingservice.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestFirmbankingCommand {
    private String fromBankName;

    private String fromBankAccountNumber;

    private String toBankName;

    private String toBankAccountNumber;

    private Integer moneyAmount;
}
