package Med.Voll.Api_Rest.Infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandle {


    public record ResponseErrorArgumentNotValid(String campo, String message){
        public ResponseErrorArgumentNotValid(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity ErrorNotFound(){
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity ErrorArgumentNotValid(MethodArgumentNotValidException exception){

        var error = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(error.stream().map(ResponseErrorArgumentNotValid::new).toList());
    }
}
