package com.conexaproject.star_wars_app.controller;

import com.conexaproject.star_wars_app.model.ErrorResponse;
import com.conexaproject.star_wars_app.model.films.Film;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.conexaproject.star_wars_app.utility.Constants.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

public interface FilmController {

    @GetMapping(path = "/films")
    @Operation(summary = "Get all films", description = "Method that returns all the Star Wars films")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_RESPONSE_OK, description = "Return de complete list of films",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Film.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_INTERNAL_SERVER_ERROR, description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,  schema = @Schema(implementation = ErrorResponse.class)))}
    )
    ResponseEntity<List<Film>> getAllFilms();

    @GetMapping(path = "/filmsById")
    @Operation(summary = "Get a film by id", description = "Method that returns a specific film")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_RESPONSE_OK, description = "Return a specific film",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Film.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_INTERNAL_SERVER_ERROR, description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_BAD_REQUEST, description = "Bad Request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<Film> getFilmsById(@Parameter(description = "Film Id", required = true)
                                                   @RequestParam(required = false) Integer id);

    @GetMapping(path = "/filmsByTitle")
    @Operation(summary = "Get a films by title", description = "Method that returns the films that contain the title sent by parameter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_RESPONSE_OK, description = "Return one or more films",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Film.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_INTERNAL_SERVER_ERROR, description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_BAD_REQUEST, description = "Bad Request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<List<Film>> getFilmsByTitle(@Parameter(description = "Film title", required = true) @RequestParam(required = false) String title);
}
