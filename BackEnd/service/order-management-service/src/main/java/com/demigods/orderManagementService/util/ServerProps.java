package com.demigods.orderManagementService.Util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ServerProps {

    Logger logger = LoggerFactory.getLogger(ServerProps.class);

    @Autowired
    Environment environment;

    private String port;
    private String local_hostName;
    private String local_hostAddress;

    private String remote_hostName;
    private String remote_hostAddress;

    public ServerProps() {
    }

    public void setServerProps() {
        if(environment.getProperty("is.container").equalsIgnoreCase("true")) {
            logger.info("Using configurations for container.");
            this.port = environment.getProperty("container.port");
            this.local_hostAddress = InetAddress.getLoopbackAddress().getHostAddress();
            this.local_hostName = InetAddress.getLoopbackAddress().getHostName();
            this.remote_hostAddress = environment.getProperty("container.host");
            this.remote_hostName = environment.getProperty("container.host");
        } else {
            logger.info("Using configurations for local.");
            this.port = environment.getProperty("server.port");
            try {
                this.local_hostAddress = InetAddress.getLocalHost().getHostAddress();
                this.local_hostName = InetAddress.getLocalHost().getHostName();

                this.remote_hostAddress = InetAddress.getLoopbackAddress().getHostAddress();
                this.remote_hostName = InetAddress.getLoopbackAddress().getHostName();

            } catch (UnknownHostException e) {

            }
        }
    }

    public String getPort() {
        return port;
    }

    public String getLocal_hostName() {
        return local_hostName;
    }

    public String getLocal_hostAddress() {
        return local_hostAddress;
    }

    public String getRemote_hostName() {
        return remote_hostName;
    }

    public String getRemote_hostAddress() {
        return remote_hostAddress;
    }

}
