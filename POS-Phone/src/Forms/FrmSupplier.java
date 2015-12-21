/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmSupplier.java
 *
 * Created on 04 Nov 15, 18:03:41
 */
package Forms;
import cls.ClassDB;
import com.mysql.jdbc.PreparedStatement;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author HadraFathaki
 */
public class FrmSupplier extends javax.swing.JInternalFrame {

    private DefaultTableModel model;
    public FrmSupplier() {
        initComponents();
        model=new DefaultTableModel();
        tblSupplier.setModel(model);
        model.addColumn("Kode Supplier");
        model.addColumn("Nama Supplier");
        model.addColumn("Alamat");        
        model.addColumn("No. Telepon");        
        
        loadData();
    }
public void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {            
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql="Select * from Supplier";
            ResultSet r=s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o=new Object[4];
                o[0]=r.getString("kd_Supplier");
                o[1]=r.getString("nm_Supplier");
                o[2]=r.getString("alamat");                
                o[3]=r.getString("notlp");
                
                model.addRow(o);
            }
            r.close();
            s.close();
            ShowData();
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan");
        }
    }
public void TambahData() {
    String kode=this.TxtKodeSupplier.getText();
    String nama=this.TxtNamaSupplier.getText();
    String alamat=this.TxtAlamat.getText();
    String tlp=this.TxtNoTlp.getText() ;
    
        try {
            Connection c=ClassDB.getkoneksi();
            String sql = "Insert into Supplier values (?,?,?,?)";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            p.setString(1, kode);
            p.setString(2, nama);
            p.setString(3, alamat);
            p.setString(4, tlp);                        
            p.executeUpdate();
            p.close();
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            loadData();
        }
}

public void UpdateData() {
    int i=tblSupplier.getSelectedRow();
        if(i==-1)
        {
            return;
        }
        String id=(String) model.getValueAt(i, 0);
        String nama=this.TxtNamaSupplier.getText();
        String alamat=this.TxtAlamat.getText();
        String tlp=this.TxtNoTlp.getText() ;
        try {
            Connection c=ClassDB.getkoneksi();
            String sql = "UPDATE Supplier Set nm_Supplier=?,alamat=?,notlp=? WHERE kd_Supplier=?";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            
            p.setString(1, nama);
            p.setString(2, alamat);
            p.setString(3, tlp);                        
            p.setString(4, id);
            p.executeUpdate();
            p.close();
        }catch(SQLException e){
            System.out.println("Terjadi kesalahan");
        }finally{
            loadData();
        }
}

public void DeleteData() {
    int i=tblSupplier.getSelectedRow();
        if(i==-1)
        {
            return;
        }
        String id=(String) model.getValueAt(i, 0);
       
       
        try {
            Connection c=ClassDB.getkoneksi();
            String sql = "DELETE From Supplier  WHERE kd_Supplier=?";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            p.setString(1, id);            
            p.executeUpdate();
            p.close();
        }catch(SQLException e){
            System.out.println("Terjadi kesalahan");
        }finally{
            loadData();
        }
}
public void mouseClicked(MouseEvent e) {
      ShowData();
   }
public void ShowData() {
    
    int i=tblSupplier.getSelectedRow();
        
        if(i==-1)
        {
            return;
        }
        String kode=(String) model.getValueAt(i, 0);
        TxtKodeSupplier.setText(kode);
        String nama=(String) model.getValueAt(i, 1);
        TxtNamaSupplier.setText(nama);
        String alamat=(String) model.getValueAt(i, 2);
        TxtAlamat.setText(alamat);
        String tlp=(String) model.getValueAt(i, 3);
        TxtNoTlp.setText(tlp);
        
}

