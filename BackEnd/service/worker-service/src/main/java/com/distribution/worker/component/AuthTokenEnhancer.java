package com.distribution.worker.component;


import com.distribution.worker.entity.User;
import com.distribution.worker.enums.UserType;
import com.distribution.worker.service.CryptoService;

import com.distribution.worker.util.PlatformConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthTokenEnhancer implements TokenEnhancer {

    @Autowired
    private CryptoService cryptoService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        if (PlatformConstant.AUTH_CLIENT_CPAP.equalsIgnoreCase(authentication.getOAuth2Request().getClientId())) {
            if (authentication.getPrincipal() != null && authentication.getPrincipal() instanceof User) {
                // new login scenario
                Map<String, Object> info = new HashMap<>();
                User user = (User) authentication.getPrincipal();
                info.put(PlatformConstant.AUTH_TOKEN_USER_ID, cryptoService.encryptEntityId(user.getId()));
                info.put(PlatformConstant.AUTH_TOKEN_USER_TYPE, UserType.ADMIN_USER.getCode());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

            } else if (authentication.getPrincipal() != null && authentication.getPrincipal() instanceof String) {
                // new access token generated with refresh token scenario
                User user = (User) authentication.getUserAuthentication().getDetails();
                Map<String, Object> info = new HashMap<>();
                info.put(PlatformConstant.AUTH_TOKEN_USER_ID, cryptoService.encryptEntityId(user.getId()));
                info.put(PlatformConstant.AUTH_TOKEN_USER_TYPE, UserType.ADMIN_USER.getCode());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
            }
        }

        return accessToken;
    }
}
