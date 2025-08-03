package EasyTransfer.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QRUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private long accountNum;

    private String password;
    private double balance;
    private LocalDate createdDate;
    private LocalTime time;

    @Enumerated(EnumType.STRING)
    private String role;

    @Enumerated(EnumType.STRING)
    private Set<Role> userRoles ;

    @Enumerated(EnumType.STRING)
    private String status;
}
//user
//accountNum, password, balance, date, status
//change password
//Reset password
//check balance
//check status
//
//admin
//update account only status update



