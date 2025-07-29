package EasyTransfer.demo.service;

import EasyTransfer.demo.dao.transferDao;
import EasyTransfer.demo.exception.apiException;
import EasyTransfer.demo.model.transfer;
import EasyTransfer.demo.model.userPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class transferService {

    @Autowired
    private randomPasswordGenerator randomPass;

    @Autowired
    private QRgenerator QR;

    @Autowired
    private QRUserService user;

    @Autowired
    private transferDao dao;

    @Autowired
    private userPrincipal userPrincipal;


    public BufferedImage generateQR() throws Exception {
        int passLength = 12 ;           //Initialize the onetime verification password length here
        String secret = randomPass.generatePassword(passLength);

        long receiver = Long.parseLong(userPrincipal.getUsername());

        transfer newTransfer = new transfer();
        newTransfer.setSecretKey(secret);

        newTransfer.setReciever(Long.parseLong(userPrincipal.getUsername()));
        newTransfer.setSender(00);
        newTransfer.setDate(null);
        newTransfer.setTime(null);
        newTransfer.setAmount(00);
        newTransfer.setStatus("Pending");

        dao.save(newTransfer);
        return QR.createQR(secret);

    }

    public int validatSecret(String secretKey){
        return dao.findId(secretKey);     }

    public void sendMoney(int transferId, double amount, String note) {
        int transferID = transferId;
        long sender = Long.parseLong(userPrincipal.getUsername());
        double lastAmount =amount;
        String lastnote = note;
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String status = "Success";
         dao.update(transferID, sender, lastAmount, note, date, time, status);
    }
}



