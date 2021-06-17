package Controller;

import Model.NhapHang;
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
public interface IQuanLyHangNhap {

    List<NhapHang> getList();

    List<NhapHang> searchList(int type, String values);

    boolean addProducts(List<NhapHang> lstAdd);

    int getLotlo();

    TreeSet<String> getListSolo();

    List<String> getIDProduct(String lotLo);

    NhapHang getProductByIDProduct(String idProduct, String lotLo);

    String getNameUnit(int idUnit);

    String getNameProduct(String idProduct);

    String[] getNameUnits();

//    boolean reSetList();
    List<NhapHang> getListExport(String idProduct);

    List<NhapHang> getListConHang();

    boolean checkLotlo(String lotlo);

    boolean editProduct(NhapHang nhapHang);

    double getPriceProduct(int idProduct);

}
