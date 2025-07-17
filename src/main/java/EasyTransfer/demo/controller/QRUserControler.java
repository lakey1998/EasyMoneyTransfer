package EasyTransfer.demo.controller;

import EasyTransfer.demo.model.QRUser;
import EasyTransfer.demo.service.QRUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/QR")
public class QRUserControler {

    @Autowired
    private QRUserService service;

    @PostMapping("/register")
    public int test(int accountnum){
        return Integer.parseInt(service.register(accountnum));
    }

    @GetMapping("/accounts")
    public List<QRUser> viewAll(){
        return service.findall();
    }

    @PostMapping("/status")
    public String stausUpdate(int accountNum, String status){
         service.update(accountNum, status);
        return "Success";
    }

    public int getAccountNumber(int userId){
       return service.getAccountNum(userId);

    }

    }
