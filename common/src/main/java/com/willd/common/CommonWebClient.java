package com.willd.common;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;

@Component
public class CommonWebClient {
    private final WebClient webClient;

    public CommonWebClient() {
        this.webClient = WebClient.builder().build();
    }

    public Mono<String> sendGetRequestResultMono(String url)  {
        return webClient.get()
                .uri(URI.create(url))
                .retrieve()
                .bodyToMono(String.class);
    }

    public Flux<String> sendGetRequestResultFlux(String url) {
        return webClient.get()
                .uri(URI.create(url))
                .retrieve()
                .bodyToFlux(String.class);
    }

    public Mono<String> sendPostRequestResultMono(String url, Map<String, Object> body) {
        return webClient.post()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Flux<String> sendPostRequestResultFlux(String url, Map<String, Object> body) {
        return webClient.post()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToFlux(String.class);
    }
}
