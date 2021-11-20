package com.company;

import java.util.ArrayList;
import java.util.List;

public class FireworkCollection {
    private final List<Firework> fireworks;

    public FireworkCollection() {



        Firework redFirework = new Firework("Red");
        Firework yellowFirework = new Firework("Yellow");
        Firework greenFirework = new Firework("Green");
        Firework blueFirework = new Firework("Blue");
        Firework whiteFirework = new Firework("White");
        this.fireworks = new ArrayList<>();
        this.fireworks.add(redFirework);
        this.fireworks.add(yellowFirework);
        this.fireworks.add(greenFirework);
        this.fireworks.add(blueFirework);
        this.fireworks.add(whiteFirework);
    }

    public List<Firework> getFireworks() {
        return fireworks;
    }
}
