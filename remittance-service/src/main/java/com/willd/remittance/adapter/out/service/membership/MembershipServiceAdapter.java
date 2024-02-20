package com.willd.remittance.adapter.out.service.membership;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.willd.common.CommonWebClient;
import com.willd.common.ServiceAdapter;
import com.willd.domain.membership.Membership;
import com.willd.remittance.application.port.out.MembershipPort;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@ServiceAdapter
public class MembershipServiceAdapter implements MembershipPort {
    private final CommonWebClient commonWebClient;

    private final ObjectMapper objectMapper;

    private final String serviceMembershipUrl;

    public MembershipServiceAdapter(
            CommonWebClient commonWebClient,
            ObjectMapper objectMapper,
            @Value("${service.membership.url}") String serviceMembershipUrl
    ) {
        this.commonWebClient = commonWebClient;
        this.objectMapper = objectMapper;
        this.serviceMembershipUrl = serviceMembershipUrl;
    }

    @Override
    public Mono<Membership> getMembershipStatus(Long membershipId) {
        String url = serviceMembershipUrl + "/membership" + "/" + membershipId;
        return commonWebClient.sendGetRequestResultMono(url)
                .flatMap(jsonString -> {
                    try {
                        return Mono.just(objectMapper.readValue(jsonString, Membership.class));
                    } catch (Exception e) {
                        return Mono.error(e);
                    }
                });
    }

    @Override
    public Mono<Membership> checkMembershipValidity(Long membershipId) {
        return getMembershipStatus(membershipId)
                .filter(Membership::getIsValid)
                .switchIfEmpty(Mono.error(new RuntimeException("")));
    }
}
