package de.tzimom.javarobot.api;

import de.tzimom.javarobot.config.BucketConfig;

import java.math.BigInteger;

public interface Bucket {
    void dropBall();

    BigInteger getBallCount();

    BucketConfig getConfig();
}
