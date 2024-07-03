package med.voll.api.infra.execptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {


    private record ResponseError400(String campo, String message){
        public ResponseError400(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity Error404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity Error400(MethodArgumentNotValidException exception){

        var error = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(error.stream().map(ResponseError400::new).toList());
    }


}
