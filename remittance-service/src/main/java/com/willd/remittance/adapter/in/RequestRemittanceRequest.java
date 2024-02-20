package com.willd.remittance.adapter.in;

import com.willd.domain.remittance.enums.RemittanceRequestType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRemittanceRequest {
    private Long fromMembershipId;

    private Long toMembershipId;

    private Integer amount;

    private RemittanceRequestType type;
}
