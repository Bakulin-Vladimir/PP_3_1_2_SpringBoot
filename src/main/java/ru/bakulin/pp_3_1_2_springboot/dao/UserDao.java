package ru.bakulin.pp_3_1_2_springboot.dao;


import ru.bakulin.pp_3_1_2_springboot.model.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    User readUserID(long id);

    List<User> readUsers();

    void updateUser(User user);

    void deleteUser(long id);
}
