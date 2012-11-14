package com.peergreen.prototype.api;

import java.util.Properties;

public interface Read {

    Properties getProps();

    String getValue(String name);
}
