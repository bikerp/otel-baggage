# Getting Started
## Application setup

### Environment variables

```
OTEL_PROPAGATORS=tracecontext,baggage,b3multi,b3
```

### VM arguments

```
-javaagent:${PROJECT_PATH}/opentelemetry-javaagent.jar 
-Dotel.instrumentation.common.enduser.id.enabled=true 
-Dotel.instrumentation.logback-mdc.add-baggage=true
```

## Run sample

```http request
POST http://localhost:8080/rolldice?player=John
```
