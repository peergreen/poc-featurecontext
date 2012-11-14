package com.peergreen.prototype.api;

public interface Repository {

    Read read(String version);
    Write write();
}
