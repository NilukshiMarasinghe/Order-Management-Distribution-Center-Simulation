package com.distribution.worker.dto.response.wrapper;





import com.distribution.worker.enums.RestApiResponseStatus;

import java.util.List;


public class ListResponseWrapper<T> extends BaseResponseWrapper {

    private List<T> content;

    public ListResponseWrapper(List<T> content) {
        super(RestApiResponseStatus.OK.OK);
        this.content = content;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
