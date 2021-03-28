package com.dylan;

import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("rest")
public class AppResConfig extends ResourceConfig {

    public AppResConfig(){
        packages(true, "com.dylan");
    }
}
