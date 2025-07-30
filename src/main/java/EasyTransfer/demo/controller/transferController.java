package EasyTransfer.demo.controller;


import EasyTransfer.demo.exception.apiException;
import EasyTransfer.demo.service.QRUserService;
import EasyTransfer.demo.service.transferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/transfer")
public class transferController {

    @Autowired
    private transferService transferService;

    @PostMapping("/receiveMoney")
    public ResponseEntity<?> recieveMoney() {
        try {
            transferService.generateQR();
            return new ResponseEntity<>(transferService.generateQR(),HttpStatus.OK);
        } catch (Exception e) {
            apiException error = new apiException(404,"QR generate incomplete!","transfer/sendMoney");
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

    @PostMapping("/confirmTansfer") //Second API for the sender to confirm transaction and Enter the amount and note
                         //Map the amount and the note with transfer ID that before api returned
    public ResponseEntity<?> confirmTransfer(@RequestBody Map<String,Object> payload){
        try {
            int transferId = (int) payload.get("transferId");
            double amount = (double) payload.get("amount");
            String note = payload.get("note").toString();

            String response=transferService.sendMoney(transferId,amount,note);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            apiException error = new apiException(404,"Transfer not completed","transfer/sendMoney");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}