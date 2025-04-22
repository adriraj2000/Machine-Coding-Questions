package org.example.entities;

import java.util.concurrent.atomic.AtomicInteger;

public class S3Object {
    private String versionId;
    private long timestamp;
    private byte[] data;

    public S3Object(byte[] data) {
        this.timestamp = System.currentTimeMillis();
        this.data = data;
    }

    public String getVersionId() {
        return versionId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public byte[] getData() {
        return data;
    }
}
