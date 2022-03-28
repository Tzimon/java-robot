package de.tzimom.javarobot.repositories;

import de.tzimom.javarobot.api.Ball;

import java.math.BigInteger;

public interface BallRepository {
    Ball createNewBall(BigInteger number);
}
