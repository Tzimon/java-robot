package de.tzimom.javarobot.entities;

import de.tzimom.javarobot.api.Bucket;
import de.tzimom.javarobot.config.BucketConfig;

import java.math.BigInteger;

public class BasicBucket implements Bucket {
    private final BucketConfig config;

    private BigInteger ballCount = BigInteger.valueOf(0);

    public BasicBucket(BucketConfig config) {
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
