package com.conexaproject.star_wars_app.dto;

import com.conexaproject.star_wars_app.dto.BasicData;
import com.conexaproject.star_wars_app.dto.BasicResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BasicDataResponse extends BasicResponse {

    @JsonProperty("total_records")
    private String totalRecords;
    @JsonProperty("total_pages")
    private String totalPages;
    private String previous;
    private String next;
    private List<BasicData> results;

    public BasicDataResponse(String message, String totalRecords, String totalPages, String previous, String next, List<BasicData> results) {
        super(message);
        this.totalRecords = totalRecords;
        this.totalPages = totalPages;
        this.previous = previous;
        this.next = next;
        this.results = results;
    }

    public BasicDataResponse() {
    }
}
