package com.example.demo.controller;



import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;



@Component
public class MailTest02Application {

    private final MailSender mailSender;

    
    public MailTest02Application(MailSender mailSender) {
        this.mailSender = mailSender;

    }



    public void sendMail(String toMail,String title,String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("matcheatsinfo@gmail.com"); // 送信元メールアドレス
        mailMessage.setTo(toMail);//送信先メールアドレス
       // mailMessage.setCc(/* ccに入れるメールアドレス */);
       // mailMessage.setBcc(/* bccに入れるメールアドレス */);
        mailMessage.setSubject(title);//タイトル
        mailMessage.setText(message);//本文



        try {
            mailSender.send(mailMessage);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}