package com.example.producer;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Flux;

@Import(ReservationHttpConfiguration.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "server.port=0"
)
public class BaseClass {

    /*
         find this object in context and if its exist, then Add this Mockito mock(fake)
         version of it.
     */
    @MockBean
    private ReservationRepository reservationRepository;

    @LocalServerPort
    private int port;

    @BeforeAll
    public void beforeAll() {
        //Mock is an Empty(Default) version of object(fake of it) - an STUB one
        Mockito.when(
                reservationRepository.findAll()
        ).thenReturn(
                Flux.just(new Reservation("1", "Sinan"),new Reservation("2", "Unal"))
        );

        RestAssured.baseURI = "http://localhost:" + this.port;
    }


}
