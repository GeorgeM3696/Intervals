package com.example.intervalsjava.services;

import com.example.intervalsjava.mappers.IntervalMapper;
import com.example.intervalsjava.models.Interval;
import com.example.intervalsjava.models.dto.IntervalDto;
import com.example.intervalsjava.repositories.IntervalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntervalService {

    private final IntervalRepository intervalRepository;
    private final IntervalMapper intervalMapper;


    public IntervalService(IntervalRepository intervalRepository, IntervalMapper intervalMapper) {

        this.intervalRepository = intervalRepository;
        this.intervalMapper = intervalMapper;
    }

    public List<IntervalDto> getAllIntervals() {
        return intervalMapper.map(intervalRepository.getAllIntervals());
    }

    public IntervalDto removeIntervalById(Long id) {
        return intervalMapper.createIntervalDtoFromInterval(intervalRepository.deleteById(id));
    }

    public IntervalDto getIntervalById(Long id) {
        return intervalMapper.createIntervalDtoFromInterval(intervalRepository.getIntervalById(id));
    }

    public void addAllIntervals(List<Interval> intervalList) {
        intervalRepository.addAll(intervalList);
    }

    public IntervalDto addInterval(Interval interval) {
        return intervalMapper.createIntervalDtoFromInterval(intervalRepository.add(interval));
    }





}
