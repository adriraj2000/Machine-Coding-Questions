package org.example;

public class VersionedValue {
    String value;
    Long version;
    public VersionedValue(String value, Long version){
        this.value = value;
        this.version = version;
    }
}
