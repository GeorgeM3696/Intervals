package com.example.intervalsjava.repositories;

import com.example.intervalsjava.models.Interval;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class IntervalRepository {
    private static final ConcurrentHashMap<Long, Interval> intervalsStorage = new ConcurrentHashMap<>();

    public Interval add(Interval interval) {
        intervalsStorage.put(interval.getId(), interval);
        return interval;
    }

    public void addAll(List<Interval> intervalList) {
        intervalList.forEach(interval -> intervalsStorage.put(interval.getId(), interval));
    }

    public Interval deleteById(Long id) {
        return intervalsStorage.remove(id);
    }

    public Interval getIntervalById(Long id) {
        return intervalsStorage.get(id);
    }

    public List<Interval> getAllIntervals() {
        return new ArrayList<>(intervalsStorage.values());
    }



}
