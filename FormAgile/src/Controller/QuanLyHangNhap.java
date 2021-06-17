package Controller;

import Model.NhapHang;
import DAO.DAOHangNhap;
import DAO.DAOUnit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author NgocPJa
 */
public class QuanLyHangNhap implements IQuanLyHangNhap {

    private DAOHangNhap dAOHangNhap;
    private DAOUnit dAOUnit;
    private List<NhapHang> lst;
    private NhapHang nhapHang;
    private SimpleDateFormat _simpleDateFormat;

    public QuanLyHangNhap() {
        dAOHangNhap = new DAOHangNhap();
        dAOUnit = new DAOUnit();
        lst = new ArrayList<NhapHang>();
        lst = dAOHangNhap.getListImport();
    }

    // trả về một danh sách sản phẩm đã nhập
    @Override
    public List<NhapHang> getList() {
        return lst;
    }

    // tìm kiếm theo kiểu và giá trị tùy ý
    /*
    type 1. tìm kiếm theo mã sản phẩm
    type 2. tìm kiếm theo tên sản phầm
    type 3. tìm kiếm theo số lượng của sản phẩm
    type 4. tìm kiếm theo số lô của sản phẩm
     */
    @Override
    public List<NhapHang> searchList(int type, String values) {
        List<NhapHang> getListSearch = new ArrayList<>();
        switch (type) {
            case 0: {
                for (NhapHang x : lst) {
                    if (x.getIdProduct().equalsIgnoreCase(values)) {
                        getListSearch.add(x);
                    }
                }
                break;
            }
            case 1: {
                for (NhapHang x : lst) {
                    if (x.getNameProduct().equalsIgnoreCase(values)) {
                        getListSearch.add(x);
                    }
                }
                break;
            }
            case 2: {
                for (NhapHang x : lst) {
                    if (x.getAmount() == Integer.parseInt(values)) {
                        getListSearch.add(x);
                    }
                }
                break;
            }
            case 3: {
                for (NhapHang x : lst) {
                    String date = getDateLotlo(x);
                    if (values.equals(date + x.getLotImport())) {
                        getListSearch.add(x);
                    }
                }
                break;
            }
        }
        return getListSearch;
    }

    // thêm sản phẩm trong bảng sản phẩm của form nhập hàng vào trong lst chính
    @Override
    public boolean addProducts(List<NhapHang> lstAdd) {
        if (lstAdd.isEmpty()) {
            return false;
        }
        int count = 0;
        for (NhapHang x : lstAdd) {
            if (dAOHangNhap.addProduct(x)) {
                lst.add(x);
                count++;
            }
        }
        if (count > 0) {
            return true;
        }
        return false;
    }

    // lấy ra số lô có thể insert vào database identity
    @Override
    public int getLotlo() {
        return dAOHangNhap.getIDLot() + 1;
    }

    // lấy ra danh sách số lô trong kho
    @Override
    public TreeSet<String> getListSolo() {
        TreeSet<String> lstSoLo = new TreeSet<>();
        for (NhapHang x : lst) {
            String date = getDateLotlo(x);
            lstSoLo.add(date + x.getLotImport());
        }
        return lstSoLo;
    }

    // lấy ra danh sách mã sản phẩm theo số lô
    @Override
    public List<String> getIDProduct(String lotLo) {
        List<String> lstIDProduct = new ArrayList<>();
        for (NhapHang x : lst) {
            String date = getDateLotlo(x);
            if (lotLo.equals(date + x.getLotImport())) {
                lstIDProduct.add(x.getIdProduct());
//                if (x.getAmount() > 0) {
//                    lstIDProduct.add(x.getIdProduct());
//                }
            }
        }
        return lstIDProduct;
    }

    // trả về một đối tượng có mã sản phẩm được truyền vào
    @Override
    public NhapHang getProductByIDProduct(String idProduct, String lotLo) {
        for (NhapHang x : lst) {
            if (x.getIdProduct().equalsIgnoreCase(idProduct)) {
                String date = getDateLotlo(x);
                if (lotLo.equalsIgnoreCase(date)) {
                    return x;
                }
            }
        }
        return null;
    }

    @Override
    public String getNameUnit(int idUnit) {
        return dAOUnit.getNameUnit(idUnit);
    }

    @Override
    public String getNameProduct(String idProduct) {
        return dAOHangNhap.getNameProduct(idProduct);
    }

    @Override
    public String[] getNameUnits() {
        return dAOHangNhap.getNameUnits();
    }

//    @Override
//    public boolean reSetList() {
//        System.out.println("Đã vào resetlst quan ly nhap hang");
//        lst.clear();
//        lst = new ArrayList<>();
//        lst = dAOHangNhap.getListImport();
//        return true;
//    }
    @Override
    public List<NhapHang> getListExport(String idProduct) {
        List<NhapHang> getListSearch = new ArrayList<>();
        for (NhapHang x : lst) {
            if (x.getIdProduct().equalsIgnoreCase(idProduct)) {
                if (x.getAmount() - x.getAmountProductExport() > 0) {
                    getListSearch.add(x);
                }
            }
        }
        return getListSearch;
    }

    @Override
    public List<NhapHang> getListConHang() {
        List<NhapHang> lstTK = new ArrayList<>();
        for (NhapHang x : lst) {
            if (x.getAmount() - x.getAmountProductExport() > 0) {
                nhapHang = new NhapHang();
                nhapHang.setAddDate(x.getAddDate());
                nhapHang.setAmount(x.getAmount() - x.getAmountProductExport());
                nhapHang.setAmountProductExport(x.getAmountProductExport());
                nhapHang.setExpiryDate(x.getExpiryDate());
                nhapHang.setIdImport(x.getIdImport());
                nhapHang.setIdProduct(x.getIdProduct());
                nhapHang.setLocation(x.getLocation());
                nhapHang.setNameProduct(x.getNameProduct());
                nhapHang.setNote(x.getNote());
                nhapHang.setPrice(x.getPrice());
                nhapHang.setUnit(x.getUnit());
                lstTK.add(nhapHang);
            }
        }
        return lstTK;
    }

    @Override
    public boolean checkLotlo(String lotlo) {
        if (lotlo == null || lotlo.isEmpty()) {
            return false;
        }
        for (NhapHang x : lst) {
            String date = getDateLotlo(x);
            if (lotlo.equals(date + x.getLotImport())) {
                return true;
            }
        }
        return false;
    }

    private String getDateLotlo(NhapHang x) {
        _simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = _simpleDateFormat.format(x.getAddDate());
        return date;
    }

    @Override
    public boolean editProduct(NhapHang nhapHang) {
        return dAOHangNhap.editProducr(nhapHang);
    }

    @Override
    public double getPriceProduct(int idProduct) {
        for (NhapHang x : lst) {
            if (x.getIdImport() == idProduct) {
                return x.getPrice();
            }
        }
        return 0;
    }

}
