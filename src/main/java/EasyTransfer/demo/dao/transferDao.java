package EasyTransfer.demo.dao;

import EasyTransfer.demo.model.transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface transferDao extends JpaRepository<transfer, Integer> {

    @Query("SELECT transferID FROM transfer t WHERE t.secretKey = :qrText")
    int findId(String qrText);

    @Query("UPDATE transfer t SET t.sender = :sender, t.amount = :lastAmount, t.note =:note, t.date = :date, t.time = :time, t.status = :status WHERE t.transferId = :transferId")
    String update(int transferID, long sender, double lastAmount, String note, LocalDate date, LocalTime time, String status);

    @Query("SELECT receiver FROM transfer a WHERE a.transferID = :transferID")
    int findReceiverById(int transferID);
}
