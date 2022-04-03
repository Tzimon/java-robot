package de.tzimom.javarobot.services;

import de.tzimom.javarobot.entities.Bucket;
import de.tzimom.javarobot.repositories.bucket.BucketRepository;

import java.util.Optional;

public class BucketService {
    private final BucketRepository bucketRepository;

    public BucketService(BucketRepository bucketRepository) {
        this.bucketRepository = bucketRepository;
    }

    public Optional<Bucket> dropBall(float angle) {
        Optional<Bucket> bucket = bucketRepository.getAllBuckets().stream()
                .filter(entry -> Math.abs((angle + 360) % 360 - (entry.getConfig().relativeAngle() + 360) % 360)
                        < entry.getConfig().angleErrorMargin())
                .findFirst();

        bucket.ifPresent(Bucket::dropBall);
        return bucket;
    }
}
