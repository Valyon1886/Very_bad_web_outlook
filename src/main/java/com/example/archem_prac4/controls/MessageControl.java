package com.example.archem_prac4.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.archem_prac4.services.MessageService;
import com.example.archem_prac4.services.UserService;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;


@Controller
public class MessageControl {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @GetMapping("/inbox")
    public String getInbox(Model model) throws IOException, MessagingException {
        model = messageService.getMessageList(model, "INBOX", null);
        return "inbox";
    }

    @GetMapping("/inbo/{id}")
    public String getInbox(@PathVariable Long id, Model model) throws IOException, MessagingException {
        model = messageService.getMessageList(model, "INBOX", id);
        return "inbo";
    }
//    @GetMapping("/sent")
//    public String getDialog(@PathVariable int id, Model model) {
//        var dialog = messageService.getDialogById((long) id);
//        if (dialog.isPresent()) {
//            model.addAttribute("messages", dialog.get().getMessageList());
//            var mes = new Message();
//            mes.setUser(userService.getUser());
//            model.addAttribute("dialogId", id);
//            model.addAttribute("message", mes);
//            model.addAttribute("toUserName", dialog.get().getUserList().stream()
//                    .filter(e -> e != userService.getUser()).collect(Collectors.toList()).get(0).getUsername());
//            return "dialog";
//        } else {
//            model.addAttribute("reason", "Не найден диалог с id " + id);
//            return "error";
//        }
//    }
//
//    @PostMapping("/sent/new_email")
//    public String addMessage(@PathVariable int id, @ModelAttribute("message") Message message, Model model) {
//        var dialog = messageService.getDialogById((long) id);
//        if (dialog.isPresent()) {
//            messageService.addNewMessageToDialog(message, dialog.get());
//            return "redirect:/dialog/" + id;
//        } else {
//            model.addAttribute("reason", "Не найден диалог с id " + id);
//            return "error";
//        }
//    }
    @GetMapping("/outbox")
    public String getOutbox(Model model) throws IOException, MessagingException {
//        Properties properties = messageService.setEmailAdress();
//        User u1 = userService.getUser();
//        String user1 = properties.getProperty("mail.user");
//        String password = properties.getProperty("mail.password");
//        String host = properties.getProperty("mail.host");
//
//
//
//        Properties prop = new Properties();
//
//        prop.put("mail.store.protocol"  , "imaps"  ); //ssl
//
//        Store store = Session.getInstance(prop).getStore();
//
//        store.connect(host, user1, password);
//
//        Folder[] f = store.getDefaultFolder().list();
//
//        String c = "";
//        if(user1.split("@")[1].equals("mail.ru")) {
//            c = "Отправленные";
//        }
//        else if(user1.split("@")[1].equals("gmail.com")) {
//            c = "[Gmail]/Sent Mail";
//        }
//        else{
//            c = "Sent";
//        }
//        Folder inbox = store.getFolder(c);
//        inbox.open(Folder.READ_ONLY);
//
//        //int num = inbox.getMessageCount();
//        //String p = "";
//
//        model.addAttribute("unread_num", inbox.getMessageCount());
//
//        String ENCODED_PART_REGEX_PATTERN="=\\?([^?]+)\\?([^?]+)\\?([^?]+)\\?=";
//        Pattern pattern=Pattern.compile(ENCODED_PART_REGEX_PATTERN);
//        List<Message> m = new ArrayList<Message>();
//        for (int i = inbox.getMessageCount(); i >0 ; i--){
//            javax.mail.Message m1 = inbox.getMessage(i);
//            Matcher ma =pattern.matcher(m1.getAllRecipients()[0].toString());
//            if(ma.find()){
//                m.add(new Message((long) i, m1.getSentDate().toString(), m1.getSubject(),
//                        MimeUtility.decodeWord(m1.getAllRecipients()[0].toString()),
//                        m1.getContent().toString()));
//            }
//            else m.add(new Message((long) i, m1.getSentDate().toString(), m1.getSubject(),
//                    m1.getAllRecipients()[0].toString(),
//                    m1.getContent().toString()));
//
//        }
//        model.addAttribute("mails", m);
//        inbox.close();
        model = messageService.getMessageList(model, "Sent", null);
        return "outbox";
    }

