package com.distribution.worker.service.impl;

import com.distribution.worker.component.RestApiClient;
import com.distribution.worker.config.ConfigProperties;
import com.distribution.worker.dto.request.LocationIn;
import com.distribution.worker.dto.response.ms2ms.ChangeWorkerStatusOut;
import com.distribution.worker.dto.response.ms2ms.ItemOut;
import com.distribution.worker.dto.response.ms2ms.ShortestPathOut;
import com.distribution.worker.dto.response.ms2ms.UpdateTaskIn;
import com.distribution.worker.exception.ComplexValidationException;
import com.distribution.worker.service.Ms2MsService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

@Service
public class Ms2MsServiceImpl implements Ms2MsService {

    @Autowired
    private RestApiClient restClient;

    @Autowired
    private ConfigProperties configProperties;

    private static final Logger logger = Logger.getLogger(Ms2MsServiceImpl.class.getName());


    @Override
    public List<String> getShortestPath(LocationIn in) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(this.configProperties.getExternalEndpoint().getGetShortestPath());
        logger.info(urlBuilder.toString());
        ShortestPathOut response = this.restClient.getRestTemplate().postForObject(urlBuilder.toString(), in, ShortestPathOut.class);
        Integer code = response.getStatusCode();
        if (!code.equals(200)) {
            handleMs2MsError(code, "shortestPath");
        }
        return response.content;

    }

    @Override
    public void handleMs2MsError(Integer code, String context) {
        switch (code) {
            case 400:
                throw new ComplexValidationException("ms2ms", context + ".inputError");
            case 500:
                throw new ComplexValidationException("ms2ms", context + ".InternalError");
            case 401:
                throw new ComplexValidationException("ms2ms", context + ".AuthError");
        }
    }

    @Override
    public ItemOut.ProductOut fetchItemDetail(String productId) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(this.configProperties.getExternalEndpoint().getFetchProductDetail());
        urlBuilder.append(productId);
        logger.info(urlBuilder.toString());
        ItemOut response = this.restClient.getRestTemplate().getForObject(urlBuilder.toString(), ItemOut.class);
        Integer code = response.getStatusCode();
        if (!code.equals(200)) {
            handleMs2MsError(code, "productDetail");
        }
        return response.getContent();
    }

    @Override
    public ChangeWorkerStatusOut.WorkerOut changeWorkerStatus(Long id, boolean status) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(this.configProperties.getExternalEndpoint().getChangeWorkerStatus());
        urlBuilder.append(id + "/");
        urlBuilder.append(status);
        logger.info(urlBuilder.toString());
        ChangeWorkerStatusOut response = this.restClient.getRestTemplate().getForObject(urlBuilder.toString(), ChangeWorkerStatusOut.class);
        Integer code = response.getStatusCode();
        if (!code.equals(200)) {
            handleMs2MsError(code, "productDetail");
        }

        return response.getContent();
    }

    @Override
    public void taskUpdate(UpdateTaskIn in) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(this.configProperties.getExternalEndpoint().getUpdateTask());
        logger.info(urlBuilder.toString());

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        RestTemplate restTemplate = this.restClient.getRestTemplate();
        restTemplate.setRequestFactory(requestFactory);
        Object response = restTemplate.patchForObject(urlBuilder.toString(), in, Object.class);
    }
}
