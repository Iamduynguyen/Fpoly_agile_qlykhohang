/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TonKho;
import java.util.Date;
import java.util.List;

/**
 *
 * @author NgocPJa
 */
public interface IQuanLyTonKho {

    List<TonKho> getListTonKho();

//    boolean checkHanSuDung(String hanSuDung);
    boolean checkHanSuDung(Date hanSuDung);

    boolean sortListTonKho(String types);

    List<TonKho> searchList(int type, String values);

}
