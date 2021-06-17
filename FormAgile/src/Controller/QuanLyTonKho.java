/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.NhapHang;
import Model.TonKho;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author NgocPJa
 */
public class QuanLyTonKho implements IQuanLyTonKho {

    List<TonKho> lst;
    IQuanLyHangNhap iQuanLyHangNhap;
    IQuanLyHangXuat iQuanLyHangXuat;
    SimpleDateFormat _SimpleDateFormat;
    TonKho tonKho = null;

    public QuanLyTonKho() {
        iQuanLyHangNhap = new QuanLyHangNhap();
        iQuanLyHangXuat = new QuanLyHangXuat();
        lst = new ArrayList<>();
        TreeSet<String> arrSolo = iQuanLyHangNhap.getListSolo();
        for (String soLo : arrSolo) {
            List<NhapHang> lstNhapHang = new ArrayList<>();
            lstNhapHang = iQuanLyHangNhap.searchList(3, soLo);
            for (NhapHang sp : lstNhapHang) {
                if (sp.getAmount() - sp.getAmountProductExport() > 0) {
                    tonKho = new TonKho();
                    tonKho.setAmount(sp.getAmount() - sp.getAmountProductExport());
                    tonKho.setIdProduct(sp.getIdProduct());
                    tonKho.setLotLo(soLo);
                    tonKho.setNameProduct(sp.getNameProduct());
                    tonKho.setNote(sp.getNote());
                    tonKho.setPrice(sp.getPrice());
                    tonKho.setIdUnit(sp.getUnit());
                    tonKho.setStatus(checkHanSuDung(sp.getExpiryDate()));
                    lst.add(tonKho);
                }
            }
        }
    }

    @Override
    public List<TonKho> getListTonKho() {
        return lst;
    }

    @Override
    public boolean sortListTonKho(String types) {
        switch (types) {
            case "Theo tên A-Z": {
                Collections.sort(lst, (o1, o2) -> {
                    return o1.getNameProduct().compareToIgnoreCase(o2.getNameProduct());
                });
                return true;
            }
            case "Theo tên Z-A": {
                Collections.sort(lst, (o1, o2) -> {
                    return o2.getNameProduct().compareToIgnoreCase(o1.getNameProduct());
                });
                return true;
            }
            case "Số lượng tăng dần": {
                Collections.sort(lst, (o1, o2) -> {
                    return o1.getAmount() - o2.getAmount();
                });
                return true;
            }
            case "Số lượng giảm dần": {
                Collections.sort(lst, (o1, o2) -> {
                    return o2.getAmount() - o1.getAmount();
                });
                return true;
            }
        }
        return false;
    }

    @Override
    public List<TonKho> searchList(int type, String values) {
        List<TonKho> getListSearch = new ArrayList<>();
        switch (type) {
            case 0: {
                for (TonKho x : lst) {
                    if (x.getIdProduct().equalsIgnoreCase(values)) {
                        getListSearch.add(x);
                    }
                }
                break;
            }
            case 1: {
                for (TonKho x : lst) {
                    if (x.getNameProduct().equalsIgnoreCase(values)) {
                        getListSearch.add(x);
                    }
                }
                break;
            }
            case 2: {
                for (TonKho x : lst) {
                    if (x.getAmount() == Integer.parseInt(values)) {
                        getListSearch.add(x);
                    }
                }
                break;
            }
            case 3: {
                for (TonKho x : lst) {
                    if (values.equals(x.getLotLo())) {
                        getListSearch.add(x);
                    }
                }
                break;
            }
        }
        return getListSearch;
    }

    @Override
    public boolean checkHanSuDung(Date hanSuDung) {
        _SimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        int hsd = Integer.parseInt(_SimpleDateFormat.format(hanSuDung));
        int dateNow = Integer.parseInt(_SimpleDateFormat.format(now));
        return hsd >= dateNow;
    }

}
