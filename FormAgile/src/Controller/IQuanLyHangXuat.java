package Controller;

import Model.XuatHang;
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
public interface IQuanLyHangXuat {

    List<XuatHang> getList();

    TreeSet<String> getListIDProduct();

    int getLotlo();

    boolean exportProduct(List<XuatHang> lstExport);

}
