/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmBarang.java
 *
 * Created on 03 Nov 15, 13:12:55
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
public final class FrmBarang extends javax.swing.JInternalFrame {

    private DefaultTableModel model;
   
    public FrmBarang() {
        initComponents();
        model=new DefaultTableModel();
        tblBarang.setModel(model);
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("No Seri");        
        model.addColumn("Harga");        
        model.addColumn("Stok");
        loadData();
        
    }
public void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {            
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql="Select * from barang";
            ResultSet r=s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o=new Object[5];
                o[0]=r.getString("kd_barang");
                o[1]=r.getString("nm_barang");
                o[2]=r.getString("no_seri");                
                o[3]=r.getString("harga");
                o[4]=r.getString("stok");
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
    String kode=this.TxtKodeBarang.getText();
    String nama=this.TxtNamaBarang.getText();
    String noseri=this.TxtNoSeri.getText();
    String harga=this.TxtHarga.getText() ;
    Integer stok=Integer.parseInt(this.TxtStok.getText());
        try {
            Connection c=ClassDB.getkoneksi();
            String sql = "Insert into barang values (?,?,?,?,?)";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            p.setString(1, kode);
            p.setString(2, nama);
            p.setString(3, noseri);
            p.setString(4, harga);            
            p.setInt(5, stok);
            p.executeUpdate();
            p.close();
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            loadData();
        }
}

public void UpdateData() {
    int i=tblBarang.getSelectedRow();
        if(i==-1)
        {
            return;
        }
        String id=(String) model.getValueAt(i, 0);
        String nama=this.TxtNamaBarang.getText();
        String noseri=this.TxtNoSeri.getText();
        String harga=this.TxtHarga.getText() ;
        Integer stok=Integer.parseInt(this.TxtStok.getText());
        try {
            Connection c=ClassDB.getkoneksi();
            String sql = "UPDATE barang Set nm_barang=?,no_seri=?,harga=?,stok=? WHERE kd_barang=?";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            
            p.setString(1, nama);
            p.setString(2, noseri);
            p.setString(3, harga);            
            p.setInt(4, stok);
            p.setString(5, id);
            p.executeUpdate();
            p.close();
        }catch(SQLException e){
            System.out.println("Terjadi kesalahan");
        }finally{
            loadData();
        }
}

public void DeleteData() {
    int i=tblBarang.getSelectedRow();
        if(i==-1)
        {
            return;
        }
        String id=(String) model.getValueAt(i, 0);
       
       
        try {
            Connection c=ClassDB.getkoneksi();
            String sql = "DELETE From barang  WHERE kd_barang=?";
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
    
    int i=tblBarang.getSelectedRow();
        
        if(i==-1)
        {
            return;
        }
        String kode=(String) model.getValueAt(i, 0);
        TxtKodeBarang.setText(kode);
        String nama=(String) model.getValueAt(i, 1);
        TxtNamaBarang.setText(nama);
        String no_seri=(String) model.getValueAt(i, 2);
        TxtNoSeri.setText(no_seri);
        String harga=(String) model.getValueAt(i, 3);
        TxtHarga.setText(harga);
        String stok=(String) model.getValueAt(i, 4);
        TxtStok.setText(stok);
}

public void HapusText() {
    this.TxtKodeBarang.setText(null);
    this.TxtNamaBarang.setText(null);
    this.TxtHarga.setText(null);
    this.TxtNoSeri.setText(null);
    this.TxtStok.setText(null);
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
        cmdEdit = new javax.swing.JButton();
        cmdDelete = new javax.swing.JButton();
        cmdAdd = new javax.swing.JButton();
        cmdRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        cmdExit = new javax.swing.JButton();
        panel2 = new java.awt.Panel();
        TxtStok = new javax.swing.JTextField();
        TxtHarga = new javax.swing.JTextField();
        TxtNamaBarang = new javax.swing.JTextField();
        TxtNoSeri = new javax.swing.JTextField();
        TxtKodeBarang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setTitle("Input Data Barang");
        setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        panel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("INPUT DATA BARANG");

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

        cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Edit-32.png"))); // NOI18N
        cmdEdit.setText("EDIT");
        cmdEdit.setFocusable(false);
        cmdEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditActionPerformed(evt);
            }
        });

        cmdDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Delete-32.png"))); // NOI18N
        cmdDelete.setText("DELETE");
        cmdDelete.setFocusable(false);
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });

        cmdAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Add-32.png"))); // NOI18N
        cmdAdd.setText("ADD");
        cmdAdd.setFocusable(false);
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });

        cmdRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Refresh-32.png"))); // NOI18N
        cmdRefresh.setText("REFRESH");
        cmdRefresh.setFocusable(false);
        cmdRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdRefreshMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cmdRefreshMousePressed(evt);
            }
        });
        cmdRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRefreshActionPerformed(evt);
            }
        });

        tblBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBarang.setRowSelectionAllowed(false);
        tblBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBarang);

        cmdExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/door_out.png"))); // NOI18N
        cmdExit.setText("EXIT");
        cmdExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitActionPerformed(evt);
            }
        });

        panel2.setEnabled(false);

        TxtStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtStokActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("No. Seri");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Nama Barang");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Kode Barang");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Stok");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Harga");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 353, Short.MAX_VALUE)
            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addComponent(jLabel7))
                    .addGap(27, 27, 27)
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TxtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtNoSeri, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(20, Short.MAX_VALUE)))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addComponent(TxtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TxtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addGap(18, 18, 18)
                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TxtNoSeri, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addGap(18, 18, 18)
                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TxtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))))
                    .addGap(18, 18, 18)
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TxtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addContainerGap(69, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cmdAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdRefresh)
                        .addGap(314, 314, 314)
                        .addComponent(cmdExit))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdExit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        setLocation(new java.awt.Point(0, 0));
    }// </editor-fold>//GEN-END:initComponents

    private void cmdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdExitActionPerformed

    private void cmdRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRefreshActionPerformed
        loadData();
        
    }//GEN-LAST:event_cmdRefreshActionPerformed

    private void tblBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBarangMouseClicked
        ShowData();
    }//GEN-LAST:event_tblBarangMouseClicked

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
       
    }//GEN-LAST:event_formMouseMoved

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
      String a=cmdAdd.getText();
      if ("ADD".equals(a))
      {
          cmdAdd.setText("SAVE");
          cmdEdit.setText("CANCEL");
          panel2.enable();
          HapusText();
          this.TxtKodeBarang.requestFocusInWindow();
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
        String kode=this.TxtKodeBarang.getText();
        
      if ("EDIT".equals(a))
      {
          if ("".equals(kode.trim()) || kode.trim()==null)
          {
          return;
          }else{
        cmdAdd.setText("UPDATE");
          cmdEdit.setText("CANCEL");
          panel2.enable();
          this.TxtKodeBarang.enable(false);
          this.TxtNamaBarang.requestFocusInWindow();
          }
      }else if ("CANCEL".equals(a)) {
          cmdAdd.setText("ADD");
          cmdEdit.setText("EDIT");
           panel2.enable(false);  
           this.TxtKodeBarang.enable();
      }
    }//GEN-LAST:event_cmdEditActionPerformed

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
       String kode=this.TxtKodeBarang.getText();
        if ("".equals(kode.trim()) || kode.trim()==null)
          {
          return;
          }else{
        DeleteData();
        HapusText();
        }
    }//GEN-LAST:event_cmdDeleteActionPerformed

    private void cmdRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdRefreshMouseClicked
        
    }//GEN-LAST:event_cmdRefreshMouseClicked

    private void cmdRefreshMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdRefreshMousePressed
       
    }//GEN-LAST:event_cmdRefreshMousePressed

    private void TxtStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtStokActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtHarga;
    private javax.swing.JTextField TxtKodeBarang;
    private javax.swing.JTextField TxtNamaBarang;
    private javax.swing.JTextField TxtNoSeri;
    private javax.swing.JTextField TxtStok;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private javax.swing.JTable tblBarang;
    // End of variables declaration//GEN-END:variables
}
