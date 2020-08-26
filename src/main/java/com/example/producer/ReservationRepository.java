package com.example.producer;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ReservationRepository extends ReactiveCrudRepository<Reservation, String> {

    /*
        -This line returns a reactive stream that returns all the reservations named by "name" Parameter
        -Flux is a Publisher of Multi Mono
        -its Completely Async
     */
    Flux<Reservation> findAllByName(String name);
}
