package EasyTransfer.demo.controller;

import EasyTransfer.demo.exception.apiException;
import EasyTransfer.demo.model.transfer;
import EasyTransfer.demo.service.QRUserService;
import EasyTransfer.demo.service.transferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private QRUserService userService;

    @Autowired
    private transferService transferService;

    @PostMapping("/register") // request using body and use the projection
    public ResponseEntity<?> test(@RequestParam long accountNum, String password){
        try {
            return new ResponseEntity<>(userService.register(accountNum,password), HttpStatus.CREATED);
        }catch(Exception e){
            apiException error = new apiException(404,"Register Failed","/register/");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/viewAcount")
    public ResponseEntity<?> stausUpdate(){
        try {
            userService.userView();
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }catch(Exception e){
            apiException error = new apiException(404,"Account number not found","/AC/");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

    }

    @PostMapping("/receiveMoney")
    public ResponseEntity<?> recieveMoney() {
        try {
            transferService.generateQR();
            return new ResponseEntity<>(transferService.generateQR(), HttpStatus.OK);
        } catch (Exception e) {
            apiException error = new apiException(404, "QR generate incomplete!", "transfer/sendMoney");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping("/sendMoney")
    public ResponseEntity<?> readQR(@RequestBody Map<String, String> payload) { //QR code reader from the front end
        String qrtext = payload.get("qrtext");

        try {
            int transferId = transferService.validatSecret(qrtext);

            HashMap<String, Object> response = new HashMap<>();
            response.put("status", "valid");
            response.put("transferId", transferId);
            response.put("message", "QR Validated! Enter the amount and the note");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            apiException error = new apiException(404, "QR code not found", "/transfer/sendMoney");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PostMapping("/confirmTransfer")
    //Second API for the sender to confirm transaction and Enter the amount and note
    //Map the amount and the note with transfer ID that before api returned
    public ResponseEntity<?> confirmTransfer(@RequestBody Map<String, Object> payload) {
        try {
            int transferId = (int) payload.get("transferId");
            double amount = (double) payload.get("amount");
            String note = payload.get("note").toString();

            String response = transferService.sendMoney(transferId, amount, note);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            apiException error = new apiException(404, "Transfer not completed", "transfer/sendMoney");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/history/receive")
    public ResponseEntity<?> receiveHistory() {
        try {
            return new ResponseEntity<>(transferService.recieveHistory(),HttpStatus.OK);
        }catch(Exception e){
            apiException error = new apiException(404,"No Data Found","/history/receieve");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @GetMapping("/history/send")
    public ResponseEntity<?> sendHistory(){
        try {
            return new ResponseEntity<>(transferService.sendHistory(),HttpStatus.OK);
        }catch(Exception e){
            apiException error = new apiException(404,"No Data Found","history/send");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }


    @GetMapping("/checkBalance")
    public ResponseEntity<?> checkBalance(){
        try{
            return new ResponseEntity<>(QRUserService.checkBalance(), HttpStatus.OK);
        }catch(Exception e){
            apiException error = new apiException(404,"Bad Request","/accountBalance");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }


}

//user
//accountNum, password, balance, date, status dto
//change password
//Reset password
//check balance
//check status

//user
//sender, receiver, amount, note, date, time, status
  //        only there transactions