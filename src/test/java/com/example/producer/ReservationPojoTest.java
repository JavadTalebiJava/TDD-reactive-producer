package com.example.producer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ReservationPojoTest {

    @Test
    public void create() {
        Reservation reservation = new Reservation("1", "Javad");

        Assertions.assertEquals(reservation.getName(), "Javad");
        //ve ya
        Assertions.assertTrue(reservation.getName().equalsIgnoreCase("javad"));
        Assertions.assertFalse(reservation.getId().equals("2"));

    }
}
