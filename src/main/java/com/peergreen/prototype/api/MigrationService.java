package com.peergreen.prototype.api;

public interface MigrationService {

    void copy(Repository in, Repository out);


    void activate(String featureName);
}
