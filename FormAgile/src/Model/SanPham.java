package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author NgocPJa
 */
public class SanPham {

    private String idProduct;
    private String nameProduct;
    private int amount;
    private int unit;

    public SanPham() {
    }

    public SanPham(String idProduct, String nameProduct, int amount, int unit) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.amount = amount;
        this.unit = unit;
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

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

}
