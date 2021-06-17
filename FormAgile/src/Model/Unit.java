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
public class Unit {

    private int idUnit;
    private String nameUnit;

    public Unit() {
    }

    public Unit(int idUnit, String nameUnit) {
        this.idUnit = idUnit;
        this.nameUnit = nameUnit;
    }

    public int getIdUnit() {
        return idUnit;
    }

    public void setIdUnit(int idUnit) {
        this.idUnit = idUnit;
    }

    public String getNameUnit() {
        return nameUnit;
    }

    public void setNameUnit(String nameUnit) {
        this.nameUnit = nameUnit;
    }

}
