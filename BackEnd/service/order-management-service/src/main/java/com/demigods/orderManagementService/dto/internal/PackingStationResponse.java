package com.demigods.orderManagementService.dto.internal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PackingStationResponse {
    private long id;
    private Boolean isAvailable;
    
	public PackingStationResponse() {
		this.isAvailable = true;
	}
    
    
}
