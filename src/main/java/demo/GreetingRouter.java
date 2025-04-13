package demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
@Tag(name = "Greeting API", description = "Endpoints for greeting service")
public class GreetingRouter {

    @Bean
    @RouterOperation(
            path = "/hello",
            method = RequestMethod.GET,
            operation = @Operation(
                    operationId = "getGreeting",
                    summary = "Get a greeting message",
                    description = "Returns a JSON object with a greeting message",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Successful response",
                                    content = @Content(schema = @Schema(implementation = Greeting.class))
                            )
                    }
            )
    )
    @Tag(name = "Greeting API", description = "Endpoints for greeting service")
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {
        return RouterFunctions
                .route(GET("/hello").and(accept(MediaType.APPLICATION_JSON)), greetingHandler::hello);
    }

}
