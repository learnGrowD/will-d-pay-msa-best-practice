package com.willd.bankingservice.adapter.out.service.firmbanking;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Firmbanking {
    private String bankName;

    private String bankAccountNumber;

    private Boolean isValid;
}
