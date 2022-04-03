package de.tzimom.javarobot.entities;

import de.tzimom.javarobot.config.BucketConfig;

import java.math.BigInteger;

public interface Bucket {
    void dropBall();

    BigInteger getBallCount();

    BucketConfig getConfig();
}
