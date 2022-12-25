package PruebaTecnica.BackendRaquelNeira.exception;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class CustomException extends RuntimeException {

    private Integer code;
    private HttpStatus status;
    private String message;

    public CustomException(Integer code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
