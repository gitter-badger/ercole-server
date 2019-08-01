package io.ercole.config;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * A class that handle all exception.
 */
@RestControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private static Log loger = LogFactory.getLog(RestResponseExceptionHandler.class);
    /**
     * This method handle SQL/databases errors.
     * @param ex the exception
     * @return the response entity
     */
    @ExceptionHandler({ SQLException.class, InvalidDataAccessResourceUsageException.class})
    public ResponseEntity<Object> exception(final Exception ex) {
        loger.error("", ex);
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in the database");
    }
}
