package com.conexaproject.star_wars_app.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataException extends RuntimeException {

    private final Integer code;

    public DataException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
