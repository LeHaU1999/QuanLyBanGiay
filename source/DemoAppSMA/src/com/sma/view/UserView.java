/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sma.view;

import com.sma.config.JDBCConnection;
import com.sma.controller.ProductController;
import com.sma.controller.RoleController;
import com.sma.controller.UserController;
import com.sma.entity.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ASUS
 */
public class UserView extends javax.swing.JFrame {

    public UserView() {
        initComponents();
        loadDataToTable();
        loadDataToCombobox();
        buttonEnable();
        textfieldEnable();
        this.setLocationRelativeTo(null);
        getId();
    }

    public void getId() {
        ListEmpView listEmp = new ListEmpView();
        String id = listEmp.id_Emp;
        this.setVisible(false);
        this.setVisible(true);
    }

    private void loadDataToTable() {
        List<User> listUser = UserController.getAllUser();
        DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
        Object[] object = new Object[5];
        for (int i = 0; i < listUser.size(); i++) {
            object[0] = listUser.get(i).getId();
            object[1] = listUser.get(i).getId_employee();
            object[2] = listUser.get(i).getAccount();
            object[3] = listUser.get(i).getPassword();
            object[4] = listUser.get(i).getId_role();
            model.addRow(object);
        }
    }

    ArrayList<String> listCombobox = UserController.loadDataToCombobox();

    public void loadDataToCombobox() {
        for (String item : listCombobox) {
            cboRole.addItem(item.toString());
        }
    }

