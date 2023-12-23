package org.example.otelbaggage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AppService {

    @Async
    public void hello() {
        log.info("Inside Async method");
    }

}
