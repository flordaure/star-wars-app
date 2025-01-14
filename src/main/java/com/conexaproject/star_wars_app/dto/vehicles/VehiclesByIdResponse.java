package com.conexaproject.star_wars_app.dto.vehicles;

import com.conexaproject.star_wars_app.dto.BasicResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehiclesByIdResponse extends BasicResponse {

    private VehiclesResult result;

    public VehiclesByIdResponse(String message, VehiclesResult result){
        super(message);
        this.result= result;
    }

    public VehiclesByIdResponse() {
    }
}
