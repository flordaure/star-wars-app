package com.conexaproject.star_wars_app.dto.films;

import com.conexaproject.star_wars_app.dto.BasicResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmsByIdResponse extends BasicResponse {

    private FilmsResult result;

    public FilmsByIdResponse(String message, FilmsResult result){
        super(message);
        this.result= result;
    }

    public FilmsByIdResponse() {
    }
}
