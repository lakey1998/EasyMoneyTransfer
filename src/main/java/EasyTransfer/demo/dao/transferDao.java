package EasyTransfer.demo.dao;

import EasyTransfer.demo.model.transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface transferDao extends JpaRepository<transfer, Integer> {
}
