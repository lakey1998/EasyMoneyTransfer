package EasyTransfer.demo.service;

import EasyTransfer.demo.dao.QRUserDao;
import EasyTransfer.demo.model.QRUser;
import EasyTransfer.demo.model.QRUserProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class QRUserService {

   //private BCryptPasswordEncoder encorder = new BCryptPasswordEncoder(12);

    @Autowired
    private QRUserDao repo;

    public String register(long accountNum, String password) {
        QRUser account = new QRUser();
        account.setAccountNum(accountNum);
       // account.setPassword(password);
        account.setPassword(encorder.encode(password);
        account.setDate(LocalDate.now());
        account.setTime(LocalTime.now());
        account.setStatus("Active");
        account.setAmount(10000); //amount is need to set from the account
        repo.save(account);
        return "Success";
    }

    public void update(int accountNum, String status) {
        repo.updateAc(accountNum, status);
    }

    public List<QRUser> findall() {
        return repo.findAll();
    }

    public QRUser getUserById(int id) {
        return this.repo.findById((int) id).get();
    }

    public QRUser view(long accountNum) {
        return repo.findByAccountNum(accountNum);
    }

    public QRUser activate(long accountNum) {
        return repo.activateAccount(accountNum);
    }

    public boolean isActive(long accountNum){
        String result = String.valueOf(repo.isActive(accountNum));
        return result == "Active";

    }

    public double findAmount(long sender) {
        return repo.findAmountByAccountNum(sender);
    }


    public void updateAmount(long sender, double lastAmount) {
        repo.updateAmount(sender, lastAmount);
    }
}

