package Controller;

import DAO.DAOHangNhap;
import Model.XuatHang;
import DAO.DAOHangXuat;
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
public class QuanLyHangXuat implements IQuanLyHangXuat {

    private DAOHangXuat _dAOHangXuat;
    private DAOHangNhap dAOHangNhap;
    List<XuatHang> _lstXH;

    public QuanLyHangXuat() {
        dAOHangNhap = new DAOHangNhap();
        _dAOHangXuat = new DAOHangXuat();
        _lstXH = new ArrayList<>();
        _lstXH = _dAOHangXuat.getListXuatHang();
    }

    @Override
    public int getLotlo() {
        return _dAOHangXuat.getIDLot() + 1;
    }

    @Override
    public List<XuatHang> getList() {
        return _lstXH;
    }

    @Override
    public TreeSet<String> getListIDProduct() {
        return dAOHangNhap.getLstIDProduct();
    }

    @Override
    public boolean exportProduct(List<XuatHang> lstExport) {
        if (lstExport.isEmpty()) {
            return false;
        }
        int count = 0;
        for (XuatHang x : lstExport) {
            if (_dAOHangXuat.exportProduct(x)) {
                _lstXH.add(x);
                count++;
            }
        }
        if (count > 0) {
            return true;
        }
        return false;
    }

}
