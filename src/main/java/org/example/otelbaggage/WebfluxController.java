package org.example.otelbaggage;

import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WebfluxController {

    private final WebClient webClient;
    private final AppService service;

    @PostMapping("/rolldice")
    public Mono<String> play(@RequestParam String player) {
        Baggage baggage = Baggage.builder().put("player", player).build();
        try (Scope scope = baggage.makeCurrent()) {
            log.info("Play started for player {}", player);
            return getRandomNumber(0, 10)
                    .flatMap(__ -> register(player))
                    .doFinally(signalType -> {
                        service.hello();
                        log.info("Finished with {}", signalType);
                    });
        }
    }

    private Mono<String> register(String player) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/client/register/{player}")
                        .build(player))
                .retrieve().bodyToMono(String.class);
    }

    private Mono<Integer> getRandomNumber(int min, int max) {
        log.info("Generating random number ...");
        return Mono.defer(() -> Mono.just(ThreadLocalRandom.current().nextInt(min, max + 1)));
    }


}
