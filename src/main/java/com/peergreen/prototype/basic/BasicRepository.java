package com.peergreen.prototype.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.peergreen.prototype.api.Read;
import com.peergreen.prototype.api.Repository;
import com.peergreen.prototype.api.Write;

public class BasicRepository implements Repository {

    private final Map<String, Properties> values;

    public BasicRepository(String name) {
        this.values = new HashMap<String, Properties>();
    }

    @Override
    public Read read(String version) {
        Properties props = values.get(version);
        if (props == null) {
            return null;
        }
        return new BasicRead(props);

    }

    @Override
    public Write write() {
        return new BasicWrite(values, new Properties());
    }

}
