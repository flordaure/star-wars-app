package com.conexaproject.star_wars_app.controller;

import com.conexaproject.star_wars_app.model.BasicInformation;
import com.conexaproject.star_wars_app.model.ErrorResponse;
import com.conexaproject.star_wars_app.model.characters.Character;
import com.conexaproject.star_wars_app.model.starships.Starship;
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

public interface StarshipController {

    @GetMapping(path = "/starships", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all starships", description = "Method that returns all the starships in the Star Wars world")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_RESPONSE_OK, description = "Return de complete list of starships",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = BasicInformation.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_INTERNAL_SERVER_ERROR, description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))}
    )
    ResponseEntity<List<BasicInformation>> getAllStarships();

    @GetMapping(path = "/starships-pages")
    @Operation(summary = "Get starships from specific pages", description = "Method that returns the starships listed on certain pages")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_RESPONSE_OK, description = "Return starship listed on certain pages",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = BasicInformation.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_INTERNAL_SERVER_ERROR, description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_BAD_REQUEST, description = "Bad Request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<List<BasicInformation>> getStarshipsFromPages(@Parameter(description = "Number of pages to be consulted", required = true)
                                                                  @RequestParam(required = false) List<Integer> pages);

    @GetMapping(path = "/starshipById")
    @Operation(summary = "Get starship by Id", description = "Method that returns the starship by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_RESPONSE_OK, description = "Return a specific starship",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Character.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_INTERNAL_SERVER_ERROR, description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_BAD_REQUEST, description = "Bad Request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<Starship> getStarshipById(@Parameter(description = "Starship Id", required = true)
                                                            @RequestParam(required = false) Integer id);

    @GetMapping(path = "/starshipsByName")
    @Operation(summary = "Get starships by name", description = "Method that returns the starships that contain the name sent by parameter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_RESPONSE_OK, description = "Return one or more starships",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Character.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_INTERNAL_SERVER_ERROR, description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_BAD_REQUEST, description = "Bad Request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<List<Starship>> getStarshipsByName(@Parameter(description = "Starship name", required = true)
                                                                    @RequestParam(required = false) String name);
}
