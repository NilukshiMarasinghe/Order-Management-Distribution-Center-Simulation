package com.distribution.worker.dto.response;

import lombok.Data;

@Data
public class UserView {

    private String id;
    private String accountName;
    private String email;
    private String status;
    private RoleOut role;

    @Data
    public static class RoleOut {
        private String id;
    }

}
