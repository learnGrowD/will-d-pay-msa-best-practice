package com.willd.remittance.adapter.in;

import com.willd.common.WebAdapter;
import com.willd.remittance.application.port.in.RemittanceRequestUseCase;
import com.willd.remittance.application.port.in.RequestRemittanceCommand;
import com.willd.domain.remittance.RemittanceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@WebAdapter
@RequiredArgsConstructor
public class RequestRemittanceController {
    private final RemittanceRequestUseCase remittanceRequestUseCase;
    @PostMapping(path = "/remittance/request")
    public Mono<RemittanceRequest> requestRemittance(@RequestBody RequestRemittanceRequest request) {
        RequestRemittanceCommand command = RequestRemittanceCommand.builder()
                .fromMembershipId(request.getFromMembershipId())
                .toMembershipId(request.getToMembershipId())
                .amount(request.getAmount())
                .type(request.getType())
                .build();
        return remittanceRequestUseCase.requestRemittance(command);
    }
}
