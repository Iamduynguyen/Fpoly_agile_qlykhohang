/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ILogin;
import Model.NhapHang;
import Model.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Controller.IQuanLyHangNhap;
import Controller.IQuanLyHangXuat;
import Controller.IQuanLyTonKho;
import Controller.QuanLyTonKho;
import Controller.Service;
import Model.TonKho;
import Model.XuatHang;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author dell
 */
public class FormMain extends javax.swing.JFrame {

    private User _User = null;
    protected IQuanLyHangNhap iQuanLyHangnhap = Service._IQuanLyHangNhap;
    protected IQuanLyHangXuat iQuanLyHangXuat = Service._IQuanLyHangXuat;
    protected IQuanLyTonKho iQuanLyTonKho = Service._IQuanLyTonKho;
    protected ILogin iLogin = Service._ILogin;
    private FormExportProduct formExportProduct = null;
    private FormImportProduct formImportProduct = null;
    private FormEditProduct formEditProduct = null;
    private Form_ListUser form_ListUser = null;
    private Form_Pwchange form_Pwchange = null;
    protected static Form_addUser form_addUser = null;
    protected static Form_repairUser form_repairUser = null;
    private DefaultTableModel modelTonKho;
    private DefaultTableModel modelHangNhap;
    private DefaultTableModel modelHangXuat;

    /**
     * Creates new form MainGui
     */
    public FormMain() {
        setShowForm();
    }

    public FormMain(User user) {
        setShowForm();
        _User = user;
        if (_User.getRole() == 1) {
            btnQuanLyTaiKhoan.setText("Quản lý tài khoản");
            btnQuanLyTaiKhoan.setVisible(true);
        } else if (_User.getRole() == 2) {
            btnQuanLyTaiKhoan.setVisible(false);
        } else {
            btnQuanLyTaiKhoan.setVisible(false);
        }
        lblTaiKhoan.setText("Tài khoản: " + _User.getUsername());
        lblChucVu.setText("Chức vụ: " + iLogin.getNameRole(_User.getRole()));
    }

