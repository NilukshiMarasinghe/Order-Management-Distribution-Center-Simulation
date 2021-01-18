package com.distribution.worker.dto.response.wrapper;


import com.distribution.worker.enums.RestApiResponseStatus;

import java.util.List;
import java.util.Map;


public class MapResponseWrapper<T> extends BaseResponseWrapper {

    private Map<String, List<T>> content;

    public MapResponseWrapper(Map<String, List<T>> content) {
        super(RestApiResponseStatus.OK.OK);
        this.content = content;
    }

    public Map<String, List<T>> getContent() {
        return content;
    }

    public void setContent(Map<String, List<T>> content) {
        this.content = content;
    }
}
