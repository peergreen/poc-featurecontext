package com.peergreen.prototype.basic;

import java.util.Properties;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;

import com.peergreen.prototype.api.FeatureManager;
import com.peergreen.prototype.api.MigrationService;
import com.peergreen.prototype.api.Repository;


@Component
@Provides
@Instantiate(name="Migration Service")
public class BasicMigrationService implements MigrationService{

    @Requires
    private FeatureManager featureManager;

    @Override
    public void copy(Repository in, Repository out) {
        Properties inProps = in.read("production").getProps();
        out.write().setProps(inProps);

    }

    @Override
    public void activate(String featureName) {
        featureManager.refreshFeatureContextTomcat();
    }

}
