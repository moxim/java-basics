package ch.finnova.java.schulung;

import ch.finnova.java.schulung.db.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

//@SpringBootApplication
public class RestClient {
    private static final Logger log = LoggerFactory.getLogger(RestClient.class);
    private static Long addressId;

    public static void main(String[] aLotOfArgs) {
        if (aLotOfArgs == null || aLotOfArgs.length < 1) {
            addressId = 1001L;
        } else {
            addressId = Long.valueOf(aLotOfArgs[0]);
        }

        SpringApplication app = new SpringApplication(RestClient.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8083"));
        app.run(aLotOfArgs);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder aBuilder) {
        return aBuilder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate aRestTemplate) throws Exception {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                try {
                    Address address = aRestTemplate.getForObject(
                            "http://localhost:8080/addresses/" + addressId, Address.class);
                    log.info(address.toString());
                } catch (HttpClientErrorException.NotFound e) {
                    log.error(e.getMessage());
                }
            }
        };
    }
}
