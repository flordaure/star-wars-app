package com.conexaproject.star_wars_app.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Errors {

    ID_NOT_FOUND(404001, "The required id: %s, does not exist"),
    NAME_NOT_FOUND(404002, "The required name: %s, does not exist"),
    ID_PARAM_IS_NULL(400001,"The id parameter is required"),
    TITLE_PARAM_IS_NULL(400003, "The title parameter is required"),
    NAME_PARAM_IS_NULL(400004, "The name parameter is required"),
    INVALID_ID(400005, "The id provided is not valid. It must be a number"),
    PAGES_PARAM_IS_NULL(400006, "The pages parameter is required"),
    HTTP_STATUS_CODE_NOT_OK(500001, "The invoked API returned a status code -> %s"),
    CONNECTION_EXCEPTION(500002, "Problem communicating with the API. The problem is -> %s"),
    GENERAL_EXCEPTION(-1000, "Error -> %s");

    private final Integer code;
    private final String message;
}
