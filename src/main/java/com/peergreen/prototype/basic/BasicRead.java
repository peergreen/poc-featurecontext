package com.peergreen.prototype.basic;

import java.util.Properties;

import com.peergreen.prototype.api.Read;

public class BasicRead implements Read {

    private final Properties properties;

    public BasicRead(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String getValue(String name) {
        return properties.getProperty(name);

    }

    @Override
    public Properties getProps() {
        return properties;
    }

}
