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



        this.sendMail();
    }



    public void sendMail() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom("aso1801177@gmail.com"); // 送信元メールアドレス
//        mailMessage.setTo("1801148@s.asojuku.ac.jp");//送信先メールアドレス
       // mailMessage.setCc(/* ccに入れるメールアドレス */);
       // mailMessage.setBcc(/* bccに入れるメールアドレス */);
        mailMessage.setSubject("テスト用表題");//タイトル
        mailMessage.setText("ローカルから送る用テストメッセージ");//本文



        try {
            mailSender.send(mailMessage);
        } catch (MailException e) {
            // TODO: エラー処理
        }
    }
}