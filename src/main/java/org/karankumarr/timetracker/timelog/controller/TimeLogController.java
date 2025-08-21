package org.karankumarr.timetracker.timelog.controller;

import org.karankumarr.timetracker.common.ApiResponse;
import org.karankumarr.timetracker.timelog.dto.TimeLogRequest;
import org.karankumarr.timetracker.timelog.dto.TimeLogResponse;
import org.karankumarr.timetracker.timelog.service.TimeLogService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/timelog")
public class TimeLogController {

    private final TimeLogService timeLogService;

    public TimeLogController(TimeLogService timeLogService) {
        this.timeLogService = timeLogService;
    }

    @GetMapping
    public  ApiResponse<List<TimeLogResponse>> getTimeLogs(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        Page<TimeLogResponse> result = timeLogService.getTimeLogs(page, size);

        return new ApiResponse<>(
                200,
                "Fetched successfully",
                result.getContent(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements()
        );
    }

    @PostMapping
    public ApiResponse<TimeLogResponse> createTimeLog(@RequestBody TimeLogRequest timeLogRequest) {
        return new ApiResponse<>(200, "Created.", timeLogService.createTimeLog(timeLogRequest));
    }

}
