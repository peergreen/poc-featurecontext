package com.peergreen.prototype.basic;

import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.peergreen.prototype.api.FeatureContext;
import com.peergreen.prototype.api.FeatureManager;
import com.peergreen.prototype.api.RepositoryProvider;


@Component
@Provides
@Instantiate(name="Feature Manager")
public class BasicFeatureManager implements FeatureManager {

    private final BundleContext bc;

    @Requires(filter="(factory.name=com.peergreen.prototype.provider.FileProvider)")
    private RepositoryProvider fileProvider;


    @Requires(filter="(factory.name=com.peergreen.prototype.provider.GitProvider)")
    private RepositoryProvider gitProvider;

    private ServiceRegistration tomcatFeatureContextRegistration = null;

    private FeatureContext tomcatFeatureContext = null;

    private final Dictionary<String, String> dictionary;

    public BasicFeatureManager(BundleContext bc) {
        this.bc = bc;
        this.dictionary = new Hashtable<String, String>();
    }


    @Override
    public void createFeatureContextTomcat() {
        this.tomcatFeatureContext = new BasicFeatureContext(fileProvider.getRepository("tomcat"));
        this.dictionary.put("name", "tomcat");
        this.tomcatFeatureContextRegistration = bc.registerService(FeatureContext.class.getName(), tomcatFeatureContext, dictionary);
    }


    @Override
    public void refreshFeatureContextTomcat() {
        this.tomcatFeatureContext.setRepository(gitProvider.getRepository("tomcat"));
        dictionary.put("refresh", "true");
        this.tomcatFeatureContextRegistration.setProperties(dictionary);
    }
}
