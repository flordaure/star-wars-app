package com.conexaproject.star_wars_app.dto.people;

import com.conexaproject.star_wars_app.dto.BasicResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class PeopleByNameResponse extends BasicResponse {

    private List<PeopleResult> result;

    public PeopleByNameResponse(String message, List<PeopleResult>result){
        super(message);
        this.result = result;
    }

    public PeopleByNameResponse() {
    }
}