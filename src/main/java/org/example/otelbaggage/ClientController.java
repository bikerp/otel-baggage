package org.example.otelbaggage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final AppService service;

    @PostMapping("/client/register/{player}")
    public Mono<String> play(@PathVariable String player) {
        log.info("Registering player {}", player);
        service.hello();
        return Mono.just("OK");
    }
}
