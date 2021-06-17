/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author NgocPJa
 */
public class Service {

    public static ILogin _ILogin = new Login();
    public static IQuanLyHangNhap _IQuanLyHangNhap = new QuanLyHangNhap();
    public static IQuanLyHangXuat _IQuanLyHangXuat = new QuanLyHangXuat();
    public static IQuanLyTonKho _IQuanLyTonKho = new QuanLyTonKho();

    public Service() {
        _ILogin = new Login();
        _IQuanLyHangNhap = new QuanLyHangNhap();
        _IQuanLyHangXuat = new QuanLyHangXuat();
        _IQuanLyTonKho = new QuanLyTonKho();
    }

}
