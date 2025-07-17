package EasyTransfer.demo.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.image.BufferedImage;
import java.util.Map;

@Service
public class QRgenerator {

    public BufferedImage createQR(String str) throws Exception{
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(str, BarcodeFormat.QR_CODE, 200,200);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public String readQR(@RequestBody Map<String, String> payload){
        String qrtext = payload.get("qrtext");
        return qrtext;
    }
}
