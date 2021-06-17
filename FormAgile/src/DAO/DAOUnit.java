/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Unit;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NgocPJa
 */
public class DAOUnit {

    private DAO dao = new DAO();
    private List<Unit> lstUnit = new ArrayList<>();

    public DAOUnit() {
        String sql = "SELECT * FROM UNITPRODUCT";
        try (Statement stm = dao.openConnection().createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                Unit unit = new Unit();
                unit.setIdUnit(rs.getInt(1));
                unit.setNameUnit(rs.getString(2));
                lstUnit.add(unit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getNameUnit(int idunit) {
        for (Unit x : lstUnit) {
            if (x.getIdUnit() == idunit) {
                return x.getNameUnit();
            }
        }
        return "";
    }
}
