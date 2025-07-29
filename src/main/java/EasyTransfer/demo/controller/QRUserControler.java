package EasyTransfer.demo.controller;

import EasyTransfer.demo.exception.apiException;
import EasyTransfer.demo.model.QRUser;
import EasyTransfer.demo.model.QRUserProjection;
import EasyTransfer.demo.service.QRUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/QR")
public class QRUserControler {

    @Autowired
    private QRUserService service;

    @PostMapping("/user/register") // request using body and use the projection
    public ResponseEntity<?> test(@RequestParam long accountNum, String password){
        try {
            return new ResponseEntity<>(service.register(accountNum,password), HttpStatus.CREATED);
        }catch(Exception e){
            apiException error = new apiException(404,"Register Failed","/register/");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/admin/accounts")
    public ResponseEntity<?> viewAll(){
        try {
            return new ResponseEntity<>(service.findall(), HttpStatus.OK);
        }catch(Exception e){
            apiException error = new apiException(404," Not any account found","/accounts");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @GetMapping("/user/viewAcount")
    public ResponseEntity<?> stausUpdate(long accountNum){
        try {
            service.view(accountNum);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }catch(Exception e){
            apiException error = new apiException(404,"Account number not found","/AC/"+accountNum);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

    }

    @PostMapping("/admin/activateAccount")
    public ResponseEntity<?> activateAccount(long accountNum){
        try {
            service.activate(accountNum);
            return new ResponseEntity<>("Success",HttpStatus.OK);
        }catch(Exception e){
            apiException error = new apiException(404,"Account Not Found","/activeAccount/" + accountNum);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
    }
