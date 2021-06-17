package Model;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author NgocPJa
 */
public class XuatHang extends SanPham {

    private int lotExport;
    private int lotImport;
    private Date shippingDate;
    private String location;
    private String note;
    private int idImport;

    public XuatHang() {
    }

    public XuatHang(int lotExport, int lotImport, Date shippingDate, String location, String note, int idImport) {
        this.lotExport = lotExport;
        this.lotImport = lotImport;
        this.shippingDate = shippingDate;
        this.location = location;
        this.note = note;
        this.idImport = idImport;
    }

    public XuatHang(int lotExport, int lotImport, Date shippingDate, String location, String note, int idImport, String idProduct, String nameProduct, int amount, int unit) {
        super(idProduct, nameProduct, amount, unit);
        this.lotExport = lotExport;
        this.lotImport = lotImport;
        this.shippingDate = shippingDate;
        this.location = location;
        this.note = note;
        this.idImport = idImport;
    }

    public int getIdImport() {
        return idImport;
    }

    public void setIdImport(int idImport) {
        this.idImport = idImport;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLotImport() {
        return lotImport;
    }

    public void setLotImport(int lotImport) {
        this.lotImport = lotImport;
    }

    public int getLotExport() {
        return lotExport;
    }

    public void setLotExport(int lotExport) {
        this.lotExport = lotExport;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