    private void setShowForm() {
        initComponents();
        setLocationRelativeTo(this);
        setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0); //calling the method is a must
            }
        });
        modelTonKho = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        modelHangNhap = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        modelHangXuat = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        tbTonKho.setModel(modelTonKho);
        tbHangNhap.setModel(modelHangNhap);
        tbHangXuat.setModel(modelHangXuat);
        setColTable();
        loadTableTonKho();
        loadTableNhapHang();
        loadTableXuatHang();
        setCbcSapXep();
    }

    private void setColTable() {
        String[] arrTonKho = {"STT", "Số lô", "Mã sản phẩm", "Tên sản phẩm", "Số lượng tồn", "Đơn giá", "Trạng thái", "Ghi chú"};
        String[] arrHangNhap = {"STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng nhập", "Đơn giá", "Ngày nhập", "Ghi chú"};
        String[] arrHangXuat = {"STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng xuất", "Ngày xuất", "Ghi chú"};
        for (String x : arrTonKho) {
            modelTonKho.addColumn(x);
        }
        for (String x : arrHangNhap) {
            modelHangNhap.addColumn(x);
        }
        for (String x : arrHangXuat) {
            modelHangXuat.addColumn(x);
        }
    }

    private void setCbcSapXep() {
        cbcSapXep.setModel(new DefaultComboBoxModel<>(new String[]{
            "Theo tên A-Z", "Theo tên Z-A", "Số lượng tăng dần", "Số lượng giảm dần"
        }));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        btn_lsu1 = new javax.swing.JButton();
        pn_head = new javax.swing.JPanel();
        lbl_Title = new javax.swing.JLabel();
        btnNhapHang = new javax.swing.JButton();
        btnXuatHang = new javax.swing.JButton();
        btnQuanLy = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        pn_tb = new javax.swing.JPanel();
        tab = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTonKho = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbHangNhap = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHangXuat = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cbcSapXep = new javax.swing.JComboBox<>();
        btnQuanLyTaiKhoan = new javax.swing.JButton();
        lblChucVu = new javax.swing.JLabel();
        lblTaiKhoan = new javax.swing.JLabel();
        btnDoiMatKhauQTV = new javax.swing.JButton();
        btnDangXuat1 = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        jMenuItem1.setText("jMenuItem1");

        btn_lsu1.setBackground(new java.awt.Color(0, 51, 204));
        btn_lsu1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_lsu1.setForeground(new java.awt.Color(255, 255, 255));
        btn_lsu1.setText("Lịch sử");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý kho hàng");
        setBackground(new java.awt.Color(255, 255, 255));

        lbl_Title.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbl_Title.setForeground(new java.awt.Color(255, 0, 51));
        lbl_Title.setText("QUẢN LÝ KHO HÀNG");

        javax.swing.GroupLayout pn_headLayout = new javax.swing.GroupLayout(pn_head);
        pn_head.setLayout(pn_headLayout);
        pn_headLayout.setHorizontalGroup(
            pn_headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_headLayout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(lbl_Title)
                .addGap(84, 84, 84))
        );
        pn_headLayout.setVerticalGroup(
            pn_headLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_headLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Title, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnNhapHang.setBackground(new java.awt.Color(0, 0, 204));
        btnNhapHang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNhapHang.setForeground(new java.awt.Color(255, 255, 255));
        btnNhapHang.setText("Nhập hàng");
        btnNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapHangActionPerformed(evt);
            }
        });

        btnXuatHang.setBackground(new java.awt.Color(0, 0, 204));
        btnXuatHang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXuatHang.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatHang.setText("Xuất hàng");
        btnXuatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatHangActionPerformed(evt);
            }
        });

        btnQuanLy.setBackground(new java.awt.Color(0, 0, 204));
        btnQuanLy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnQuanLy.setForeground(new java.awt.Color(255, 255, 255));
        btnQuanLy.setText("Sửa");
        btnQuanLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyActionPerformed(evt);
            }
        });

        btnTimKiem.setBackground(new java.awt.Color(0, 0, 204));
        btnTimKiem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnDangXuat.setBackground(new java.awt.Color(255, 0, 51));
        btnDangXuat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        pn_tb.setBackground(new java.awt.Color(255, 255, 218));

        tbTonKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá tiền", "Trạng thái"
            }
        ));
        jScrollPane1.setViewportView(tbTonKho);

        tab.addTab("Tồn kho", jScrollPane1);

        tbHangNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã lô", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá tiền", "Ngày nhập", "Trạng thái", "Người nhập"
            }
        ));
        jScrollPane3.setViewportView(tbHangNhap);

        tab.addTab("Hàng nhập", jScrollPane3);

        tbHangXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã lô", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Trạng thái", "Ngày xuất", "Người xuất"
            }
        ));
        jScrollPane2.setViewportView(tbHangXuat);

        tab.addTab("Hàng xuất", jScrollPane2);

        javax.swing.GroupLayout pn_tbLayout = new javax.swing.GroupLayout(pn_tb);
        pn_tb.setLayout(pn_tbLayout);
        pn_tbLayout.setHorizontalGroup(
            pn_tbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 959, Short.MAX_VALUE)
            .addGroup(pn_tbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE))
        );
        pn_tbLayout.setVerticalGroup(
            pn_tbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 514, Short.MAX_VALUE)
            .addGroup(pn_tbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pn_tbLayout.createSequentialGroup()
                    .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Sắp xếp");

        cbcSapXep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbcSapXepMouseClicked(evt);
            }
        });
        cbcSapXep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcSapXepActionPerformed(evt);
            }
        });

        btnQuanLyTaiKhoan.setBackground(new java.awt.Color(0, 0, 204));
        btnQuanLyTaiKhoan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnQuanLyTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnQuanLyTaiKhoan.setText("Quản lý tài khoản");
        btnQuanLyTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanLyTaiKhoanActionPerformed(evt);
            }
        });

        lblChucVu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblChucVu.setText("Chức vụ:");

        lblTaiKhoan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTaiKhoan.setText("Tài khoản:");

        btnDoiMatKhauQTV.setBackground(new java.awt.Color(0, 0, 204));
        btnDoiMatKhauQTV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDoiMatKhauQTV.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiMatKhauQTV.setText("Đổi mật khẩu");
        btnDoiMatKhauQTV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauQTVActionPerformed(evt);
            }
        });

        btnDangXuat1.setBackground(new java.awt.Color(51, 51, 255));
        btnDangXuat1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDangXuat1.setForeground(new java.awt.Color(255, 255, 255));
        btnDangXuat1.setText("LoadTable");
        btnDangXuat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuat1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(cbcSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(lblTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(lblChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(btnQuanLyTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pn_tb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(228, 228, 228)
                            .addComponent(pn_head, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnNhapHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnXuatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDangXuat1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnDoiMatKhauQTV)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pn_head, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbcSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnQuanLyTaiKhoan)
                            .addComponent(lblChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDoiMatKhauQTV))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pn_tb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(btnNhapHang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXuatHang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuanLy)
                        .addGap(11, 11, 11)
                        .addComponent(btnTimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDangXuat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDangXuat1)))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuanLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyActionPerformed
        String inputLotLo = JOptionPane.showInputDialog("Nhập số lô muốn sửa:");
        if (inputLotLo == null || inputLotLo.isEmpty()) {
            return;
        }
        if (!inputLotLo.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Số lô phải là một dãy các số nguyên không chứa ký tự đặc biệt!");
        } else {
            if (iQuanLyHangnhap.checkLotlo(inputLotLo)) {
                JOptionPane.showMessageDialog(this, "Đã tìm thấy những sản phẩm trong lô " + inputLotLo);
                if (formEditProduct != null) {
                    formEditProduct.dispose();
                }
                formEditProduct = new FormEditProduct(this, inputLotLo);
                formEditProduct.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Số lô nhập vào không tồn tại!");
            }
        }
    }//GEN-LAST:event_btnQuanLyActionPerformed

    private void btnQuanLyTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanLyTaiKhoanActionPerformed
        if (_User.getRole() == 1) {
            if (form_ListUser != null) {
                form_ListUser.dispose();
            }
            form_ListUser = new Form_ListUser(_User);
            form_ListUser.setVisible(true);
        }
    }//GEN-LAST:event_btnQuanLyTaiKhoanActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        if (formExportProduct != null) {
            formExportProduct.setVisible(false);
        }
        if (formImportProduct != null) {
            formImportProduct.setVisible(false);
        }
        if (formEditProduct != null) {
            formEditProduct.setVisible(false);
        }
        if (form_ListUser != null) {
            form_ListUser.setVisible(false);
        }
        if (form_Pwchange != null) {
            form_Pwchange.setVisible(false);
        }
        if (form_addUser != null) {
            form_addUser.setVisible(false);
        }
        if (form_repairUser != null) {
            form_repairUser.setVisible(false);
        }
        new FormLogin().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        btnSearchProduct(this);
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnXuatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatHangActionPerformed
        if (formExportProduct != null) {
            formExportProduct.dispose();
        }
        formExportProduct = new FormExportProduct(this);
        formExportProduct.setVisible(true);
    }//GEN-LAST:event_btnXuatHangActionPerformed

    private void btnNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapHangActionPerformed
        if (formImportProduct != null) {
            formImportProduct.dispose();
        }
        formImportProduct = new FormImportProduct(this);
        formImportProduct.setVisible(true);
    }//GEN-LAST:event_btnNhapHangActionPerformed

    private void cbcSapXepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbcSapXepMouseClicked

    }//GEN-LAST:event_cbcSapXepMouseClicked

    private void cbcSapXepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcSapXepActionPerformed
        String luaChonSapXep = cbcSapXep.getSelectedItem().toString();
        if (iQuanLyTonKho.sortListTonKho(luaChonSapXep)) {
            List<TonKho> lstTonKho = new ArrayList<>();
            lstTonKho = iQuanLyTonKho.getListTonKho();
            loadTableTonKho(lstTonKho);
        }
    }//GEN-LAST:event_cbcSapXepActionPerformed

    private void btnDoiMatKhauQTVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauQTVActionPerformed
        if (form_Pwchange != null) {
            form_Pwchange.dispose();
        }
        form_Pwchange = new Form_Pwchange(_User);
        form_Pwchange.setVisible(true);
    }//GEN-LAST:event_btnDoiMatKhauQTVActionPerformed

    private void btnDangXuat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuat1ActionPerformed
        iQuanLyTonKho = new QuanLyTonKho();
        loadTableTonKho();
    }//GEN-LAST:event_btnDangXuat1ActionPerformed

    protected void loadTableTonKho() {
        List<TonKho> lstTonKho = new ArrayList<>();
        lstTonKho = iQuanLyTonKho.getListTonKho();
        loadTableTonKho(lstTonKho);
    }

    private void loadTableTonKho(List<TonKho> lstTonKho) {
        modelTonKho.setRowCount(0);
        int stt = 0;
        for (TonKho x : lstTonKho) {
            modelTonKho.addRow(new Object[]{
                ++stt, x.getLotLo(), x.getIdProduct(), x.getNameProduct(),
                x.getAmount() + " " + iQuanLyHangnhap.getNameUnit(x.getIdUnit()),
                x.getPrice(), x.isStatus() ? "Còn hạn" : "Hết hạn", x.getNote()
            });
        }
    }

    protected void loadTableNhapHang() {
        int sttHangNhap = 0;
        modelHangNhap.setRowCount(0);
        for (NhapHang x : iQuanLyHangnhap.getList()) {
            modelHangNhap.addRow(new Object[]{
                ++sttHangNhap, x.getIdProduct(), x.getNameProduct(),
                x.getAmount() + " " + iQuanLyHangnhap.getNameUnit(x.getUnit()),
                x.getPrice(), x.getAddDate(), x.getNote()
            });
        }
    }

    protected void loadTableXuatHang() {
        int sttHangXuat = 0;
        modelHangXuat.setRowCount(0);
        for (XuatHang x : iQuanLyHangXuat.getList()) {
            modelHangXuat.addRow(new Object[]{
                ++sttHangXuat, x.getIdProduct(), x.getNameProduct(),
                x.getAmount() + " " + iQuanLyHangnhap.getNameUnit(x.getUnit()),
                x.getShippingDate(), x.getNote()
            });
        }
    }

    // code chức năng tìm kiếm sản phẩm
    private void btnSearchProduct(JFrame formMain) {
        String searchType[] = {"Mã SP", "Tên SP", "Số lượng", "Số lô"};
        int i = JOptionPane.showOptionDialog(formMain, "Tìm kiếm:", "Tìm kiếm",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null,
                searchType, null);
        if (i < 0) {
            return;
        }
        String input = JOptionPane.showInputDialog("Nhập " + searchType[i].toLowerCase() + " muốn tìm:");
        StringBuilder spER = new StringBuilder();
        if (input == null) {
            return;
        }
        if (!input.isEmpty()) {
            switch (i) {
                case 0: {
                    if (!input.matches("[a-zA-Z0-9]+")) {
                        spER.append("Mã sản phẩm không được chứa các ký tự đặc biệt!");
                    }
                    break;
                }
                case 1: {
                    for (char x : input.toCharArray()) {
                        if ((x + "").matches("\\p{Punct}")) {
                            spER.append("Mã sản phẩm không được chứa các ký tự đặc biệt!");
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    if (!input.matches("[0-9]+")) {
                        spER.append("Chỉ được phép nhập số lượng là số nguyên!");
                    }
                    break;
                }
                case 3: {
                    if (!input.matches("[0-9]+")) {
                        spER.append("Chỉ được phép nhập số lô là số nguyên!");
                    }
                    break;
                }
            }
        }
        if (!input.isEmpty() && spER.length() == 0) {
            List<TonKho> lstSearch = new ArrayList<>();
            lstSearch = iQuanLyTonKho.searchList(i, input);
            if (lstSearch.size() == 0) {
                JOptionPane.showMessageDialog(formMain, "Không tồn tại sản phẩm có "
                        + searchType[i].toLowerCase() + " " + input.toUpperCase());
            } else {
                loadTableTonKho(lstSearch);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnDangXuat1;
    private javax.swing.JButton btnDoiMatKhauQTV;
    private javax.swing.JButton btnNhapHang;
    private javax.swing.JButton btnQuanLy;
    private javax.swing.JButton btnQuanLyTaiKhoan;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXuatHang;
    private javax.swing.JButton btn_lsu1;
    private javax.swing.JComboBox<String> cbcSapXep;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lbl_Title;
    private javax.swing.JPanel pn_head;
    private javax.swing.JPanel pn_tb;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tbHangNhap;
    private javax.swing.JTable tbHangXuat;
    private javax.swing.JTable tbTonKho;
    // End of variables declaration//GEN-END:variables
}
