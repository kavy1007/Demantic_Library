/*
 * Copyright (C) Lowe's Companies, Inc. All rights reserved.
 * This file is for internal use only at Lowe's Companies, Inc.
 */
package com.dematic.books.controller;

import lombok.Getter;

import java.time.OffsetDateTime;


@Getter
public class ApiError {

    private OffsetDateTime timestamp;
    private String message;
    private String desc;

    ApiError(String message, String desc) {

        this.message = message;
        this.desc = desc;
        this.timestamp = OffsetDateTime.now();
    }
}
