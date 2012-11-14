package com.peergreen.prototype.client;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.ipojo.annotations.Validate;

import com.peergreen.prototype.api.FeatureManager;
import com.peergreen.prototype.api.MigrationService;
import com.peergreen.prototype.api.Repository;
import com.peergreen.prototype.api.RepositoryProvider;
import com.peergreen.prototype.api.Write;


@Component
@Provides
@Instantiate(name="My Client")
public class MyClient {

    @Requires
    private FeatureManager featureManager;

    @Requires(filter="(factory.name=com.peergreen.prototype.provider.FileProvider)")
    private RepositoryProvider fileProvider;

    @Requires(filter="(factory.name=com.peergreen.prototype.provider.GitProvider)")
    private RepositoryProvider gitProvider;

    @Requires
    private MigrationService migrationService;

      public MyClient() {

    }

    // populate file repository
    private void setupRepositories() {
        Write write = fileProvider.getRepository("tomcat").write();
        write.push("key1",  "value1");
        write.push("key2",  "value2");
        write.push("key3",  "value3");
        write.addVersion("production");
    }


    @Validate
    public void start() {
        // init
        setupRepositories();

        // create feature context
        featureManager.createFeatureContextTomcat();

        // tomcat should start...
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Now migrate from file to git
        Repository gitRepo = gitProvider.getRepository("tomcat");
        Repository fileRepo = fileProvider.getRepository("tomcat");
        migrationService.copy(fileRepo, gitRepo);

        Write write = gitRepo.write();
        write.push("key1", "updateValue");
        write.addVersion("production");

        // Notify tomcat service that there is a new configuration ?
        migrationService.activate("tomcat");


    }



}
