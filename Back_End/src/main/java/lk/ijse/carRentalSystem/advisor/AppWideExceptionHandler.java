package lk.ijse.carRentalSystem.advisor;

import lk.ijse.carRentalSystem.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@CrossOrigin
public class AppWideExceptionHandler {
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseUtil handleException(RuntimeException e) {
        System.out.println(e.getMessage());
        return new ResponseUtil("Error",e.getMessage(),"");
    }
}
