package lk.ijse.carRentalSystem.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseUtil implements Serializable {
    private String state;
    private String message;
    private Object data;
}
