package de.tzimom.javarobot.repositories.ball;

import de.tzimom.javarobot.entities.Ball;

import java.math.BigInteger;

public interface BallRepository {
    Ball createNewBall(BigInteger number);
}
