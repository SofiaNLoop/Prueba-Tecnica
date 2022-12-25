package PruebaTecnica.BackendRaquelNeira.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;


@RestControllerAdvice
public class ExceptionManager {


    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<CustomException> BusinessExceptionHandler(CustomException ex){
        CustomException error = new CustomException(ex.getCode(), ex.getStatus(), ex.getMessage());
        return new ResponseEntity(error.getMessage(), ex.getStatus());
    }
}
