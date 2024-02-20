package com.willd.remittance.application.port.in;

import com.willd.domain.remittance.RemittanceRequest;
import reactor.core.publisher.Mono;

public interface RemittanceRequestUseCase {
    Mono<RemittanceRequest> requestRemittance(RequestRemittanceCommand command);
}
