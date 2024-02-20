package com.willd.bankingservice.application.port.in;

import com.willd.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetBankAccountByBankAccountNumberCommand extends SelfValidating<GetBankAccountByBankAccountNumberCommand> {
    @NotBlank
    private String bankAccountNumber;

    public GetBankAccountByBankAccountNumberCommand(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
        this.validateSelf();
    }
}
