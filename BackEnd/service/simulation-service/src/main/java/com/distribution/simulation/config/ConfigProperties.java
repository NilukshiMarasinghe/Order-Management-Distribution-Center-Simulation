package com.distribution.simulation.config;

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

        private String userCreate;

        private String userView;

        private String roleCreate;

        private String roleView;

        private String getDcMap;

        private String getAllVertices;

        private String getOppositeVertices;

        private String getAllItems;

        private String getAllItemsMin;

        private String itemView;

        private String getAllWorkers;

        private String getAllByStatus;

        private String getByName;

        private String getPackingStations;

        private String getShortestPath;

        private String itemById;

        private String changeWorkerStatus;


    }

}
