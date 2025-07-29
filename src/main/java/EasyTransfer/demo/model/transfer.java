package EasyTransfer.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transferID;
    private long sender;
    private long reciever;
    private double amount;
    private String note;
    private String secretKey;
    private LocalDate date;
    private LocalTime time;
    private String status;


}
