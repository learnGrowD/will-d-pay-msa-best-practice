package com.willd.bankingservice.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;

@Table(name = "registered_bank_account")
@Entity
@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBankAccountJpaEntity {
    @Id
    @GeneratedValue
    private Long registeredBankAccountId;

    private Long membershipId;

    private String bankName;

    private String bankAccountNumber;

    private Boolean linkedStatusIsValid;

    public static RegisteredBankAccountJpaEntity register(
            Long membershipId,
            String bankName,
            String bankAccountNumber,
            Boolean linkedStatusIsValid
    ) {
        return RegisteredBankAccountJpaEntity.builder()
                .membershipId(membershipId)
                .bankName(bankName)
                .bankAccountNumber(bankAccountNumber)
                .linkedStatusIsValid(linkedStatusIsValid)
                .build();
    }

    public void modify(
            Long membershipId,
            String bankName,
            String bankAccountNumber,
            Boolean linkedStatusIsValid
    ) {
        if (membershipId != null) setMembershipId(membershipId);
        if (bankName != null) setBankName(bankName);
        if (bankAccountNumber != null) setBankAccountNumber(bankAccountNumber);
        if (linkedStatusIsValid != null) setLinkedStatusIsValid(linkedStatusIsValid);

    }
}
