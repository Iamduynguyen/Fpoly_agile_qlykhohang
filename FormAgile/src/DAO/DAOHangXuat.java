package DAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Model.XuatHang;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NgocPJa
 */
public class DAOHangXuat {

    private DAO dao = new DAO();
    private XuatHang xuatHang = null;
    private SimpleDateFormat _simpleDateFormat = new SimpleDateFormat();
    private List<XuatHang> lst = new ArrayList<>();

    public DAOHangXuat() {
        String sql = "SELECT PRE.LOTLO, PRI.LOTLO, PRE.DATEEXPORT, PRI.LOCATION, PRE.NOTE,\n"
                + "PRI.ID, P.IDPRODUCT, P.NAMEPRODUCT, PRE.AMOUNT, PRI.IDUNIT\n"
                + "FROM PRODUCTEXPORT AS PRE JOIN PRODUCTIMPORT AS PRI\n"
                + "ON PRE.IDIMPORT = PRI.ID JOIN PRODUCT AS P\n"
                + "ON PRI.IDPRODUCT = P.IDPRODUCT";
        try (PreparedStatement pstm = dao.openConnection().prepareStatement(sql);) {
            try (ResultSet rs = pstm.executeQuery();) {
                while (rs.next()) {
                    xuatHang = new XuatHang();
                    xuatHang.setLotExport(rs.getInt(1));
                    xuatHang.setLotImport(rs.getInt(2));
                    xuatHang.setShippingDate(rs.getDate(3));
                    xuatHang.setLocation(rs.getString(4));
                    xuatHang.setNote(rs.getString(5));
                    xuatHang.setIdImport(rs.getInt(6));
                    xuatHang.setIdProduct(rs.getString(7));
                    xuatHang.setNameProduct(rs.getString(8));
                    xuatHang.setAmount(rs.getInt(9));
                    xuatHang.setUnit(rs.getInt(10));
                    lst.add(xuatHang);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<XuatHang> getListXuatHang() {
        return lst;
    }

    public int getIDLot() {
        String sql = "EXEC PROC_GETID ?";
        try (PreparedStatement pstm = dao.openConnection().prepareCall(sql);) {
            pstm.setString(1, "PRODUCTEXPORT");
            try (ResultSet rs = pstm.executeQuery();) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean exportProduct(XuatHang xuatHang) {
        if (xuatHang != null) {
            _simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            String sqlInserExport = "INSERT PRODUCTEXPORT (IDIMPORT, AMOUNT, DATEEXPORT, NOTE)\n"
                    + "VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstm = dao.openConnection().prepareStatement(sqlInserExport);) {
                pstm.setInt(1, xuatHang.getIdImport());
                pstm.setInt(2, xuatHang.getAmount());
                pstm.setString(3, _simpleDateFormat.format(xuatHang.getShippingDate()));
                pstm.setString(4, xuatHang.getNote());
                pstm.executeUpdate();
                String getAmountImport = "SELECT AMOUNTPRODUCTEXPRORT FROM PRODUCTIMPORT WHERE ID = '" + xuatHang.getIdImport() + "'";
                try (Statement stm = dao.openConnection().createStatement();
                        ResultSet rs = stm.executeQuery(getAmountImport);) {
                    String sqlInsertImport = "UPDATE PRODUCTIMPORT SET AMOUNTPRODUCTEXPRORT = ? WHERE ID = ?";
                    try (PreparedStatement pstm1 = dao.openConnection().prepareStatement(sqlInsertImport);) {
                        if (rs.next()) {
                            String soLuong = rs.getString(1);
                            if (soLuong != null) {
                                pstm1.setInt(1, xuatHang.getAmount() + Integer.parseInt(soLuong));
                            } else {
                                pstm1.setInt(1, xuatHang.getAmount());
                            }
                            pstm1.setInt(2, xuatHang.getIdImport());
                            pstm1.executeUpdate();
                        }
                    }
                }
                System.out.println("đã xuất file daohangxuat");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
