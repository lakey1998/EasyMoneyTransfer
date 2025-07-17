package EasyTransfer.demo.dao;

import EasyTransfer.demo.model.QRUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QRUserDao extends JpaRepository<QRUser, Integer> {

    @Query("UPDATE account a SET a.status= :status WHERE a.accountNumber = :accountNum")
    public default void updateAc(int accountNum, String status) {

    }

    @Query("SELECT accountNumber FROM account a WHERE a.Id = :userId")
    int findAccountNumber(int userId);
}
