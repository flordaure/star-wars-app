package com.conexaproject.star_wars_app.dto.people;

import com.conexaproject.star_wars_app.dto.BasicResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeopleByIdResponse extends BasicResponse {

    private PeopleResult result;

    public PeopleByIdResponse(String message, PeopleResult result){
        super(message);
        this.result= result;
    }

    public PeopleByIdResponse() {
    }
}
