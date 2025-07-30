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

    @Query("SELECT * FROM QRUser a WHERE a.accountNum = :accountNum")
    QRUser findByAccountNum(long accountNum);

    @Query("UPDATE QRUser a SET 'active' WHERE a.accountNum = :accountNum")
    QRUser activateAccount(long accountNum);

    @Query("SELECT a.status FROM QRUser a WHERE a.accountNum=:accountNum")
    QRUser isActive(long accountNum);

    @Query("SELECT amount FROM QRUser a WHERE a.accountNum = :accountNum")
    double findAmountByAccountNum(long accountNum);

    @Query("UPDATE QRUser b SET b.amount = :lastAmount WHERE b.accountNum = :accountNum ")
    void updateAmount(long accountNum, double lastAmount);
}
