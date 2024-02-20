package com.willd.bankingservice.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetBankAccountByMembershipIdCommand {
    private Long membershipId;
}
