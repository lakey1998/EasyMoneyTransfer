package EasyTransfer.demo.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QRUserDTO {

    private long accountnumber;
    private String password;
    private LocalDate date;
    private LocalTime time;
    private String role;
    private String status;

}
