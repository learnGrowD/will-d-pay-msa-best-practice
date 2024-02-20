package com.willd.bankingservice.adapter.out.service.firmbanking;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestFirmbanking {
    private RequestFirmbankingStatus resultCode; // 0: 성공, 1: 실패
}
