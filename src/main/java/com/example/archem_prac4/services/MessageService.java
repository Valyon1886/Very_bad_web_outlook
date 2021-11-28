package com.example.archem_prac4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.archem_prac4.models.Message;
import com.example.archem_prac4.models.User;
import com.example.archem_prac4.repos.MessageRepo;
import org.springframework.ui.Model;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserService userService;

    //@Transactional(readOnly = true)
    public Properties setEmailAdress() {
        Properties properties = new Properties();
        User u1 = userService.getUser();
        String d = u1.getEmails().split("@")[1];
        if (d.equals("yandex.ru")) {
            properties.put("mail.user", u1.getEmails());//"Test6234@yandex.ru");
            properties.put("mail.password", u1.getE_secret());//"eaaxzxpvesyyifyj");
            properties.put("mail.host", "imap.yandex.ru");
            properties.put("mail.transport.protocol", "smtps");
            properties.put("mail.smtp.host", "smtp.yandex.ru");
            //Требуется ли аутентификация для отправки сообщения
            properties.put("mail.smtp.auth", "true");
            //Порт для установки соединения
            properties.put("mail.smtp.socketFactory.port", "465");
            //Фабрика сокетов, так как при отправке сообщения Yandex требует SSL-соединения
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        else if(d.equals("gmail.com")){
            properties.put("mail.user", u1.getEmails());//"Test6234@yandex.ru");
            properties.put("mail.password", u1.getE_secret());//"eaaxzxpvesyyifyj");
            properties.put("mail.host", "imap.gmail.com");
            properties.put("mail.transport.protocol", "smtps");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            //Требуется ли аутентификация для отправки сообщения
            properties.put("mail.smtp.auth", "true");
            //Порт для установки соединения
            properties.put("mail.smtp.socketFactory.port", "465");
            //Фабрика сокетов, так как при отправке сообщения Yandex требует SSL-соединения
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        else if(d.equals("mail.ru")){
            properties.put("mail.user", u1.getEmails());//"Test6234@yandex.ru");
            properties.put("mail.password", u1.getE_secret());//"daZMFC6XEyP2b6Kh8qCa");
            properties.put("mail.host", "imap.mail.ru");
            properties.put("mail.transport.protocol", "smtps");
            properties.put("mail.smtp.host", "smtp.mail.ru");
            //Требуется ли аутентификация для отправки сообщения
            properties.put("mail.smtp.auth", "true");
            //Порт для установки соединения
            properties.put("mail.smtp.socketFactory.port", "465");
            //Фабрика сокетов, так как при отправке сообщения Yandex требует SSL-соединения
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }

        return properties;
    }

    @Transactional(readOnly = true)
    public Model getMessageList(Model model, String method, Long id) throws MessagingException, IOException {
        Properties properties = setEmailAdress();
        String user1 = properties.getProperty("mail.user");
        String password = properties.getProperty("mail.password");
        String host = properties.getProperty("mail.host");

        Properties prop = new Properties();

        prop.put("mail.store.protocol"  , "imaps"); //ssl
        try {
        Store store = Session.getInstance(prop).getStore();

        store.connect(host, user1, password);
        String c = "";
        if(method.equals("Sent")) {

            if (user1.split("@")[1].equals("mail.ru")) {
                c = "Отправленные";
            } else if (user1.split("@")[1].equals("gmail.com")) {
                c = "[Gmail]/Sent Mail";
            } else {
                c = "Sent";
            }

        }
        else {
            c = method;
        }
        Folder inbox = store.getFolder(c); //INBOX

        inbox.open(Folder.READ_ONLY);

        if(id==null) {

            model.addAttribute("unread_num", inbox.getMessageCount());

            String ENCODED_PART_REGEX_PATTERN = "=\\?([^?]+)\\?([^?]+)\\?([^?]+)\\?=";
            Pattern pattern = Pattern.compile(ENCODED_PART_REGEX_PATTERN);
            List<Message> m = new ArrayList<Message>();

            int count = 20;//

            if(inbox.getMessageCount()<20){//
                count = inbox.getMessageCount();
            }
            if (method.equals("INBOX")) {
                for (int i = inbox.getMessageCount(); i > inbox.getMessageCount()- count; i--) {//
                    javax.mail.Message m1 = inbox.getMessage(i);
                    Matcher ma = pattern.matcher(m1.getFrom()[0].toString());
                    if (ma.find()) {
                        m.add(new Message((long) i, m1.getSentDate().toString(), m1.getSubject(),
                                MimeUtility.decodeWord(m1.getFrom()[0].toString()),
                                m1.getContent().toString()));
                    } else m.add(new Message((long) i, m1.getSentDate().toString(), m1.getSubject(),
                            m1.getFrom()[0].toString(),
                            m1.getContent().toString()));

                }
            } else {
                for (int i = inbox.getMessageCount(); i > inbox.getMessageCount()- count; i--) {//
                    javax.mail.Message m1 = inbox.getMessage(i);
                    Matcher ma = pattern.matcher(m1.getAllRecipients()[0].toString());
                    if (ma.find()) {
                        m.add(new Message((long) i, m1.getSentDate().toString(), m1.getSubject(),
                                MimeUtility.decodeWord(m1.getAllRecipients()[0].toString()),
                                m1.getContent().toString()));
                    } else m.add(new Message((long) i, m1.getSentDate().toString(), m1.getSubject(),
                            m1.getAllRecipients()[0].toString(),
                            m1.getContent().toString()));

                }
            }
            model.addAttribute("mails", m);
        }
        else {
            //if()
            javax.mail.Message m1 = inbox.getMessage(Math.toIntExact(id));

            model.addAttribute("unread_num", m1.getSubject());
            model.addAttribute("sent_data", m1.getSentDate().toString());
            String contentType = m1.getContentType();
            String messageContent="";

            if (contentType.contains("multipart")) {
                Multipart multiPart = (Multipart) m1.getContent();
                int numberOfParts = multiPart.getCount();
                for (int partCount = 0; partCount < numberOfParts; partCount++) {
                    MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                    messageContent = part.getContent().toString();
                }
            }
            else if (contentType.contains("text/plain")
                    || contentType.contains("text/html")) {
                Object content = m1.getContent();
                if (content != null) {
                    messageContent = content.toString();
                }
            }

            model.addAttribute("content", messageContent);
        }

        inbox.close();
        return model;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.exit(2);
        }
        return model;
    }


}
