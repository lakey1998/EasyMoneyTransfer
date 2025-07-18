package EasyTransfer.demo.dao;

import EasyTransfer.demo.model.transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface transferDao extends JpaRepository<transfer, Integer> {

    @Query("SELECT transferID FROM transfer t WHERE t.secretKey = :qrtext")
    int findId(String qrtext);
}
