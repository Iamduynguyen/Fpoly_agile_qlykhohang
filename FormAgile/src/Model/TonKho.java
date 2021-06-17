/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author NgocPJa
 */
public class TonKho {

    private String lotLo;
    private String idProduct;
    private String nameProduct;
    private int amount;
    private int idUnit;
    private double price;
    private boolean status;
    private String note;

    public TonKho() {
    }

    public TonKho(String lotLo, String idProduct, String nameProduct, int amount, int idUnit, double price, boolean status, String note) {
        this.lotLo = lotLo;
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.amount = amount;
        this.idUnit = idUnit;
        this.price = price;
        this.status = status;
        this.note = note;
    }

    public int getIdUnit() {
        return idUnit;
    }

    public void setIdUnit(int idUnit) {
        this.idUnit = idUnit;
    }

    public String getLotLo() {
        return lotLo;
    }

    public void setLotLo(String lotLo) {
        this.lotLo = lotLo;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
