package com.company;

import java.util.List;

public class FireworkCollection {
    private final List<Firework> fireworks;

    public FireworkCollection() {
        fireworks = List.of(
                new Firework("Red"),
                new Firework("Yellow"),
                new Firework("Green"),
                new Firework("Blue"),
                new Firework("White")
        );
    }

    public List<Firework> getFireworks() {
        return fireworks;
    }
}
