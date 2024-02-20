package com.willd.bankingservice.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterBankAccountCommand {
    private Long membershipId;

    private String bankName;

    private String bankAccountNumber;
}
