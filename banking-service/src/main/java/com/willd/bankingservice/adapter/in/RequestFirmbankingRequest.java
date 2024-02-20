package com.willd.bankingservice.adapter.in;

import lombok.Getter;

@Getter
public class RequestFirmbankingRequest {
    private String fromBankName;

    private String fromBankAccountNumber;

    private String toBankName;

    private String toBankAccountNumber;

    private Integer moneyAmount;

}
