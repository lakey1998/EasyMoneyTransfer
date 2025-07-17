package EasyTransfer.demo.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class randomPasswordGenerator {

    public String generatePassword(int len){
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String nums = "0123456789";
        String specialChars = "<>?,./{}[]!@#$%^&*()_+";
        String combination = upper+lower+specialChars+nums;

        char[] password = new char[len];

        Random random = new Random();
        for(int i = 0; i < password.length; i++){
            password[i]=combination.charAt(random.nextInt(combination.length()));
        }

        String key = String.valueOf(password);
        return key;

    }
}
