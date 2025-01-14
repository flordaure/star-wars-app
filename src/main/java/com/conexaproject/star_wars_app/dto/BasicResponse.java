package com.conexaproject.star_wars_app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicResponse {
    private String message;

    public BasicResponse(String message) {
        this.message = message;
    }

    public BasicResponse() {
    }
}
