/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmPelanggan.java
 *
 * Created on 04 Nov 15, 18:01:49
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
public final class FrmPelanggan extends javax.swing.JInternalFrame {

    private DefaultTableModel model;
    public FrmPelanggan() {
        initComponents();
        model=new DefaultTableModel();
        tblPelanggan.setModel(model);
        model.addColumn("Kode Pelanggan");
        model.addColumn("Nama Pelanggan");
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
            String sql="Select * from pelanggan";
            ResultSet r=s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o=new Object[4];
                o[0]=r.getString("kd_pelanggan");
                o[1]=r.getString("nm_pelanggan");
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
    String kode=this.TxtKodePelanggan.getText();
    String nama=this.TxtNamaPelanggan.getText();
    String alamat=this.TxtAlamat.getText();
    String tlp=this.TxtNoTlp.getText() ;
    
        try {
            Connection c=ClassDB.getkoneksi();
            String sql = "Insert into pelanggan values (?,?,?,?)";
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
    int i=tblPelanggan.getSelectedRow();
        if(i==-1)
        {
            return;
        }
        String id=(String) model.getValueAt(i, 0);
        String nama=this.TxtNamaPelanggan.getText();
        String alamat=this.TxtAlamat.getText();
        String tlp=this.TxtNoTlp.getText() ;
        try {
            Connection c=ClassDB.getkoneksi();
            String sql = "UPDATE pelanggan Set nm_pelanggan=?,alamat=?,notlp=? WHERE kd_pelanggan=?";
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
    int i=tblPelanggan.getSelectedRow();
        if(i==-1)
        {
            return;
        }
        String id=(String) model.getValueAt(i, 0);
       
       
        try {
            Connection c=ClassDB.getkoneksi();
            String sql = "DELETE From pelanggan  WHERE kd_pelanggan=?";
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
    
    int i=tblPelanggan.getSelectedRow();
        
        if(i==-1)
        {
            return;
        }
        String kode=(String) model.getValueAt(i, 0);
        TxtKodePelanggan.setText(kode);
        String nama=(String) model.getValueAt(i, 1);
        TxtNamaPelanggan.setText(nama);
        String alamat=(String) model.getValueAt(i, 2);
        TxtAlamat.setText(alamat);
        String tlp=(String) model.getValueAt(i, 3);
        TxtNoTlp.setText(tlp);
        
}

public void HapusText() {
    this.TxtKodePelanggan.setText(null);
    this.TxtNamaPelanggan.setText(null);
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
        tblPelanggan = new javax.swing.JTable();
        cmdEdit = new javax.swing.JButton();
        cmdAdd = new javax.swing.JButton();
        cmdDelete = new javax.swing.JButton();
        cmdRefresh = new javax.swing.JButton();
        cmdExit = new javax.swing.JButton();
        panel2 = new java.awt.Panel();
        jLabel2 = new javax.swing.JLabel();
        TxtKodePelanggan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtNamaPelanggan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TxtAlamat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TxtNoTlp = new javax.swing.JTextField();

        panel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("INPUT DATA PELANGGAN");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(583, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblPelanggan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPelangganMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPelanggan);

        cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Edit-32.png"))); // NOI18N
        cmdEdit.setText("EDIT");
        cmdEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditActionPerformed(evt);
            }
        });

        cmdAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Add-32.png"))); // NOI18N
        cmdAdd.setText("ADD");
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });

        cmdDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Delete-32.png"))); // NOI18N
        cmdDelete.setText("DELETE");
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });

        cmdRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Refresh-32.png"))); // NOI18N
        cmdRefresh.setText("REFRESH");
        cmdRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRefreshActionPerformed(evt);
            }
        });

        cmdExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/door_out.png"))); // NOI18N
        cmdExit.setText("EXIT");
        cmdExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitActionPerformed(evt);
            }
        });

        panel2.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Kode Pelanggan");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Nama Pelanggan");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Alamat");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("No. Telp/HP");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 351, Short.MAX_VALUE)
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
                        .addComponent(TxtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtKodePelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(TxtKodePelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TxtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cmdAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdExit))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 532, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(379, 379, 379)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdExit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(71, 71, 71)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(105, Short.MAX_VALUE)))
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
          this.TxtKodePelanggan.requestFocusInWindow();
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
        String kode=this.TxtKodePelanggan.getText();
        
      if ("EDIT".equals(a))
      {
          if ("".equals(kode.trim()) || kode.trim()==null)
          {
          return;
          }else{
        cmdAdd.setText("UPDATE");
          cmdEdit.setText("CANCEL");
          panel2.enable();
          this.TxtKodePelanggan.enable(false);
          this.TxtNamaPelanggan.requestFocusInWindow();
          }
      }else if ("CANCEL".equals(a)) {
          cmdAdd.setText("ADD");
          cmdEdit.setText("EDIT");
           panel2.enable(false);  
           this.TxtKodePelanggan.enable();
      }
    }//GEN-LAST:event_cmdEditActionPerformed

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        String kode=this.TxtKodePelanggan.getText();
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

    private void tblPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPelangganMouseClicked
        ShowData();
    }//GEN-LAST:event_tblPelangganMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtAlamat;
    private javax.swing.JTextField TxtKodePelanggan;
    private javax.swing.JTextField TxtNamaPelanggan;
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
    private javax.swing.JTable tblPelanggan;
    // End of variables declaration//GEN-END:variables
}
