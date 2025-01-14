package com.conexaproject.star_wars_app.controller;

import com.conexaproject.star_wars_app.model.BasicInformation;
import com.conexaproject.star_wars_app.model.ErrorResponse;
import com.conexaproject.star_wars_app.model.characters.Character;
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

public interface PeopleController {

    @GetMapping(path = "/characters", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all characters", description = "Method that returns all the characters in the Star Wars world")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_RESPONSE_OK, description = "Return de complete list of characters",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = BasicInformation.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_INTERNAL_SERVER_ERROR, description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))}
    )
    ResponseEntity<List<BasicInformation>> getAllCharacters();

    @GetMapping(path = "/characters-pages")
    @Operation(summary = "Get characters from specific pages", description = "Method that returns the characters listed on certain pages")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_RESPONSE_OK, description = "Return characters listed on certain pages",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = BasicInformation.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_INTERNAL_SERVER_ERROR, description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_BAD_REQUEST, description = "Bad Request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<List<BasicInformation>> getCharactersFromPages(@Parameter(description = "Number of pages to be consulted", required = true)
                                                                  @RequestParam(required = false) List<Integer> pages);

    @GetMapping(path = "/characterById")
    @Operation(summary = "Get characters by Id", description = "Method that returns the characters by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_RESPONSE_OK, description = "Return a specific character",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Character.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_INTERNAL_SERVER_ERROR, description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_BAD_REQUEST, description = "Bad Request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<Character> getCharacterById(@Parameter(description = "Character Id", required = true)
                                                            @RequestParam(required = false) Integer id);

    @GetMapping(path = "/charactersByName")
    @Operation(summary = "Get characters by name", description = "method that returns the characters that contain the name sent by parameter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_RESPONSE_OK, description = "Return one or more characters",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Character.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_INTERNAL_SERVER_ERROR, description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_BAD_REQUEST, description = "Bad Request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<List<Character>> getCharactersByName(@Parameter(description = "Character name", required = true)
                                                                    @RequestParam(required = false) String name);
}
