package com.distribution.simulation.dto.request;


import com.distribution.simulation.modelmapper.ModelMappingAware;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class RoleCreateIn {

    private String name;

    private String description;

    @Valid
    @NotEmpty(message = "platformRoleCreateRequest.authorities.empty")
    private List<AuthorityData> authorities;

    @Data
    public static class AuthorityData implements ModelMappingAware {

        @NotBlank(message = "platformRoleCreateRequest.authorities.id.empty")
        private String id;

        @Override
        public Class<?> getDestinationType() {
            return AuthorityData.class;
        }
    }
}

