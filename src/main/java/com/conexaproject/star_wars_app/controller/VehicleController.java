package com.conexaproject.star_wars_app.controller;

import com.conexaproject.star_wars_app.model.BasicInformation;
import com.conexaproject.star_wars_app.model.ErrorResponse;
import com.conexaproject.star_wars_app.model.characters.Character;
import com.conexaproject.star_wars_app.model.vehicles.Vehicle;
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

public interface VehicleController {

    @GetMapping(path = "/vehicles", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all vehicles", description = "Method that returns all the vehicles in the Star Wars world")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_RESPONSE_OK, description = "Return de complete list of vehicles",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = BasicInformation.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_INTERNAL_SERVER_ERROR, description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))}
    )
    ResponseEntity<List<BasicInformation>> getAllVehicles();

    @GetMapping(path = "/vehicles-pages")
    @Operation(summary = "Get vehicles from specific pages", description = "Method that returns the vehicles listed on certain pages")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_RESPONSE_OK, description = "Return vehicles listed on certain pages",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = BasicInformation.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_INTERNAL_SERVER_ERROR, description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_BAD_REQUEST, description = "Bad Request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<List<BasicInformation>> getVehiclesFromPages(@Parameter(description = "Number of pages to be consulted", required = true)
                                                                  @RequestParam(required = false) List<Integer> pages);

    @GetMapping(path = "/vehiclesById")
    @Operation(summary = "Get vehicles by Id", description = "Method that returns a vehicle by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_RESPONSE_OK, description = "Return a specific vehicle",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_INTERNAL_SERVER_ERROR, description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_BAD_REQUEST, description = "Bad Request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<Vehicle> getVehicleById(@Parameter(description = "Vehicle Id", required = true)
                                                            @RequestParam(required = false) Integer id);

    @GetMapping(path = "/vehiclesByName")
    @Operation(summary = "Get vehicles by name", description = "method that returns the vehicles that contain the name sent by parameter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_RESPONSE_OK, description = "Return one or more vehicle",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Character.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_INTERNAL_SERVER_ERROR, description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = CODE_RESPONSE_BAD_REQUEST, description = "Bad Request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<List<Vehicle>> getVehiclesByName(@Parameter(description = "Vehicle name", required = true)
                                                                    @RequestParam(required = false) String name);
}
