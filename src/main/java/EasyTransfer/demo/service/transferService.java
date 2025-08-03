package EasyTransfer.demo.service;

import EasyTransfer.demo.dao.transferDao;
import EasyTransfer.demo.model.transfer;
import EasyTransfer.demo.model.userPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
        int passLength = 12;           //Initialize the onetime verification password length here
        String secret = randomPass.generatePassword(passLength);

        long receiver = Long.parseLong(userPrincipal.getUsername());

        if (user.isActive(receiver)) {
            transfer newTransfer = new transfer();
            newTransfer.setSecretKey(secret);

            newTransfer.setReceiver(Long.parseLong(userPrincipal.getUsername()));
            newTransfer.setSender(00);
            newTransfer.setDate(null);
            newTransfer.setTime(null);
            newTransfer.setAmount(00);
            newTransfer.setStatus("Pending");

            dao.save(newTransfer);
            return QR.createQR(secret);

        }else throw new Exception("Your account is not at active status");
    }

    public int validatSecret(String secretKey){
        return dao.findId(secretKey);
        }

    public String sendMoney(int transferId, double amount, String note) {

        int transferID = transferId;
        long sender = Long.parseLong(userPrincipal.getUsername());
        double lastAmount =amount;
        String lastnote = note;
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String status = "Success";

        if (user.isActive(sender)) {
            double senderAmount = user.findAmount(sender);
            if (senderAmount > lastAmount) {
                double newSenderAmount = senderAmount - lastAmount;
                user.updateAmount(sender, lastAmount);          //Update sender amount

                long receiverAccount = dao.findReceiverById(transferID);
                double receiverAmount = user.findAmount(receiverAccount);
                double newReceiverAmount = receiverAmount + lastAmount;
                user.updateAmount(receiverAccount, newReceiverAmount);       //Update receiver amount

                dao.update(transferID, sender, lastAmount, note, date, time, status);
                return "Transfer complete Successful";
            } else
                return "Insufficient Account Balance";
        }else
            return "Your account is not at active status";

    }

    public List<transfer> recieveHistory() {
        long accountNum= Long.parseLong(userPrincipal.getUsername());
        return dao.findReceive(accountNum);
    }

    public List<transfer> sendHistory() {
        long accountNum= Long.parseLong(userPrincipal.getUsername());
        return dao.findSend(accountNum);
    }

    public List<transfer> History() {
        return dao.History();
    }

    public List<transfer> History(long accountNum) {
        return dao.History(accountNum);
    }
}



