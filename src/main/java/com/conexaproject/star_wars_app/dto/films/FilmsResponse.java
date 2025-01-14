package com.conexaproject.star_wars_app.dto.films;

import com.conexaproject.star_wars_app.dto.BasicResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilmsResponse extends BasicResponse {

    private List<FilmsResult> result;

    public FilmsResponse(String message, List<FilmsResult> result){
        super(message);
        this.result= result;
    }

    public FilmsResponse() {
    }
}
