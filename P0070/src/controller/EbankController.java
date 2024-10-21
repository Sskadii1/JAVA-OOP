/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

/**
 *
 * @author Admin
 */
public class EbankController {

    Inputter inputter = new Inputter();
    EbankView view = new EbankView();
    Locale locale;
    ResourceBundle bundle;

    
    
    
    
    public void execute() {
        while (true) {
            view.menu();
            int choice = inputter.getInt(1, 3);
            switch (choice) {
                case 1:
                    setLocale(new Locale.Builder().
                            setLanguage("Vi").setRegion("VN").build());
                    bundle = ResourceBundle.
                            getBundle("language.Vi", locale);
                    input();
                    break;
                    
                    
                    
                    
                    
                case 2:
                    setLocale(new Locale.Builder().
                            setLanguage("En").setRegion("US").build());
                    bundle = ResourceBundle.
                            getBundle("language/En", locale);
                    input();
                    break;
                case 3:
                    return;
            }
            inputter.displayNotice("");
        }
    }

    
    
    
    
    
    void input() {
        while (true) {
            String accountNumber = inputter.getString(bundle.getString("account"));
            String msg = checkAccountNumber(accountNumber);
            if (msg.isEmpty()) {
                break;
            }
            inputter.displayErr(msg);
        }

        
        
        
        while (true) {
            String password = inputter.getString(bundle.getString("password"));
            String msg = checkPassword(password);
            if (msg.isEmpty()) {
                break;
            }
            inputter.displayErr(msg);
        }

        
        
        
        while (true) {
            String captchaGenerate = generateCaptcha();
            inputter.displayNotice(bundle.getString("captcha") + captchaGenerate);            
            String captchaInput = inputter.getString(bundle.getString("captcha"));            
            String msg = checkCaptcha(captchaInput, captchaGenerate);            
            if (msg.isEmpty()) {
                break;
            }
            inputter.displayErr(msg);
        }
    }

    
    
    
    
    void setLocale(Locale locale) {
        this.locale = locale;
    }

    
    
    
    
    String checkAccountNumber(String accountNumber) {
        if (accountNumber.matches("^[0-9]{10}$")) {
            return "";
        }
        return bundle.getString("check.account");
    }

    
    
    
    
    
    String checkPassword(String password) {
        if (password.matches("^[0-9a-zA-Z]{8,31}$") 
                && password.matches(".*[a-zA-Z].*") 
                && password.matches(".*[0-9].*")) {
            return "";
        }
        return bundle.getString("wrong.password");        
    }
    
    
    
    
    
    String checkCaptcha(String captchaInput, String captchaGenerate) {
        if (captchaGenerate.contains(captchaInput)) {
            return "";
        }
        return bundle.getString("wrong.captcha");       
    }

    
    
    
    
    String generateCaptcha() {
        String s = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random r = new Random();
        String captcha = "";
        for (int i = 0; i < 5; i++) {
            int index = r.nextInt(s.length());
            captcha = captcha + s.charAt(index);
            s = s.substring(0, index) + s.substring(index + 1);
        }
        return captcha;
    }

}
