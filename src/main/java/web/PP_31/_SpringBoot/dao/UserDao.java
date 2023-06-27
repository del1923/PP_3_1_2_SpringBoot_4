package web.PP_31._SpringBoot.dao;


import web.PP_31._SpringBoot.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> getUserList();

    void addUser(User user);

    User show(int id);

    void update(int id, User user);

    void delete(int id);

    Optional<User> showByEMail(String eMail);

}
