package Controller;

import Model.User;
import DAO.DAOLogin;
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
public class Login implements ILogin {

    private DAOLogin dAOLogin;

    public Login() {
        dAOLogin = new DAOLogin();
    }

    @Override
    public User loginUser(String username, String password) {
        return dAOLogin.checkLogin(username, password);
    }

    @Override
    public boolean changedPassword(User user) {
        if (user != null) {
            if (dAOLogin.changedPassword(user)) {
                return dAOLogin.changedPassword(user);
            }
        }
        return false;
    }

    @Override
    public boolean addAccount(User user) {
        if (user != null) {
            return dAOLogin.addAcount(user);
        }
        return false;
    }

    @Override
    public boolean editAccount(User user) {
        if (user != null) {
            if (dAOLogin.editAcount(user)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> getListUserName() {
        return dAOLogin.getArrUserName();
    }

    @Override
    public User getUser(String userName) {
        return dAOLogin.getUser(userName);
    }

    @Override
    public List<User> getListUser() {
        return dAOLogin.getListUserDB();
    }

    @Override
    public String getNameRole(int idRole) {
        return dAOLogin.getNameRole(idRole);
    }

}
