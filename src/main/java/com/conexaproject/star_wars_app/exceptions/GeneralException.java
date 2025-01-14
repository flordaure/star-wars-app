package com.conexaproject.star_wars_app.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralException extends RuntimeException {

    private final Integer code;

    public GeneralException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
