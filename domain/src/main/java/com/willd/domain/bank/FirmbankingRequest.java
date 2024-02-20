package com.willd.domain.bank;

import com.willd.domain.bank.enums.FirmbankingRequestStatus;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FirmbankingRequest {
    private Long firmbankingRequestId;

    private String fromBankName;

    private String fromBankAccountNumber;

    private String toBankName;

    private String toBankAccountNumber;

    private Integer moneyAmount;

    private FirmbankingRequestStatus firmbankingStatus;

    private String uuid;

    public static FirmbankingRequest generate(
            Long firmbankingRequestId,
            String fromBankName,
            String fromBankAccountNumber,
            String toBankName,
            String toBankAccountNumber,
            Integer moneyAmount,
            FirmbankingRequestStatus firmbankingStatus,
            String uuid
    ) {
        return new FirmbankingRequest(
                firmbankingRequestId,
                fromBankName,
                fromBankAccountNumber,
                toBankName,
                toBankAccountNumber,
                moneyAmount,
                firmbankingStatus,
                uuid
        );
    }
}
