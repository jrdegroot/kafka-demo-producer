package com.example.kafkademoproducer.model;

import java.util.Random;

public enum Location {
    NL,
    DE,
    BE;

    private static final Random RANDOM = new Random();

    public static Location randomLocation()  {
        Location[] locations = values();
        return locations[RANDOM.nextInt(locations.length)];
    }
}
