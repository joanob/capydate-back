package com.capyjella.capydate.common.exceptions;

public class OperationNotPermitedException extends RuntimeException {
    public OperationNotPermitedException(String message) {
        super(message);
    }
}
