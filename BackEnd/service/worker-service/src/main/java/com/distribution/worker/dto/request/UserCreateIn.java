package com.distribution.worker.dto.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserCreateIn {

    @NotBlank(message = "userCreate.accountName.empty")
    private String accountName;

    @NotBlank(message = "userCreate.email.empty")
    private String email;

    @NotBlank(message = "userCreate.password.empty")
    private String password;

    private RoleIn role;

    @Data
    public static class RoleIn {

        @NotBlank(message = "userCreate.userRole.empty")
        private String id;
    }

}

