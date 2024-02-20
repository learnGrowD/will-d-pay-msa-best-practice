package com.willd.moneyservice.adapter.out.service.membership;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.willd.common.CommonHttpClient;
import com.willd.common.ServiceAdapter;
import com.willd.domain.membership.Membership;
import com.willd.moneyservice.application.port.out.MembershipPort;
import org.springframework.beans.factory.annotation.Value;

@ServiceAdapter
public class MembershipServiceAdapter implements MembershipPort {

    private final CommonHttpClient commonHttpClient;

    private final ObjectMapper objectMapper;

    private final String serviceMembershipUrl;


    public MembershipServiceAdapter(
            CommonHttpClient commonHttpClient,
            ObjectMapper objectMapper,
            @Value("${service.membership.url}") String serviceMembershipUrl
    ) {
        this.commonHttpClient = commonHttpClient;
        this.objectMapper = objectMapper;
        this.serviceMembershipUrl = serviceMembershipUrl;
    }


    @Override
    public Membership getMembership(Long targetMembershipId) throws Exception {
        String url = serviceMembershipUrl + "/membership" + "/" + targetMembershipId;
        try {
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();
            return objectMapper.readValue(jsonResponse, Membership.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
