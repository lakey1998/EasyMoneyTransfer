package EasyTransfer.demo.service;

import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class transferService {

    @Autowired
    private randomPasswordGenerator randomPass;

    @Autowired
    private QRgenerator QR;

    public BufferedImage generateQR() throws Exception {
        int passLength = 12 ;       //Initialize the onetime verification password here

       return QR.createQR(randomPass.generatePassword(passLength));

    }
}
