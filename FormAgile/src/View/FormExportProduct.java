/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.QuanLyHangNhap;
import Controller.QuanLyHangXuat;
import Controller.QuanLyTonKho;
import Controller.Service;
import Model.NhapHang;
import Model.XuatHang;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NgocPJa
 */
public class FormExportProduct extends javax.swing.JFrame {

    private XuatHang xuatHang;
    private List<XuatHang> lstExport = new ArrayList<>();
    private FormMain _main = new FormMain();
    private DefaultTableModel model;
    private int lotLo = 0;
    private int idUnit = 0;
    private int idImport = 0;
    private List<NhapHang> lstNHTK = new ArrayList<>();
    private SimpleDateFormat _SimpleDateFormat;
    private Date _now = new Date();

    /**
     * Creates new form FormExport
     */
    public FormExportProduct() {
        setForm();
    }

    public FormExportProduct(FormMain main) {
        setForm();
        _main = main;
    }

    private void setForm() {
        initComponents();
        btnTongTien.setVisible(false);
        lstNHTK = _main.iQuanLyHangnhap.getListConHang();
        setLocationRelativeTo(this);
        setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        tbXuatHang.setModel(model);
        setColTable();
        setDateNow();
        setIDProduct();
        setSpinAmount();
    }

    private void setSpinAmount() {
        ((JSpinner.DefaultEditor) spinSoLuong.getEditor()).getTextField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String getAmout = ((JSpinner.DefaultEditor) spinSoLuong.getEditor()).getTextField().getText();
                if (!lblGiaTien.getText().isEmpty()) {
                    if (Integer.parseInt(getAmout) > 0) {
                        double getSoLuong = Double.parseDouble(getAmout);
                        double getGiaTien = Double.parseDouble(lblGiaTien.getText());
                        lblThanhTien.setText(String.format("%15.2f", getSoLuong * getGiaTien));
                    } else {
                        lblThanhTien.setText("");
                    }
                }
            }
        });
    }

    private void setDateNow() {
        _SimpleDateFormat = new SimpleDateFormat("yyyy-dd-MM hh:mm");
        lblNgayXuat.setText(_SimpleDateFormat.format(_now));
        _SimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        lotLo = _main.iQuanLyHangXuat.getLotlo();
        lblLotlo.setText(_SimpleDateFormat.format(_now) + lotLo);
    }

    private void setColTable() {
        String[] arrCol = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá", "Thành tiền", "Hạn sử dụng", "Vị trí", "Ghi chú", "Xóa"};
        for (String x : arrCol) {
            model.addColumn(x);
        }
    }

    private void setIDProduct() {
        TreeSet<String> lstIDProduct = _main.iQuanLyHangXuat.getListIDProduct();
        String[] arr = new String[lstIDProduct.size()];
        int i = 0;
        for (String x : lstIDProduct) {
            arr[i++] = x;
        }
        cbcMaSP.setModel(new DefaultComboBoxModel<>(arr));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnKetThuc = new javax.swing.JButton();
        btnXuat = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaNote = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbXuatHang = new javax.swing.JTable();
        lblNgayXuat = new javax.swing.JLabel();
        lbl_Title = new javax.swing.JLabel();
        lblLotlo = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbcMaSP = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        lblTenSP = new javax.swing.JLabel();
        spinSoLuong = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        lblDonVi = new javax.swing.JLabel();
        lblGiaTien = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblThanhTien = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jlabel10 = new javax.swing.JLabel();
        lblSoluong = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbcHanSuDung = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        lblViTri = new javax.swing.JLabel();
        btnTongTien = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Xuất hàng");

        btnKetThuc.setBackground(new java.awt.Color(255, 0, 0));
        btnKetThuc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnKetThuc.setForeground(new java.awt.Color(255, 255, 255));
        btnKetThuc.setText("Kết thúc");
        btnKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKetThucActionPerformed(evt);
            }
        });

        btnXuat.setBackground(new java.awt.Color(51, 0, 255));
        btnXuat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXuat.setForeground(new java.awt.Color(255, 255, 255));
        btnXuat.setText("Xuất");
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(51, 0, 255));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Ghi chú:");

        txaNote.setColumns(20);
        txaNote.setRows(5);
        jScrollPane1.setViewportView(txaNote);

        tbXuatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã lô", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Thành tiền", "Người xuất", "Xóa"
            }
        ));
        tbXuatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbXuatHangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbXuatHang);

        lblNgayXuat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNgayXuat.setForeground(new java.awt.Color(255, 0, 51));
        lblNgayXuat.setText("02-02-2020");

        lbl_Title.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbl_Title.setForeground(new java.awt.Color(255, 0, 51));
        lbl_Title.setText("Xuất hàng");

        lblLotlo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Mã lô:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Mã sản phẩm:");

        cbcMaSP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbcMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcMaSPActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Tên sản phẩm:");

        lblTenSP.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        spinSoLuong.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinSoLuong.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinSoLuongStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Số lượng:");

        lblDonVi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        lblGiaTien.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Giá tiền:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Thành tiền:");

        lblThanhTien.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Ngày xuất");

        jlabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlabel10.setText("Tối đa:");

        lblSoluong.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Hạn sử dụng:");

        cbcHanSuDung.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbcHanSuDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcHanSuDungActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Vị trí:");

        lblViTri.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        btnTongTien.setBackground(new java.awt.Color(255, 255, 153));
        btnTongTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTongTien.setForeground(new java.awt.Color(255, 51, 51));
        btnTongTien.setText("jButton1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cbcHanSuDung, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(lblViTri, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(lblThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblNgayXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(19, 19, 19)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jlabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spinSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblSoluong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblDonVi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(btnKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnTongTien))))
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(72, 72, 72)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(350, 350, 350)
                            .addComponent(lbl_Title, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(75, 75, 75)
                                    .addComponent(lblLotlo, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(cbcMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGap(40, 40, 40)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(69, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(228, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3))
                    .addComponent(cbcHanSuDung, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spinSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDonVi)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabel10)
                    .addComponent(lblSoluong))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGiaTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(lblThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(lblViTri, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblNgayXuat))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTongTien)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(63, 63, 63)
                    .addComponent(lbl_Title)
                    .addGap(26, 26, 26)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 97, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(lblLotlo))
                            .addGap(8, 8, 8)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel2))
                                .addComponent(cbcMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(8, 8, 8)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12)
                                .addComponent(lblTenSP))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        btnExportProduct(this, cbcMaSP, lblTenSP, spinSoLuong, txaNote, lblViTri);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Đồng ý xuất hàng?", "Xuất hàng",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (_main.iQuanLyHangXuat.exportProduct(lstExport)) {
                Service._IQuanLyHangNhap = new QuanLyHangNhap();
                Service._IQuanLyHangXuat = new QuanLyHangXuat();
                _main.iQuanLyTonKho = new QuanLyTonKho();
                _main.loadTableTonKho();
                _main.loadTableXuatHang();
                this.dispose();
            }
        }
    }//GEN-LAST:event_btnXuatActionPerformed

    private void btnKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKetThucActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Hủy xuất hàng?", "Hủy xuất", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_btnKetThucActionPerformed

    List<NhapHang> lstProductByIDProduct;
    int indexClickExpiryDate = 0;

    private List<NhapHang> getListByIDProduct(String idProduct) {
        List<NhapHang> lstIDProduct = new ArrayList<>();
        for (NhapHang x : lstNHTK) {
            if (x.getIdProduct().equalsIgnoreCase(idProduct) && x.getAmount() > 0) {
                lstIDProduct.add(x);
            }
        }
        return lstIDProduct;
    }
    private void cbcMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcMaSPActionPerformed
        loadHSD();
    }//GEN-LAST:event_cbcMaSPActionPerformed

    private void loadHSD() {
        lstProductByIDProduct = new ArrayList<>();
        String inputIDProduct = cbcMaSP.getSelectedItem().toString();
        lstProductByIDProduct = getListByIDProduct(inputIDProduct);
        if (!lstProductByIDProduct.isEmpty()) {
            Collections.sort(lstProductByIDProduct, (o1, o2) -> {
                Date date1 = o1.getExpiryDate();
                Date date2 = o2.getExpiryDate();
                _SimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                int getDate1 = Integer.parseInt(_SimpleDateFormat.format(date1));
                int getDate2 = Integer.parseInt(_SimpleDateFormat.format(date2));
                return getDate2 - getDate1;
            });
            String nameProduct = _main.iQuanLyHangnhap.getNameProduct(inputIDProduct);
            indexClickExpiryDate = 0;
            setInfoProduct();
            lblTenSP.setText(nameProduct);
            String[] arrHanSuDung = new String[lstProductByIDProduct.size()];
            _SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < lstProductByIDProduct.size(); i++) {
                arrHanSuDung[i] = _SimpleDateFormat.format(lstProductByIDProduct.get(i).getExpiryDate());
            }
            cbcHanSuDung.setModel(new DefaultComboBoxModel<>(arrHanSuDung));
        }
    }

    private void cbcHanSuDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcHanSuDungActionPerformed
        indexClickExpiryDate = cbcHanSuDung.getSelectedIndex();
        if (indexClickExpiryDate > -1) {
            setInfoProduct();
        }
    }//GEN-LAST:event_cbcHanSuDungActionPerformed

    private void spinSoLuongStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinSoLuongStateChanged
        Object amount = spinSoLuong.getValue();
        String getAmout = amount.toString();
        if (!lblGiaTien.getText().isEmpty()) {
            if (Integer.parseInt(getAmout) > 0) {
                double getSoLuong = Double.parseDouble(getAmout);
                double getGiaTien = Double.parseDouble(lblGiaTien.getText());
                lblThanhTien.setText(String.format("%15.2f", getSoLuong * getGiaTien));
            } else {
                lblThanhTien.setText("");
            }
        }
    }//GEN-LAST:event_spinSoLuongStateChanged

    private void tbXuatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbXuatHangMouseClicked
        Point p = evt.getPoint();
        int row = tbXuatHang.rowAtPoint(p);
        int col = tbXuatHang.columnAtPoint(p);
        if (tbXuatHang.getValueAt(row, col).toString().equalsIgnoreCase("X")) {
            if (JOptionPane.showConfirmDialog(this, "Xóa sản phẩm?", "Xóa sản phẩm",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                xuatHang = new XuatHang();
                xuatHang = lstExport.get(row);
                for (NhapHang x : lstNHTK) {
                    if (x.getIdImport() == xuatHang.getIdImport()) {
                        int tong = x.getAmount() + xuatHang.getAmount();
                        x.setAmount(tong);
                        break;
                    }
                }
                lstExport.remove(row);
                loadHSD();
                loadTable();
            }
        }
    }//GEN-LAST:event_tbXuatHangMouseClicked

    private void setInfoProduct() {
        lblDonVi.setText(_main.iQuanLyHangnhap.getNameUnit(lstProductByIDProduct.get(indexClickExpiryDate).getUnit()));
        idUnit = lstProductByIDProduct.get(indexClickExpiryDate).getUnit();
        lblSoluong.setText((lstProductByIDProduct.get(indexClickExpiryDate).getAmount()) + "");
        lblGiaTien.setText(lstProductByIDProduct.get(indexClickExpiryDate).getPrice() + "");
        lblViTri.setText(lstProductByIDProduct.get(indexClickExpiryDate).getLocation());
        idImport = lstProductByIDProduct.get(indexClickExpiryDate).getIdImport();
        Object amount = spinSoLuong.getValue();
        String getAmout = amount.toString();
        lblThanhTien.setText(String.format("%15.2f", Integer.parseInt(getAmout) * lstProductByIDProduct.get(indexClickExpiryDate).getPrice()));
    }

    // code chức năng thêm sản phẩm xuất hàng form xuất hàng
    private void btnExportProduct(JFrame formExport,
            JComboBox cbcIDProduct, JLabel lblNameProduc, JSpinner spinAmount,
            JTextArea txaNote, JLabel lblLocation) {
        //        String lotImport = cbcLotImport.getSelectedItem().toString();
        String idProduct = cbcIDProduct.getSelectedItem().toString();
        String nameProduct = lblNameProduc.getText();
        Object values = spinAmount.getValue();
        String amountProduct = values.toString();
        String noteProduct = new String(txaNote.getText());
        StringBuilder sp = new StringBuilder();

        if (idProduct.isEmpty()) {
            sp.append("Mã sản phẩm không được để trống!\n");
        }

        if (amountProduct.isEmpty()) {
            sp.append("Số lượng sản phẩm không được để trống!\n");
        } else {
            if (!amountProduct.matches("[0-9]+")) {
                sp.append("Số lượng sản phẩm phải là một số nguyên dương!\n");
            } else {
                if (Integer.parseInt(amountProduct) < 1) {
                    sp.append("Số lượng sản phẩm phải >= 1 sản phẩm!\n");
                } else {
                    if (Integer.parseInt(amountProduct) > lstNHTK.get(getIndex(idImport)).getAmount()) {
                        sp.append("Số lượng không được nhiêu hơn số lượng xuất tối đa!");
                    }
                }
            }
        }
        if (sp.length()
                == 0) {
            xuatHang = new XuatHang(lotLo, 0, _now, lblLocation.getText(), noteProduct,
                    idImport, idProduct, nameProduct, Integer.parseInt(amountProduct), idUnit);
            for (XuatHang x : lstExport) {
                if (xuatHang.getIdProduct().equalsIgnoreCase(x.getIdProduct())
                        && xuatHang.getNameProduct().equalsIgnoreCase(x.getNameProduct())
                        && xuatHang.getLocation().equals(x.getLocation())
                        && xuatHang.getNote().equalsIgnoreCase(x.getNote())
                        && xuatHang.getIdImport() == xuatHang.getIdImport()) {
                    x.setAmount(x.getAmount() + xuatHang.getAmount());
                    xuatHang = null;
                    break;
                }
            }
            if (xuatHang != null) {
                lstExport.add(xuatHang);
            }
//            JOptionPane.showMessageDialog(formExport, "Thêm thành công ^^");
            int i = getIndex(idImport);
            int ton = lstNHTK.get(i).getAmount() - Integer.parseInt(amountProduct);// số lượng còn lại
            lstNHTK.get(i).setAmount(ton);
            lblSoluong.setText(lstNHTK.get(i).getAmount() + "");
            loadHSD();
            loadTable();
        } else {
            JOptionPane.showMessageDialog(formExport, sp);
        }
    }

    private int getIndex(int iDImport) {
        for (int i = 0; i < lstNHTK.size(); i++) {
            if (lstNHTK.get(i).getIdImport() == iDImport) {
                return i;
            }
        }
        return -2;
    }

    private void loadTable() {
        model.setRowCount(0);
        double tongTien = 0;
        for (XuatHang x : lstExport) {
            model.addRow(new Object[]{
                x.getIdProduct(), x.getNameProduct(),
                x.getAmount() + " " + _main.iQuanLyHangnhap.getNameUnit(idUnit),
                _main.iQuanLyHangnhap.getPriceProduct(x.getIdImport()),
                String.format("%15.2f", _main.iQuanLyHangnhap.getPriceProduct(x.getIdImport()) * x.getAmount()),
                cbcHanSuDung.getSelectedItem().toString(),
                x.getLocation(), x.getNote(), "X"
            });
            tongTien += _main.iQuanLyHangnhap.getPriceProduct(x.getIdImport()) * x.getAmount();
        }
        btnTongTien.setVisible(true);
        btnTongTien.setEnabled(false);
        btnTongTien.setText("Tổng tiền: " + String.format("%15.2f", tongTien));
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormExportProduct.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormExportProduct.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormExportProduct.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormExportProduct.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormExportProduct().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKetThuc;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTongTien;
    private javax.swing.JButton btnXuat;
    private javax.swing.JComboBox<String> cbcHanSuDung;
    private javax.swing.JComboBox<String> cbcMaSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jlabel10;
    private javax.swing.JLabel lblDonVi;
    private javax.swing.JLabel lblGiaTien;
    private javax.swing.JLabel lblLotlo;
    private javax.swing.JLabel lblNgayXuat;
    private javax.swing.JLabel lblSoluong;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblViTri;
    private javax.swing.JLabel lbl_Title;
    private javax.swing.JSpinner spinSoLuong;
    private javax.swing.JTable tbXuatHang;
    private javax.swing.JTextArea txaNote;
    // End of variables declaration//GEN-END:variables
}