    @GetMapping("/outbo/{id}")
    public String getOutbox(@PathVariable Long id, Model model) throws IOException, MessagingException {
        model = messageService.getMessageList(model, "Sent", id);
        return "outbo";
    }


    @PostMapping("/sent")
    public String postNewMessage(@RequestParam String email,
                            @RequestParam String sub,
                            @RequestParam String text,
                            Model model) throws IOException, MessagingException {
        //FileInputStream fileInputStream = new FileInputStream("config.properties");
//        Properties properties = new Properties();
//        User u1 = userService.getUser();
//        properties.put("mail.user", u1.getEmails());//"Test6234@yandex.ru");
//        properties.put("mail.password", u1.getE_secret());//"eaaxzxpvesyyifyj");
//        properties.put("mail.host", "imap.yandex.ru");
//
//        String user1 = properties.getProperty("mail.user");
//        String password = properties.getProperty("mail.password");
//        String host = properties.getProperty("mail.host");
//
//        Properties prop = new Properties();
//
//        prop.put("mail.store.protocol"  , "imaps"  ); //ssl
//
//        Store store = Session.getInstance(prop).getStore();
//
//        store.connect(host, user1, password);
//
//        Folder inbox = store.getFolder("Sent");
//
//        inbox.open(Folder.READ_ONLY);
//
//        //javax.mail.Message message = inbox.getMessage(inbox.getMessageCount());
//        //Multipart mp = (Multipart) message.getContent();
//        //System.out.println(mp.getContentType());
//        //BodyPart  bp = mp.getBodyPart(1);
////        String c = message.getContent().toString();
////        String mail = message.getFrom().toString();
////        String sub = message.getSubject();
////        Object body = message.getContent();
//        //model.addAttribute("content", c); //тоже в цикл
//
//        javax.mail.Message m1 = inbox.getMessage(Math.toIntExact(id));
//
//        model.addAttribute("unread_num", m1.getSubject());
//        model.addAttribute("sent_data", m1.getSentDate().toString());
//        String contentType = m1.getContentType();
//        String messageContent="";
//
//        if (contentType.contains("multipart")) {
//            Multipart multiPart = (Multipart) m1.getContent();
//            int numberOfParts = multiPart.getCount();
//            for (int partCount = 0; partCount < numberOfParts; partCount++) {
//                MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
//                messageContent = part.getContent().toString();
//            }
//        }
//        else if (contentType.contains("text/plain")
//                || contentType.contains("text/html")) {
//            Object content = m1.getContent();
//            if (content != null) {
//                messageContent = content.toString();
//            }
//        }
//
//        model.addAttribute("content", messageContent);
//        inbox.close();
        Properties props = messageService.setEmailAdress();


        List<String> matchingValues = props.entrySet().stream() //quqstxmpieidkmev  ||  ehmxnlqkqxoomxxa
                .filter(e -> e.getKey().toString().matches("mail.*"))
                .map(e -> e.getValue().toString())
                .collect(Collectors.toList());

        Session session = Session.getDefaultInstance(props,
                //Аутентификатор - объект, который передает логин и пароль
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(matchingValues.get(4), matchingValues.get(3));
                    }
                });

        //Создаем новое почтовое сообщение
        javax.mail.Message message = new MimeMessage(session);
        //От кого
        message.setFrom(new InternetAddress(matchingValues.get(4)));
        //Кому
        message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(email));
        //Тема письма
        message.setSubject(sub);
        //Текст письма
        message.setText(text);
        //Поехали!!!
        Transport.send(message);
        return "redirect:/";
    }

    @GetMapping("/sent")
    public String getSendPage(Model model) {
        return "sent";
    }
}
