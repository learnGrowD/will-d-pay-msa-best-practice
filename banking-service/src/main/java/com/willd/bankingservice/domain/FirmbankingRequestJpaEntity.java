package com.willd.bankingservice.domain;

import com.willd.domain.bank.enums.FirmbankingRequestStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Table(name = "request_firmbanking")
@Entity
@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FirmbankingRequestJpaEntity {
    @Id
    @GeneratedValue
    private Long firmbankingRequestId;

    private String fromBankName;

    private String fromBankAccountNumber;

    private String toBankName;

    private String toBankAccountNumber;

    private Integer moneyAmount;

    private FirmbankingRequestStatus firmbankingStatus;

    private String uuid;

    public static FirmbankingRequestJpaEntity register(
            String fromBankName,
            String fromBankAccountNumber,
            String toBankName,
            String toBankAccountNumber,
            Integer moneyAmount,
            FirmbankingRequestStatus firmbankingStatus
    ) {
        return FirmbankingRequestJpaEntity.builder()
                .fromBankName(fromBankName)
                .fromBankAccountNumber(fromBankAccountNumber)
                .toBankName(toBankName)
                .toBankAccountNumber(toBankAccountNumber)
                .moneyAmount(moneyAmount)
                .firmbankingStatus(firmbankingStatus)
                .build();
    }

    public void modify(
            String fromBankName,
            String fromBankAccountNumber,
            String toBankName,
            String toBankAccountNumber,
            Integer moneyAmount,
            FirmbankingRequestStatus firmbankingStatus
    ) {
        if (fromBankName != null) setFromBankName(fromBankName);
        if (fromBankAccountNumber != null) setFromBankAccountNumber(fromBankAccountNumber);
        if (toBankName != null) setToBankName(toBankName);
        if (toBankAccountNumber != null) setToBankAccountNumber(toBankAccountNumber);
        if (moneyAmount != null) setMoneyAmount(moneyAmount);
        if (firmbankingStatus != null) setFirmbankingStatus(firmbankingStatus);
    }

    public void assignUUID(String uuid) {
        setUuid(uuid);
    }

    public void assignFirmbankingStatus(FirmbankingRequestStatus status) {
        setFirmbankingStatus(status);
    }
}
