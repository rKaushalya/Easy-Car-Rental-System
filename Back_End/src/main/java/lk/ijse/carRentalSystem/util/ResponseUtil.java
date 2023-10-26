package lk.ijse.carRentalSystem.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseUtil {
    private String state;
    private String message;
    private Object data;
}
