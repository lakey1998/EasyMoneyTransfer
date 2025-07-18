package EasyTransfer.demo.controller;


import EasyTransfer.demo.service.QRUserService;
import EasyTransfer.demo.service.transferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.util.Map;

@RestController
@RequestMapping("/transfer")
public class transferController {

    @Autowired
    private transferService transferService;

    @PostMapping("/receiveMoney")
    public BufferedImage recieveMoney()throws Exception{
        return transferService.generateQR();
    }

    @PostMapping("/sendMoney")
    public String readQR(@RequestBody Map<String, String> payload) {
        String qrtext = payload.get("qrtext");
        return transferService.transfer(qrtext);
    }

}
