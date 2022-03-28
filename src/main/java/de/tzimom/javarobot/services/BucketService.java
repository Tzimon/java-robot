package de.tzimom.javarobot.services;

import de.tzimom.javarobot.api.Bucket;

import java.util.List;
import java.util.Optional;

public class BucketService {
    private final List<Bucket> buckets;

    public BucketService(List<Bucket> buckets) {
        this.buckets = buckets;
    }

    public Optional<Bucket> dropBall(float angle) {
        var bucket = buckets.stream()
                .filter(entry -> Math.abs(angle - entry.getConfig().relativeAngle()) < entry.getConfig().angleErrorMargin())
                .findFirst();

        bucket.ifPresent(Bucket::dropBall);
        return bucket;
    }
}
