package com.example.producer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

/*
    We want to test the Web Tier, not the Data Tier,
    we already done that before by MongoDB.
*/
@WebFluxTest(ReservationHttpConfiguration.class)
public class ReservationHttpTest {
    /*
        find this object in context and if its exist, then Add this Mockito mock(fake)
        version of it.
    */
    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private WebTestClient client; //in order to test the Web Slice I wanna inject the WebTestClient

    @Test
    public void getAllReservations() {
        //Mock is an Empty(Default) version of object(fake of it) - an STUB one
        Mockito.when(
                reservationRepository.findAll()
        ).thenReturn(
                Flux.just(new Reservation("1", "Sinan"),new Reservation("2", "Unal"))
        );

        client
                .get()
                .uri("/reservations")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectBody()
                    .jsonPath("@.[0].name").isEqualTo("Sinan")
                    .jsonPath("@.[1].name").isEqualTo("Unal");
    }
}
