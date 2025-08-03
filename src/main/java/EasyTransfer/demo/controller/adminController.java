package EasyTransfer.demo.controller;

import EasyTransfer.demo.exception.apiException;
import EasyTransfer.demo.model.transfer;
import EasyTransfer.demo.service.QRUserService;
import EasyTransfer.demo.service.transferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private QRUserService userService;

    @Autowired
    private transferService transferService;


    @GetMapping("/accounts")
    public ResponseEntity<?> viewAll() {
        try {
            return new ResponseEntity<>(userService.findall(), HttpStatus.OK);
        } catch (Exception e) {
            apiException error = new apiException(404, " Not any account found", "/accounts");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @GetMapping("/viewAcount")
    public ResponseEntity<?> stausUpdate(long accountNum) {
        try {
            userService.adminView(accountNum);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            apiException error = new apiException(404, "Account number not found", "/AC/");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

    }

    @PostMapping("/updateAccount")
    public ResponseEntity<?> activateAccount(long accountNum, String status) {
        try {
            userService.activate(accountNum, status);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            apiException error = new apiException(404, "Account Not Found", "/activeAccount/");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/transfer/History")
    public ResponseEntity<?> History() {
        try {
            return new ResponseEntity<>(transferService.History(), HttpStatus.OK);
        } catch (Exception e) {
            apiException error = new apiException(404, "No transaction history found", "transfer/history");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @GetMapping("/transfer/History/account")
    public ResponseEntity<?> History(@RequestParam long accountNum) {
        try {
            return new ResponseEntity<>(transferService.History(accountNum), HttpStatus.OK);
        } catch (Exception e) {
            apiException error = new apiException(404, "No transaction data found", "transfer/history/accountNum");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

        }
    }
}

//admin
//update account only status update
//view account

//admin see all transaction but can't change
//

