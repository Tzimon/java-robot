package de.tzimom.javarobot.repositories.bucket;

import de.tzimom.javarobot.entities.Bucket;

import java.util.Collection;

public interface BucketRepository {
    Collection<Bucket> getAllBuckets();
}
