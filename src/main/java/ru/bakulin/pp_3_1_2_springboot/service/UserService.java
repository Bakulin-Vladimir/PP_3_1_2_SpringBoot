package ru.bakulin.pp_3_1_2_springboot.service;

import ru.bakulin.pp_3_1_2_springboot.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    List<User> readUsers();
    User readUserId(long id);
    void updateUser(User user);
    void deleteUser(long id);


}
