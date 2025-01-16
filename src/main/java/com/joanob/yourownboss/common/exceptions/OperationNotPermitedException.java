package com.joanob.yourownboss.common.exceptions;

public class OperationNotPermitedException extends RuntimeException {
    public OperationNotPermitedException(String message) {
        super(message);
    }
}
