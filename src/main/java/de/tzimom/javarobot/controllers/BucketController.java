package de.tzimom.javarobot.controllers;

import de.tzimom.javarobot.entities.Bucket;
import de.tzimom.javarobot.config.BucketConfig;

import java.math.BigInteger;

public class BucketController implements Bucket {
    private final BucketConfig config;

    private BigInteger ballCount = BigInteger.valueOf(0);

    public BucketController(BucketConfig config) {
        this.config = config;
    }

    public void dropBall() {
        ballCount = ballCount.add(BigInteger.valueOf(1));
    }

    public BigInteger getBallCount() {
        return ballCount;
    }

    public BucketConfig getConfig() {
        return config;
    }
}
