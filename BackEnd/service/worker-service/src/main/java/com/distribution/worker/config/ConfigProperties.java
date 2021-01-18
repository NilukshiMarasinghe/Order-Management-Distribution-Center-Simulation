package com.distribution.worker.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties("app")
@Configuration
public class ConfigProperties {

    private Auth auth;

    private Crypto crypto;

    private Endpoint endpoint;

    private ExternalEndpoint externalEndpoint;

    private String passwordPolicy;


    @Data
    public static class Auth {

        private Integer defaultAccessTokenTimeout;

        private Integer defaultRefreshTokenTimeout;

        private String kfName;

        private String ksPass;

        private String resourceId;

        private String corsAllowedOrigins;

    }

    @Data
    public static class Crypto {

        private String password;

        private String salt;
    }


    @Data
    public static class Endpoint {
        private String getShortestPath;
        private String getTasksByPicker;
    }

    @Data
    public static class ExternalEndpoint {

        private String getShortestPath;

        private String fetchProductDetail;

        private String changeWorkerStatus;

        private String updateTask;


    }

}
