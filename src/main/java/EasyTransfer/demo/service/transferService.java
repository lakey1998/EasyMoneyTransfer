package EasyTransfer.demo.service;

import EasyTransfer.demo.dao.transferDao;
import EasyTransfer.demo.model.transfer;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.image.BufferedImage;

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


    public BufferedImage generateQR() throws Exception {
        int passLength = 12 ;           //Initialize the onetime verification password here
        int Id = 0 ;
        int accno = user.getAccountNum(Id);
        String secret = randomPass.generatePassword(passLength);

        transfer newTransfer = new transfer();
        newTransfer.setRecieverAc(accno);
        newTransfer.setSecretKey(secret);
        newTransfer.setDate(null);
        newTransfer.setTime(null);
        newTransfer.setSenderAc(00);
        newTransfer.setAmount(00);
        newTransfer.setStatus("Pending");

        dao.save(newTransfer);

        return QR.createQR(secret);

    }

    public String transfer(String qrtext){
        int senderId = 01 ;
        int tempId = dao.findId(qrtext);

        int sederac = user.getAccountNum(senderId);
       // int amount =

        return "Success";
    }

    //public getDetails(@RequestBody )
}
