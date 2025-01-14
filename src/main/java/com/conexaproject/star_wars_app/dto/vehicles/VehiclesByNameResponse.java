package com.conexaproject.star_wars_app.dto.vehicles;

import com.conexaproject.star_wars_app.dto.BasicResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VehiclesByNameResponse extends BasicResponse {

    private List<VehiclesResult> result;

    public VehiclesByNameResponse(String message, List<VehiclesResult> result){
        super(message);
        this.result= result;
    }

    public VehiclesByNameResponse() {
    }
}
