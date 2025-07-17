package EasyTransfer.demo.controller;


import EasyTransfer.demo.service.QRUserService;
import EasyTransfer.demo.service.transferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/transfer")
public class transferController {

    @Autowired
    private transferService transferService;

    @PostMapping("/receiveMoney")
    public BufferedImage recieveMoney()throws Exception{
        return transferService.generateQR();
    }

}
