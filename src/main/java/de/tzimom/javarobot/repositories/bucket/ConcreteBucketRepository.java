package de.tzimom.javarobot.repositories.bucket;

import de.tzimom.javarobot.entities.Bucket;

import java.util.Collection;

public class ConcreteBucketRepository implements BucketRepository {
    private final Collection<Bucket> buckets;

    public ConcreteBucketRepository(Collection<Bucket> buckets) {
        this.buckets = buckets;
    }

    public Collection<Bucket> getAllBuckets() {
        return buckets;
    }
}
