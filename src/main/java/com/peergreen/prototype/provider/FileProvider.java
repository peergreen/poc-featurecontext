package com.peergreen.prototype.provider;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;

import com.peergreen.prototype.basic.BasicRepositoryProvider;


@Component
@Provides
@Instantiate(name="File Repository Provider")
public class FileProvider extends BasicRepositoryProvider {

}
