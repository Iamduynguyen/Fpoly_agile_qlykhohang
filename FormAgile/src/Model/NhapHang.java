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
public class NhapHang extends SanPham {

    private int lotImport;
    private double price;
    private Date expiryDate;
    private Date addDate;
    private String location;
    private String note;
    private int amountProductExport = 0;
    private int idImport;

    public NhapHang() {
    }

    public NhapHang(int lotImport, double price, Date expiryDate, Date addDate, String location, String note, int idImport) {
        this.lotImport = lotImport;
        this.price = price;
        this.expiryDate = expiryDate;
        this.addDate = addDate;
        this.location = location;
        this.note = note;
        this.idImport = idImport;
    }

    public NhapHang(int lotImport, double price, Date expiryDate, Date addDate, String location, String note, int idImport, String idProduct, String nameProduct, int amount, int unit) {
        super(idProduct, nameProduct, amount, unit);
        this.lotImport = lotImport;
        this.price = price;
        this.expiryDate = expiryDate;
        this.addDate = addDate;
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

    public int getAmountProductExport() {
        return amountProductExport;
    }

    public void setAmountProductExport(int amountProductExport) {
        this.amountProductExport = amountProductExport;
    }

    public int getLotImport() {
        return lotImport;
    }

    public void setLotImport(int lotImport) {
        this.lotImport = lotImport;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
