package com.conexaproject.star_wars_app.dto.starships;

import com.conexaproject.star_wars_app.dto.BasicResponse;
import com.conexaproject.star_wars_app.dto.people.PeopleResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StarshipsByIdResponse extends BasicResponse {

    private StarshipsResult result;

    public StarshipsByIdResponse(String message, StarshipsResult result){
        super(message);
        this.result= result;
    }

    public StarshipsByIdResponse() {
    }
}
