package com.willd.bankingservice.adapter.in;

import lombok.Getter;

@Getter
public class RegisterBankAccountRequest {
    private Long membershipId;

    private String bankName;

    private String bankAccountNumber;
}
