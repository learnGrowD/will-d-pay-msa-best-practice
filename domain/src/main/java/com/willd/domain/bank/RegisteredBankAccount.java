package com.willd.domain.bank;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBankAccount {
    private Long registeredBankAccountId;

    private Long membershipId;

    private String bankName;

    private String bankAccountNumber;

    private Boolean linkedStatusIsValid;

    public static RegisteredBankAccount generate(
            Long registeredBankAccountId,
            Long membershipId,
            String bankName,
            String bankAccountNumber,
            Boolean linkedStatusIsValid
    ) {
        return new RegisteredBankAccount(
                registeredBankAccountId,
                membershipId,
                bankName,
                bankAccountNumber,
                linkedStatusIsValid
        );
    }
}