    public void autoId() {
        Connection conn = null;
        try {
            conn = JDBCConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT Max(id_user) FROM  users");
            rs.next();
            if (rs.getString("Max(id_user)") == null) {
                txtID.setText("USER001");
            } else {
                int id = Integer.parseInt(rs.getString("Max(id_user)").substring(6, rs.getString("Max(id_user)").length()));
                id++;
                txtID.setText("USER00" + id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buttonEnable() {
        btnSave.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
    }

    public void textfieldEnable() {
        txtID.setText("");
        txtMaNV.setText("");
        txtAccount.setText("");
        txtPassword.setText("");
        cboRole.setSelectedItem(null);

        txtID.setEnabled(true);
        txtMaNV.setEnabled(true);
        txtAccount.setEnabled(true);
        txtPassword.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        txtAccount = new javax.swing.JTextField();
        lblGoToHomeScreen = new javax.swing.JLabel();
        btnAddNewUser = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cboRole = new javax.swing.JComboBox();
        btnGetListEmp = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("ID:");

        jLabel2.setText("Mã NV:");

        jLabel3.setText("Account");

        txtAccount.setPreferredSize(new java.awt.Dimension(50, 22));

        lblGoToHomeScreen.setBackground(new java.awt.Color(0, 76, 128));
        lblGoToHomeScreen.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        lblGoToHomeScreen.setForeground(new java.awt.Color(238, 28, 37));
        lblGoToHomeScreen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGoToHomeScreen.setText("Go To HomeScreen");
        lblGoToHomeScreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGoToHomeScreenMouseClicked(evt);
            }
        });

        btnAddNewUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-add-new-user.png"))); // NOI18N
        btnAddNewUser.setText("Add New User");
        btnAddNewUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewUserActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-delete.png"))); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-updatepng.png"))); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-save.png"))); // NOI18N
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnClose.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnClose.setText("CLOSE");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        jLabel4.setText("Pasword");

        jLabel5.setText("Role");

        btnGetListEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sma/icon/icons-view.png"))); // NOI18N
        btnGetListEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetListEmpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGoToHomeScreen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnAddNewUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnSave)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnUpdate)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                                        .addComponent(txtID, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnGetListEmp))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                                    .addComponent(cboRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGetListEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAddNewUser)
                    .addComponent(btnClose))
                .addGap(18, 18, 18)
                .addComponent(lblGoToHomeScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã NV", "Account", "Pasword", "Role"
            }
        ));
        tblUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUser);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblGoToHomeScreenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGoToHomeScreenMouseClicked
        // TODO add your handling code here:
        HomeScreen home = new HomeScreen();
        home.setVisible(true);
        setVisible(false);
        System.out.println("You have just pressed button 'Go To HomeScreen' ");
    }//GEN-LAST:event_lblGoToHomeScreenMouseClicked

    private void tblUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserMouseClicked
        // TODO add your handling code here:
        int i = tblUser.getSelectedRow();
        TableModel model = tblUser.getModel();
        txtID.setText(model.getValueAt(i, 0).toString());
        txtMaNV.setText(model.getValueAt(i, 1).toString());
        txtAccount.setText(model.getValueAt(i, 2).toString());
        txtPassword.setText(model.getValueAt(i, 3).toString());

        String role = model.getValueAt(i, 4).toString().trim();
        switch (role) {
            case "01":
                cboRole.setSelectedIndex(0);
                break;
            case "02":
                cboRole.setSelectedIndex(1);
                break;
            case "03":
                cboRole.setSelectedIndex(2);
                break;
        }

        txtID.setEditable(false);
        txtMaNV.setEditable(false);

        btnSave.setEnabled(false);
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);

    }//GEN-LAST:event_tblUserMouseClicked

    private void btnAddNewUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewUserActionPerformed
        // TODO add your handling code here:
        autoId();
        txtMaNV.setText("");
        txtAccount.setText("");
        txtPassword.setText("");
        cboRole.setSelectedItem(null);

        txtMaNV.setEnabled(true);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnSave.setEnabled(true);
    }//GEN-LAST:event_btnAddNewUserActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:

        String maNV = txtMaNV.getText();
        String userName = txtAccount.getText();
        String pass = txtPassword.getText();

        try {
            User user = new User();
            user.setId(txtID.getText());
            user.setId_employee(maNV);
            user.setAccount(userName);
            user.setPassword(pass);

            String role = cboRole.getSelectedItem().toString().trim();
            switch (role) {
                case "Quản TRị Viên": {
                    user.setId_role("01");
                    break;
                }
                case "Quản Lý": {
                    user.setId_role("02");
                    break;
                }
                case "Nhân Viên": {
                    user.setId_role("03");
                    break;
                }
            }

            if (maNV.equals("")) {
                JOptionPane.showMessageDialog(null, "Chưa có Mã Nhân Viên");
            } else if (UserController.checkEmp(maNV)) {
                JOptionPane.showMessageDialog(null, "ID đã có tài khoảng");
            } else if (userName.equals("")) {
                JOptionPane.showMessageDialog(null, "Chưa có UserName");
            } else if (pass.equals("")) {
                JOptionPane.showMessageDialog(null, "Chưa có Password");
            } else if (UserController.checkUsername(userName)) {
                JOptionPane.showMessageDialog(null, "Tài Khoảng Đã Tồn Tại");
            } else {
                try {
                    UserController.addUser(user);
                    JOptionPane.showMessageDialog(null, "Add User Sucessfully!");
                    DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
                    model.setRowCount(0);
                    loadDataToTable();
                    autoId();
                    txtMaNV.setText("");
                    txtAccount.setText("");
                    txtPassword.setText("");
                    cboRole.setSelectedItem(null);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ID User đã tồn tại");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID Nhân Viên không tồn tại");
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(null, "Are You Sure", "DELETE", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {
            int rowIndex = tblUser.getSelectedRow();
            String id_user = tblUser.getModel().getValueAt(rowIndex, 0).toString();

            UserController.deleteUser(id_user);
            DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
            model.setRowCount(0);
            JOptionPane.showMessageDialog(null, "Deleted Sucessfully!");
            loadDataToTable();
            buttonEnable();
            textfieldEnable();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        User user = new User();
        user.setId(txtID.getText());
        user.setId_employee(txtMaNV.getText());
        user.setAccount(txtAccount.getText());
        user.setPassword(txtPassword.getText());
        String role = cboRole.getSelectedItem().toString().trim();
        switch (role) {
            case "Quản TRị Viên": {
                user.setId_role("01");
                break;
            }
            case "Quản Lý": {
                user.setId_role("02");
                break;
            }
            case "Nhân Viên": {
                user.setId_role("03");
                break;
            }
        }

        int rowIndex = tblUser.getSelectedRow();
        String id_user = tblUser.getModel().getValueAt(rowIndex, 0).toString();
        UserController.updateUser(user, id_user);
        JOptionPane.showMessageDialog(null, "Update Role Sucessfully!");
        DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
        model.setRowCount(0);
        loadDataToTable();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        RoleUserView roleUserView = new RoleUserView();
        roleUserView.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnGetListEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetListEmpActionPerformed
        // TODO add your handling code here:
        ListEmpView listEmp = new ListEmpView();
        listEmp.setVisible(true);
    }//GEN-LAST:event_btnGetListEmpActionPerformed

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
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNewUser;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnGetListEmp;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cboRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblGoToHomeScreen;
    private javax.swing.JTable tblUser;
    private javax.swing.JTextField txtAccount;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtPassword;
    // End of variables declaration//GEN-END:variables
}
