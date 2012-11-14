package com.peergreen.prototype.basic;

import java.util.Map;
import java.util.Properties;

import com.peergreen.prototype.api.Write;

public class BasicWrite extends BasicRead implements Write {

    private final Map<String, Properties> repo;
    private Properties properties;


    public BasicWrite(Map<String, Properties> repo, Properties props) {
        super(props);
        this.repo = repo;
        this.properties = props;
    }


    @Override
    public void push(String name, String value) {
       properties.setProperty(name, value);
    }

    @Override
    public void addVersion(String version) {
        repo.put(version, properties);
    }


    @Override
    public void setProps(Properties props) {
        this.properties = props;
    }

}
