package com.distribution.worker.service;


import com.distribution.worker.dto.request.LocationIn;
import com.distribution.worker.dto.response.ms2ms.ChangeWorkerStatusOut;
import com.distribution.worker.dto.response.ms2ms.ItemOut;
import com.distribution.worker.dto.response.ms2ms.UpdateTaskIn;

import java.util.List;

// Maintain micro service to service communication
public interface Ms2MsService {

    List<String> getShortestPath(LocationIn in);

    void handleMs2MsError(Integer code, String context);

    ItemOut.ProductOut fetchItemDetail(String productId);

    ChangeWorkerStatusOut.WorkerOut changeWorkerStatus(Long id, boolean status);

    void taskUpdate(UpdateTaskIn in);


}
