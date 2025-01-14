package com.conexaproject.star_wars_app.dto.starships;

import com.conexaproject.star_wars_app.dto.BasicResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StarshipsByNameResponse extends BasicResponse {

    private List<StarshipsResult> result;

    public StarshipsByNameResponse(String message, List<StarshipsResult> result){
        super(message);
        this.result= result;
    }

    public StarshipsByNameResponse() {
    }
}
