package com.capyjella.capydate.config.http;

import com.capyjella.capydate.common.exceptions.IncorrectDataException;
import com.capyjella.capydate.common.exceptions.OperationNotPermitedException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IncorrectDataException.class)
    public ResponseEntity<?> handleIncorrectDataException(IncorrectDataException ex) {
        logger.info(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(OperationNotPermitedException.class)
    public ResponseEntity<?> handleOperationNotPermitedException(OperationNotPermitedException ex) {
        logger.info(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex) {
        logger.info(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        ex.printStackTrace();
        logger.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }
}
