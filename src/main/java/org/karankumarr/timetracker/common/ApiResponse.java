package org.karankumarr.timetracker.common;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private final int status;
    private final String message;

    private final T data; // nullable (e.g -> delete route)
    private final Integer page;   // nullable
    private final Integer size;  // nullable
    private final Long total;     // nullable

    public ApiResponse(int status, String message) {
        this(status, message, null, null, null, null);
    }

    public ApiResponse(int status, String message, T data) {
        this(status, message, data, null, null, null);
    }

    public ApiResponse(int status, String message, T data, Integer page, Integer size, Long total) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.page = page;
        this.size = size;
        this.total = total;
    }

}
