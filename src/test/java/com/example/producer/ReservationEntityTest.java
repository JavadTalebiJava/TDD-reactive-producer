package com.example.producer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@DataMongoTest
public class ReservationEntityTest {

    @Autowired
    private ReactiveMongoTemplate template;

    @Test
    public void persist() {
        Reservation reservation = new Reservation(null , "Cevat");

        Mono<Reservation> savedReservation = template.save(reservation);
        StepVerifier
                .create(savedReservation)
                .expectNextMatches( rez -> rez.getName().equalsIgnoreCase("Cevat") &&  rez.getId()!=null)
                .verifyComplete();

    }
}
