package DAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NgocPJa
 */
public class DAOLogin {

    private DAO dao = new DAO();
    private User user = null;
    private List<User> lst = new ArrayList<>();

    public DAOLogin() {
        String sql = "SELECT USERNAME, PASSWORD, NAME, STATUS, IDROLE FROM USERS";
        try (Statement stm = dao.openConnection().createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                user = new User();
                user.setUsername(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setName(rs.getString(3));
                user.setStatus(rs.getInt(4) == 1);
                user.setRole(rs.getInt(5));
                lst.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getListUserDB() {
//        List<User> lst = new ArrayList<>();
//        String sql = "SELECT USERNAME, PASSWORD, NAME, STATUS, IDROLE FROM USERS";
//        try (Statement stm = dao.openConnection().createStatement();
//                ResultSet rs = stm.executeQuery(sql);) {
//            while (rs.next()) {
//                user = new User();
//                user.setUsername(rs.getString(1));
//                user.setPassword(rs.getString(2));
//                user.setName(rs.getString(3));
//                user.setStatus(rs.getInt(4) == 1);
//                user.setRole(rs.getInt(5));
//                lst.add(user);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return lst;
    }

    public User checkLogin(String username, String password) {
//        String sql = "SELECT USERNAME, PASSWORD, NAME, STATUS, IDROLE FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
//        try (PreparedStatement pstm = dao.openConnection().prepareStatement(sql);) {
//            pstm.setString(1, username);
//            pstm.setString(2, password);
//            try (ResultSet rs = pstm.executeQuery();) {
//                if (rs.next()) {
//                    user = new User();
//                    user.setUsername(rs.getString(1));
//                    user.setPassword(rs.getString(2));
//                    user.setName(rs.getString(3));
//                    user.setStatus(rs.getInt(4) == 1);
//                    user.setRole(rs.getInt(5));
//                    return user;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        for (User x : lst) {
            if (x.getUsername().equalsIgnoreCase(username) && x.getPassword().equals(password)) {
                return x;
            }
        }
        return null;
    }

    public boolean changedPassword(User user) {
        String sql = "UPDATE USERS SET PASSWORD = ? WHERE USERNAME = ?";
        try (PreparedStatement pstm = dao.openConnection().prepareStatement(sql);) {
            pstm.setString(1, user.getPassword());
            pstm.setString(2, user.getUsername());
            if (pstm.executeUpdate() > 0) {
                for (int i = 0; i < lst.size(); i++) {
                    if (lst.get(i).getUsername().equalsIgnoreCase(user.getUsername())) {
                        lst.set(i, user);
                        break;
                    }
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addAcount(User user) {
        String sql = "INSERT INTO USERS (USERNAME, PASSWORD, NAME, STATUS, IDROLE)"
                + " VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = dao.openConnection().prepareStatement(sql);) {
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getUsername());
            pstm.setInt(4, user.isStatus() ? 1 : 0);
            pstm.setInt(5, user.getRole());
            if (pstm.executeUpdate() > 0) {
                lst.add(user);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean editAcount(User user) {
        String sql = "UPDATE USERS SET PASSWORD = ?, NAME = ?, STATUS = ?, IDROLE = ? WHERE USERNAME = ?";
        try (PreparedStatement pstm = dao.openConnection().prepareStatement(sql);) {
            pstm.setString(1, user.getPassword());
            pstm.setString(2, user.getName());
            pstm.setInt(3, user.isStatus() ? 1 : 0);
            pstm.setInt(4, user.getRole());
            pstm.setString(5, user.getUsername());
            if (pstm.executeUpdate() > 0) {
                for (int i = 0; i < lst.size(); i++) {
                    if (lst.get(i).getUsername().equalsIgnoreCase(user.getUsername())) {
                        lst.set(i, user);
                        break;
                    }
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> getArrUserName() {
//        String sql = "SELECT USERNAME FROM USERS";
//        try (Statement stm = dao.openConnection().createStatement();
//                ResultSet rs = stm.executeQuery(sql);) {
//            List<String> arr = new ArrayList<>();
//            while (rs.next()) {
//                arr.add(rs.getString(1));
//            }
//            return arr;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        List<String> arr = new ArrayList<>();
        for (User x : lst) {
            arr.add(x.getUsername());
        }
        return arr;
    }

    public User getUser(String userName) {
        if (userName == null || userName.isEmpty()) {
            return null;
        }
        for (User x : lst) {
            if (x.getUsername().equalsIgnoreCase(userName)) {
                return x;
            }
        }
//        String sql = "SELECT PASSWORD, NAME, STATUS, IDROLE FROM USERS WHERE USERNAME = ?";
//        try (PreparedStatement pstm = dao.openConnection().prepareStatement(sql);) {
//            pstm.setString(1, userName);
//            try (ResultSet rs = pstm.executeQuery();) {
//                if (rs.next()) {
//                    user = new User();
//                    user.setUsername(userName);
//                    user.setPassword(rs.getString(1));
//                    user.setName(rs.getString(2));
//                    user.setStatus(rs.getInt(3) == 1);
//                    user.setRole(rs.getInt(4));
//                    return user;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }

    public String getNameRole(int idRole) {
        String sql = "SELECT NAMEROLE FROM USERROLE WHERE ID = ?";
        try (PreparedStatement pstm = dao.openConnection().prepareStatement(sql);) {
            pstm.setInt(1, idRole);
            try (ResultSet rs = pstm.executeQuery();) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
