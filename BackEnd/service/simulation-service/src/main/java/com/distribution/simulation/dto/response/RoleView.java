package com.distribution.simulation.dto.response;

import lombok.Data;

import java.util.Set;

@Data
public class RoleView {

    private String id;

    private String name;

    private String description;

    private Set<AuthorityData> authorities;

    @Data
    public static class AuthorityData {
        private String id;
    }

}