public void HapusText() {
    this.TxtKodeSupplier.setText(null);
    this.TxtNamaSupplier.setText(null);
    this.TxtAlamat.setText(null);
    this.TxtNoTlp.setText(null);    
}
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSupplier = new javax.swing.JTable();
        panel2 = new java.awt.Panel();
        jLabel2 = new javax.swing.JLabel();
        TxtKodeSupplier = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtNamaSupplier = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TxtAlamat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TxtNoTlp = new javax.swing.JTextField();
        cmdAdd = new javax.swing.JButton();
        cmdEdit = new javax.swing.JButton();
        cmdRefresh = new javax.swing.JButton();
        cmdDelete = new javax.swing.JButton();
        cmdExit = new javax.swing.JButton();

        panel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("INPUT DATA SUPPLIER");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSupplierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSupplier);

        panel2.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Kode Supplier");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Nama Supplier");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Alamat");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("No. Telp/HP");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TxtNoTlp, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtNamaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtKodeSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 188, Short.MAX_VALUE)
            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addComponent(TxtKodeSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TxtNamaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addGap(18, 18, 18)
                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TxtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addGap(18, 18, 18)
                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TxtNoTlp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        cmdAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Add-32.png"))); // NOI18N
        cmdAdd.setText("ADD");
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });

        cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Edit-32.png"))); // NOI18N
        cmdEdit.setText("EDIT");
        cmdEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditActionPerformed(evt);
            }
        });

        cmdRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Refresh-32.png"))); // NOI18N
        cmdRefresh.setText("REFRESH");
        cmdRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRefreshActionPerformed(evt);
            }
        });

        cmdDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Delete-32.png"))); // NOI18N
        cmdDelete.setText("DELETE");
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });

        cmdExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/door_out.png"))); // NOI18N
        cmdExit.setText("EXIT");
        cmdExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdExit)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdExit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        String a=cmdAdd.getText();
      if ("ADD".equals(a))
      {
          cmdAdd.setText("SAVE");
          cmdEdit.setText("CANCEL");
          panel2.enable();
          HapusText();
          this.TxtKodeSupplier.requestFocusInWindow();
      }else if ("SAVE".equals(a)) {          
          cmdAdd.setText("ADD");
          cmdEdit.setText("EDIT");                    
          panel2.enable(false);
          TambahData();
      }else if("UPDATE".equals(a)) {
          cmdAdd.setText("ADD");
          cmdEdit.setText("EDIT");
          panel2.enable(false);
          UpdateData();
      }
    }//GEN-LAST:event_cmdAddActionPerformed

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditActionPerformed
        String a=cmdEdit.getText();
        String kode=this.TxtKodeSupplier.getText();
        
      if ("EDIT".equals(a))
      {
          if ("".equals(kode.trim()) || kode.trim()==null)
          {
          return;
          }else{
        cmdAdd.setText("UPDATE");
          cmdEdit.setText("CANCEL");
          panel2.enable();
          this.TxtKodeSupplier.enable(false);
          this.TxtNamaSupplier.requestFocusInWindow();
          }
      }else if ("CANCEL".equals(a)) {
          cmdAdd.setText("ADD");
          cmdEdit.setText("EDIT");
           panel2.enable(false);  
           this.TxtKodeSupplier.enable();
      }
    }//GEN-LAST:event_cmdEditActionPerformed

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        String kode=this.TxtKodeSupplier.getText();
        if ("".equals(kode.trim()) || kode.trim()==null)
          {
          return;
          }else{
        DeleteData();
        HapusText();
        }
    }//GEN-LAST:event_cmdDeleteActionPerformed

    private void cmdRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRefreshActionPerformed
        loadData();
    }//GEN-LAST:event_cmdRefreshActionPerformed

    private void cmdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdExitActionPerformed

    private void tblSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSupplierMouseClicked
        ShowData();
    }//GEN-LAST:event_tblSupplierMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtAlamat;
    private javax.swing.JTextField TxtKodeSupplier;
    private javax.swing.JTextField TxtNamaSupplier;
    private javax.swing.JTextField TxtNoTlp;
    private javax.swing.JButton cmdAdd;
    private javax.swing.JButton cmdDelete;
    private javax.swing.JButton cmdEdit;
    private javax.swing.JButton cmdExit;
    private javax.swing.JButton cmdRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private javax.swing.JTable tblSupplier;
    // End of variables declaration//GEN-END:variables
}
