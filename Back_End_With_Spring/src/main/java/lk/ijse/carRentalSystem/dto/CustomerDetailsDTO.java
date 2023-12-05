package lk.ijse.carRentalSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDetailsDTO {
    private String nicNo;
    private String fileName;
    private String filePath;
    private String fileType;
}
