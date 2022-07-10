package com.example.intervalsjava.mappers;

import com.example.intervalsjava.models.Interval;
import com.example.intervalsjava.models.dto.IntervalDto;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class IntervalMapper {

    public List<IntervalDto> map(List<Interval> source) {
        List<IntervalDto> intervalDtos = new LinkedList<>();

        if (source.isEmpty()) {
            return intervalDtos;
        }

        intervalDtos.add(createIntervalDtoFromInterval(source.get(0)));

        for (var i = 1; i < source.size(); i++) {
            var interval = source.get(i);
            var intervalDto = createIntervalDtoFromInterval(interval);

            intervalDto.setBreakDuration(getDuration(source.get(i - 1).getEnd(), interval.getStart()));
            intervalDtos.add(intervalDto);
        }

        return intervalDtos;
    }

    public IntervalDto createIntervalDtoFromInterval(Interval interval) {
        var intervalDto = new IntervalDto();

        intervalDto.setId(interval.getId());
        intervalDto.setEnd(interval.getEnd());
        intervalDto.setStart(interval.getStart());
        intervalDto.setDuration(getDuration(interval.getStart(), interval.getEnd()));

        return intervalDto;
    }

    private String getDuration(String startDate, String endDate) {
        var simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            var start = simpleDateFormat.parse(startDate);
            var end = simpleDateFormat.parse(endDate);

            return calculateDuration(start, end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String calculateDuration(Date start, Date end) {
        long duration = (end.getTime() - start.getTime()) / 1000;

        if (duration < 0) {
            duration = duration * -1;
        }

        int day = (int) TimeUnit.SECONDS.toDays(duration);
        long hours = TimeUnit.SECONDS.toHours(duration) - (day * 24);
        long minute = TimeUnit.SECONDS.toMinutes(duration) - (TimeUnit.SECONDS.toHours(duration) * 60);
        long second = TimeUnit.SECONDS.toSeconds(duration) - (TimeUnit.SECONDS.toMinutes(duration) * 60);

        return String.format("%sd %sh %sm %ss", day, hours, minute, second);
    }

}
