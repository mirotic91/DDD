package com.mirotic91.demo.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "C-001", "Invalid Input Value"),
    INVALID_TYPE_VALUE(400, "C-002", "Invalid Type Value"),
    ENTITY_NOT_FOUND(400, "C-003", "Entity Not Found"),
    HANDLE_ACCESS_DENIED(403, "C-004", "Access is Denied"),
    METHOD_NOT_ALLOWED(405, "C-005", "Method Not Allowed"),
    INTERNAL_SERVER_ERROR(500, "C-006", "Server Error"),

    MEMBER_NOT_FOUND(400, "M-001", "Member Not Found"),
    PASSWORD_NOT_MATCHED(400, "M-002", "Password Not Matched"),
    EMAIL_DUPLICATED(400, "M-003", "Email is Duplicated"),

    ORDER_NOT_FOUND(400, "O-001", "Order Not Found");

    private final int status;

    private final String code;

    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}