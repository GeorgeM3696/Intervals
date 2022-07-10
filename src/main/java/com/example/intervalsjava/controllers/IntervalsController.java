package com.example.intervalsjava.controllers;


import com.example.intervalsjava.models.Interval;
import com.example.intervalsjava.models.dto.IntervalDto;
import com.example.intervalsjava.services.IntervalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/intervals")
public class IntervalsController {

    private final IntervalService intervalService;

    public IntervalsController(IntervalService intervalService) {
        this.intervalService = intervalService;
    }

    @GetMapping("")
    public ResponseEntity<List<IntervalDto>> getAllIntervals() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(intervalService.getAllIntervals());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/all")
    public ResponseEntity<List<Interval>> addAllIntervals(@RequestBody List<Interval> intervalList) {
        try {
            intervalService.addAllIntervals(intervalList);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Interval> removeIntervalById(@PathVariable Long id) {
        try {
            intervalService.removeIntervalById(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NullPointerException nullPointerException) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<IntervalDto> getIntervalById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(intervalService.getIntervalById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("")
    public ResponseEntity<IntervalDto> addInterval(@RequestBody Interval interval) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(intervalService.addInterval(interval));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
