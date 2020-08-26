package com.example.producer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DataMongoTest
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void query() {
       /*
        - Delete everything
        - persist 4 record
        - then query by name.
        */
        Flux<Reservation> reservationFlux = reservationRepository.deleteAll()//Delete everything
                .thenMany(
                        Flux.just("A","B", "C", "D")
                                .map(name -> new Reservation(null, name))//create a new Reservation for each item SYNC
                                .flatMap(rec -> reservationRepository.save(rec)) //save each Reservation in Db        ASYNC
                                .thenMany(reservationRepository.findAllByName("C")) //after saving event happen query by name="C"
                );

        StepVerifier
                .create(reservationFlux)
                .expectNextCount(1) //3 burada yanlis bilgi vererek Test The negative yapiyoruz.
                .verifyComplete();

    }
}
