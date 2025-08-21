package org.karankumarr.timetracker.timelog.controller;

import org.karankumarr.timetracker.common.ApiResponse;
import org.karankumarr.timetracker.timelog.dto.TimeLogRequest;
import org.karankumarr.timetracker.timelog.dto.TimeLogResponse;
import org.karankumarr.timetracker.timelog.service.TimeLogService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("api/v1/timelog")
public class TimeLogController {

    private final TimeLogService timeLogService;

    public TimeLogController(TimeLogService timeLogService) {
        this.timeLogService = timeLogService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TimeLogResponse>>> getTimeLogs(@RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size) {
        Page<TimeLogResponse> result = timeLogService.getTimeLogs(page, size);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Fetched successfully.",
                result.getContent(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements()
        ));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TimeLogResponse>> createTimeLog(@RequestBody TimeLogRequest timeLogRequest) {
        return ResponseEntity.ok(new ApiResponse<>(201, "Created.", timeLogService.createTimeLog(timeLogRequest)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<TimeLogResponse>> updateTimeLog(@PathVariable Integer id,
                                                                      @RequestBody TimeLogRequest timeLogRequest) {
        return ResponseEntity.ok(new ApiResponse<>(200, "Updated.", timeLogService.updateTimeLog(id, timeLogRequest)));
    }

}
