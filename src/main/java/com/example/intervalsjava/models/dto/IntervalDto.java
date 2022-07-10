package com.example.intervalsjava.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IntervalDto {

    private Long id;

    private String start;

    private String end;

    private String duration;

    @JsonProperty("break")
    private String breakDuration;
}
