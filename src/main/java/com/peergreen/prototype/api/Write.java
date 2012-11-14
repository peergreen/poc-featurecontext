package com.peergreen.prototype.api;

import java.util.Properties;

public interface Write {

    void setProps(Properties props);
    void push(String name, String value);

    void addVersion(String version);
}
