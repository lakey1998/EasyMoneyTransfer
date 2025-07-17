package EasyTransfer.demo.service;

import EasyTransfer.demo.dao.QRUserDao;
import EasyTransfer.demo.model.QRUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class QRUserService {

    @Autowired
    private QRUserDao repo;


    public String register(int accountnum) {
        QRUser account = new QRUser();
        account.setAccountnumber(accountnum);
        account.setDate(LocalDate.now());
        account.setTime(LocalTime.now());
        account.setStatus("Active");
        repo.save(account);
        return "Success";
    }

    public void update(int accountNum, String status) {
        repo.updateAc(accountNum, status);
    }

    public List<QRUser> findall() {
        return repo.findAll();
    }

    public int getAccountNum(int userId) {
        return repo.findAccountNumber(userId);
    }
}

