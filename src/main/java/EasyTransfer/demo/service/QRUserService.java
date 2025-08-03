package EasyTransfer.demo.service;

import EasyTransfer.demo.dao.QRUserDao;
import EasyTransfer.demo.model.QRUser;
import EasyTransfer.demo.model.accountStatus;
import EasyTransfer.demo.model.Role;
import EasyTransfer.demo.model.userPrincipal;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class QRUserService {

   private final BCryptPasswordEncoder encorder = new BCryptPasswordEncoder(12);

    @Autowired
    private static QRUserDao repo;

    @Autowired
    private static userPrincipal userPrincipal;

    @Enumerated(EnumType.STRING)
    private accountStatus accountStatus;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static double checkBalance() {
        long accountNum = Long.parseLong(userPrincipal.getUsername());
        return repo.findBalanceById(accountNum);
    }

    public String register(long accountNum, String password) {
        QRUser account = new QRUser();
        account.setAccountNum(accountNum);
        //account.setPassword(password);
        account.setPassword(encorder.encode(password));
        account.setCreatedDate(LocalDate.now());
        account.setTime(LocalTime.now());
        account.setStatus(String.valueOf(accountStatus.ACTIVE));
        account.setBalance(10000); //amount is need to set from the account
        account.setRole(String.valueOf(role.USER));
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
        return this.repo.findById(id).get();
    }

    public QRUser adminView(long accountNum) {
        return repo.findByAccountNum(accountNum);
    }
    public QRUser userView() {
        long accountNum = Long.parseLong(userPrincipal.getUsername());
        return repo.findByAccountNum(accountNum);
    }

    public QRUser activate(long accountNum, String status) {
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

    public void changePassword(String oldPassword, String newPasword) {
        long accountNum = Long.parseLong(userPrincipal.getUsername());
        String password = repo.getPassword(accountNum);
        //encode password look
    }
}

