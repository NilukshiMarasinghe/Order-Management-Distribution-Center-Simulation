package com.distribution.worker.dto.response.ms2ms;

import lombok.Data;

import java.util.List;

@Data
public class ShortestPathOut {

    public String status;
    public Integer statusCode;
    public List<String> content;
}
