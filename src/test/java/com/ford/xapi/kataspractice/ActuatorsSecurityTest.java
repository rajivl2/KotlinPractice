package com.ford.xapi.kataspractice;

import com.ford.cloudnative.base.app.web.exception.handler.ExceptionHandlerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(ExceptionHandlerConfiguration.class)
@AutoConfigureWebTestClient(timeout = "30000")
public class ActuatorsSecurityTest {

    @Autowired
    private WebTestClient webClient;

    /***********************************************************************************************
     * ENDPOINTS: Actuator
     ***********************************************************************************************/

    @Test
    public void should_allowActuatorInfoEndpoint_withoutAuthentication() {
        webClient.get().uri("/actuator/info").exchange().expectStatus().is2xxSuccessful();
    }

    @Test
    public void should_allowActuatorHealthEndpoint_withoutAuthentication() {
        FluxExchangeResult<Object> responseObject = webClient.get().uri("/actuator/health").exchange().returnResult(Object.class);
        assertThat(responseObject.getStatus()).isNotIn(HttpStatus.UNAUTHORIZED, HttpStatus.FORBIDDEN, HttpStatus.NOT_FOUND, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Test
    public void should_notAllowSensitiveActuatorEndpoints_withoutAuthentication() {
        List<String> sensitiveActuatorEndpoints = TestUtil.getSensitiveActuatorEndpoints();
        for (String sensitiveEndpoint : sensitiveActuatorEndpoints) {
            webClient.get().uri(sensitiveEndpoint).exchange().expectStatus().is4xxClientError();
        }
    }

    @Test
    public void should_notAllowSensitiveActuatorEndpoints_withInValidAuthentication() {
        webClient.get().uri("/actuator/env").header("Authorization", "Basic nnnn").exchange().expectStatus().is4xxClientError();
    }
}
