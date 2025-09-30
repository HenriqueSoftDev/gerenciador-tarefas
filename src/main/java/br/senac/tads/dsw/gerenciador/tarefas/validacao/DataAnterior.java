package br.senac.tads.dsw.gerenciador.tarefas.validacao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.FieldError;

@ControllerAdvice
public class DataAnterior {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        FieldError fieldError = ex.getBindingResult().getFieldError();
        String mensagem = "Erro de validação";

        if (fieldError != null) {
            if ("dataTermino".equals(fieldError.getField())) {
                mensagem = "A data de término deve ser hoje ou uma data futura.";
            } else {
                mensagem = fieldError.getDefaultMessage();
            }
        }

        return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    }
}
