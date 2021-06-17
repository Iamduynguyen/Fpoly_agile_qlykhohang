package Controller;

import Model.User;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author NgocPJa
 */
public interface ILogin {

    List<User> getListUser();

    User loginUser(String username, String password);

    boolean changedPassword(User user);

    boolean addAccount(User user);

    boolean editAccount(User user);

    List<String> getListUserName();

    User getUser(String userName);

    String getNameRole(int idRole);

}
