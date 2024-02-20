package com.willd.bankingservice.adapter.out.service.membership;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.willd.bankingservice.application.port.out.MembershipPort;
import com.willd.common.CommonHttpClient;
import com.willd.common.ServiceAdapter;
import com.willd.domain.membership.Membership;
import org.springframework.beans.factory.annotation.Value;

@ServiceAdapter
public class MembershipServiceAdapter implements MembershipPort {

    private final CommonHttpClient commonHttpClient;
    private final ObjectMapper objectMapper;
    private final String membershipServiceUrl;

    public MembershipServiceAdapter(
            CommonHttpClient commonHttpClient,
            ObjectMapper objectMapper,
            @Value("${service.membership.url}") String membershipServiceUrl
    ) {
        this.commonHttpClient = commonHttpClient;
        this.objectMapper = objectMapper;
        this.membershipServiceUrl = membershipServiceUrl;
    }

    @Override
    public Membership getMemberStatus(Long membershipId) {
        String url = membershipServiceUrl + "/membership" + "/" + membershipId;
        try {
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();

            return objectMapper.readValue(jsonResponse, Membership.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
