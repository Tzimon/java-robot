package de.tzimom.javarobot.entities;

import de.tzimom.javarobot.api.Ball;
import de.tzimom.javarobot.api.BallColor;

import java.math.BigInteger;

public class BasicBall implements Ball {
    private final BigInteger number;
    private final BallColor ballColor;

    public BasicBall(BigInteger number, BallColor ballColor) {
        this.number = number;
        this.ballColor = ballColor;
    }

    public BigInteger getNumber() {
        return number;
    }

    public BallColor getColor() {
        return ballColor;
    }
}
