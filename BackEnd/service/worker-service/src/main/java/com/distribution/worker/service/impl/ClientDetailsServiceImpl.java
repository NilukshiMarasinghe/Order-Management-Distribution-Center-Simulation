package com.distribution.worker.service.impl;

import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

public class ClientDetailsServiceImpl extends JdbcClientDetailsService {
    public ClientDetailsServiceImpl(DataSource dataSource) {
        super(dataSource);
        this.setSelectClientDetailsSql("select client_id, client_secret, resource_ids, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove from oauth_client_details where client_id = ?");
    }
}
