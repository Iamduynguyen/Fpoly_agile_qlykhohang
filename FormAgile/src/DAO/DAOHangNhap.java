package DAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Model.NhapHang;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author NgocPJa
 */
public class DAOHangNhap {

    private DAO dao = new DAO();
    private NhapHang nhapHang;
    private SimpleDateFormat _simpleDateFormat = new SimpleDateFormat();
    private List<NhapHang> lstNhapHang = new ArrayList<>();

    public DAOHangNhap() {
        String sql = "SELECT LIM.LOTLO, PRI.PRICE, PRI.EXPIRYDATE, LIM.DATEADDED, PRI.LOCATION,\n"
                + "PRI.NOTE, PRI.ID, P.IDPRODUCT, P.NAMEPRODUCT, PRI.AMOUNT, UNI.IDUNIT, PRI.AMOUNTPRODUCTEXPRORT\n"
                + "FROM LOTIMPORT AS LIM JOIN PRODUCTIMPORT AS PRI\n"
                + "ON LIM.LOTLO = PRI.LOTLO JOIN PRODUCT AS P\n"
                + "ON P.IDPRODUCT = PRI.IDPRODUCT JOIN UNITPRODUCT AS UNI\n"
                + "ON PRI.IDUNIT = UNI.IDUNIT";
        try (Statement stm = dao.openConnection().createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                nhapHang = new NhapHang();
                nhapHang.setLotImport(rs.getInt(1));
                nhapHang.setPrice(rs.getDouble(2));
                nhapHang.setExpiryDate(rs.getDate(3));
                nhapHang.setAddDate(rs.getDate(4));
                nhapHang.setLocation(rs.getString(5));
                nhapHang.setNote(rs.getString(6));
                nhapHang.setIdImport(rs.getInt(7));
                nhapHang.setIdProduct(rs.getString(8));
                nhapHang.setNameProduct(rs.getString(9));
                nhapHang.setAmount(rs.getInt(10));
                nhapHang.setUnit(rs.getInt(11));
                nhapHang.setAmountProductExport(rs.getInt(12));
                lstNhapHang.add(nhapHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<NhapHang> getListImport() {
        return lstNhapHang;
    }

    public int getIDLot() {
        String sql = "EXEC PROC_GETID ?";
        try (PreparedStatement pstm = dao.openConnection().prepareCall(sql);) {
            pstm.setString(1, "LOTIMPORT");
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

    public boolean addProduct(NhapHang nhapHang) {
        if (nhapHang != null) {
            _simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            String sql = "EXEC PROC_IMPORTPRODUCT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
            try (PreparedStatement pstm = dao.openConnection().prepareCall(sql);) {
                pstm.setString(1, nhapHang.getIdProduct());
                pstm.setString(2, nhapHang.getNameProduct());
                pstm.setString(3, _simpleDateFormat.format(nhapHang.getAddDate()));
                pstm.setInt(4, nhapHang.getLotImport());
                pstm.setInt(5, nhapHang.getAmount());
                pstm.setInt(6, nhapHang.getUnit());
                pstm.setDouble(7, nhapHang.getPrice());
                pstm.setString(8, _simpleDateFormat.format(nhapHang.getExpiryDate()));
                pstm.setString(9, nhapHang.getLocation());
                pstm.setString(10, nhapHang.getNote());
                int i = pstm.executeUpdate();
                if (i > 0) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // lấy ra toàn bộ mã sản phẩm đã nhập có trên database
    public TreeSet<String> getLstIDProduct() {
//        List<String> lstIDProduct = new ArrayList<>();
//        String sql = "SELECT DISTINCT P.IDPRODUCT FROM PRODUCT AS P JOIN PRODUCTIMPORT AS PRI\n"
//                + "ON P.IDPRODUCT = PRI.IDPRODUCT\n"
//                + "WHERE PRI.AMOUNT - PRI.AMOUNTPRODUCTEXPRORT > 0";
//        try (Statement stm = dao.openConnection().createStatement();
//                ResultSet rs = stm.executeQuery(sql);) {
//            while (rs.next()) {
//                lstIDProduct.add(rs.getString(1));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        TreeSet<String> lstIDProduct = new TreeSet<>();
        for (NhapHang x : lstNhapHang) {
            if (x.getAmount() - x.getAmountProductExport() > 0) {
                lstIDProduct.add(x.getIdProduct());
            }
        }
        return lstIDProduct;
    }



    public String getNameProduct(String idProduct) {
        for (NhapHang x : lstNhapHang) {
            if (x.getIdProduct().equalsIgnoreCase(idProduct)) {
                return x.getNameProduct();
            }
        }
        return "";
    }

    public String[] getNameUnits() {
        String sql = "SELECT NAMEUNIT FROM UNITPRODUCT";
        try (Statement stm = dao.openConnection().createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
            List<String> lstNameUnits = new ArrayList<>();
            while (rs.next()) {
                lstNameUnits.add(rs.getString(1));
            }
            String[] arrNameUnits = new String[lstNameUnits.size()];
            for (int i = 0; i < lstNameUnits.size(); i++) {
                arrNameUnits[i] = lstNameUnits.get(i);
            }
            return arrNameUnits;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    @ID INT,
	@IDPRODUCT VARCHAR(15),
	@NAMEPRODUCT NVARCHAR(50),
	@AMOUNT INT,
	@IDUNIT INT,
	@PRICE MONEY,
	@EXPIRYDATE DATETIME,
	@LOCATION NVARCHAR(100),
	@NOTE NTEXT,
	@AMOUNTEXPORT INT    
     */
    public boolean editProducr(NhapHang nhapHang) {
        if (nhapHang != null) {
            _simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            String sql = "EXEC PROC_EDITPRODUCT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
            try (PreparedStatement pstm = dao.openConnection().prepareStatement(sql);) {
                pstm.setInt(1, nhapHang.getIdImport());
                pstm.setString(2, nhapHang.getIdProduct());
                pstm.setString(3, nhapHang.getNameProduct());
                pstm.setInt(4, nhapHang.getAmount());
                pstm.setInt(5, nhapHang.getUnit());
                pstm.setDouble(6, nhapHang.getPrice());
                pstm.setString(7, _simpleDateFormat.format(nhapHang.getExpiryDate()));
                pstm.setString(8, nhapHang.getLocation());
                pstm.setString(9, nhapHang.getNote());
                pstm.setInt(10, nhapHang.getAmountProductExport());
                System.out.println("đã tới edit product file daonhaphang");
                int i = pstm.executeUpdate();
                if (i > 0) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
