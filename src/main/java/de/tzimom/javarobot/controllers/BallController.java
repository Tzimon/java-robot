package de.tzimom.javarobot.controllers;

import de.tzimom.javarobot.entities.Ball;
import de.tzimom.javarobot.entities.RenderColor;

import java.math.BigInteger;

public class BallController implements Ball {
    private final BigInteger number;
    private final RenderColor ballColor;

    public BallController(BigInteger number, RenderColor ballColor) {
        this.number = number;
        this.ballColor = ballColor;
    }

    public BigInteger getNumber() {
        return number;
    }

    public RenderColor getColor() {
        return ballColor;
    }
}
