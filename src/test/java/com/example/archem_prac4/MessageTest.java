//package com.example.archem_prac4;
//
//import com.example.archem_prac4.services.MessageService;
//import com.example.archem_prac4.services.UserService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.example.archem_prac4.models.Message;
//import com.example.archem_prac4.models.User;
//
//import java.util.HashSet;
//
//public class MessageTest extends ArchemPrac4ApplicationTests {
//    @Mock
//    private DialogRepo dialogRepo;
//
//    @Autowired
//    private MessageService messageService;
//
//    @Autowired
//    private UserService userService;
//
//    @Test
//    public void addDialogAndMessage() {
//        var set = new HashSet<Role>();
//        set.add(new Role(2L, "ROLE_USER"));
//        User user = new User();
//        user.setUsername("user1");
//        user.setPassword("password1");
//        user.setRoles(set);
//
//        User user2 = new User();
//        user2.setUsername("user2");
//        user2.setPassword("password2");
//        user2.setRoles(set);
//
//        userService.save(user);
//        userService.save(user2);
//        userService.addFriend(user, user2);
//        userService.addFriend(user2, user);
//
//        var dialog = new Dialog();
//        dialog.addUser(user);
//        dialog.addUser(user2);
//        dialogRepo.save(dialog);
//        var message = new Message();
//        message.setContent("test");
//
//        messageService.addNewMessageToDialog(message, dialog);
//
//        Assertions.assertFalse(dialog.getMessageList().isEmpty());
//    }
//}
