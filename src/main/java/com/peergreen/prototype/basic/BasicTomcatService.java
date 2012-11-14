package com.peergreen.prototype.basic;

import org.apache.felix.ipojo.annotations.Bind;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Modified;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Validate;

import com.peergreen.prototype.api.FeatureContext;
import com.peergreen.prototype.api.TomcatService;

@Component
@Provides
@Instantiate(name="Basic Tomcat Service")
public class BasicTomcatService implements TomcatService {


    @Validate
    public void start() {
        System.out.println("starting...");
    }


    @Bind(optional=false, filter="(name=tomcat)")
    public void bindFeatureContext(FeatureContext featureContext) {
        System.out.println("Feature context is being set");
        // Reading my conf
        System.out.println("FeatureContext set= " + featureContext);
        System.out.println("production view = " + featureContext.getProductionView());
        System.out.println("value = " + featureContext.getProductionView().getValue("key1"));

    }


    @Modified
    public void modifiedFeatureContext(FeatureContext featureContext) {
        System.out.println("Feature context is being updated");
        // Reading my conf
        System.out.println("FeatureContext set= " + featureContext);
        System.out.println("production view = " + featureContext.getProductionView());
        System.out.println("value = " + featureContext.getProductionView().getValue("key1"));
    }



}
