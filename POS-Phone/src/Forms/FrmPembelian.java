/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmPembelian.java
 *
 * Created on 07 Nov 15, 14:13:25
 */
package Forms;

import cls.ClassDB;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HadraFathaki
 */
public final class FrmPembelian extends javax.swing.JInternalFrame {

    private DefaultTableModel model;
    public FrmPembelian() {
        initComponents();
        loadcombodata();
        model=new DefaultTableModel();
        tblPembelian.setModel(model);
        model.addColumn("No. Transaksi");
        model.addColumn("Tgl. Transaksi");
        model.addColumn("Kode Supplier");  
        model.addColumn("Kode Barang");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Total Harga");
        loadData();
    }
    
public void loadcombodata(){
    try {        
        TxtKodeBarang.removeAllItems();
        
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql="Select * from barang";
            ResultSet r=s.executeQuery(sql);
            
            while (r.next()) {
                TxtKodeBarang.addItem(r.getString("kd_barang"));
            }
            r.close();
            s.close();
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan");
        }    
    
    try {        
        TxtKodeSupplier.removeAllItems();
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql="Select * from supplier";
            ResultSet r=s.executeQuery(sql);
            
            while (r.next()) {
                TxtKodeSupplier.addItem(r.getString("kd_supplier"));
            }
            r.close();
            s.close();
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan");
        }    
    
}
public void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {            
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql="Select * from pembelian where no_transaksi='" + this.TxtNoTransaksi.getText() + "'";
            ResultSet r=s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o=new Object[7];
                o[0]=r.getString("no_transaksi");
                o[1]=r.getString("tgl_transaksi");
                o[2]=r.getString("kd_supplier");                
                o[3]=r.getString("kd_barang");
                o[4]=r.getString("harga");
                o[5]=r.getString("jumlah");                
                o[6]=r.getString("totalharga");
                
                model.addRow(o);
            }
            r.close();
            s.close();
            
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan");
        }
    }
public void updatebarang(String jml,String kode) {
    String sql1="Update barang Set stok=stok + ? where kd_barang=?";
    try {        
    Connection c=ClassDB.getkoneksi();            
            PreparedStatement st=(PreparedStatement) c.prepareStatement(sql1);
            st.setString(1, jml);
            st.setString(2, kode);
            st.executeUpdate();
            st.close();
            
    }catch(SQLException e) {
        System.out.println(e);
    }
}

public void TambahData() {
    String kode=this.TxtNoTransaksi.getText();
    java.util.Date tgl=(java.util.Date) this.TxtTglTransaksi.getDate();
    String KodeSup=(String) this.TxtKodeSupplier.getSelectedItem();
    String KodeBar=(String) this.TxtKodeBarang.getSelectedItem();
    String Harga=this.TxtHarga.getText();
    String Jml=this.TxtJumlah.getText();
    String total=this.TxtTotalHarga.getText();
        try {
            String sql1="Select * from pembelian where no_transaksi='" + kode + "' AND kd_barang='" + KodeBar + "' AND kd_supplier='" + KodeSup + "'";
            Connection c=ClassDB.getkoneksi();            
            PreparedStatement st=(PreparedStatement) c.prepareStatement(sql1);
            ResultSet rs=st.executeQuery(sql1);
            if (rs.next())
            {
            String sql = "UPDATE pembelian Set jumlah=jumlah + ?,totalharga= totalharga + ? where no_transaksi='" + kode + "' AND kd_barang='" + KodeBar + "' AND kd_supplier='" + KodeSup + "'";
            PreparedStatement p2=(PreparedStatement) c.prepareStatement(sql);
            
            p2.setString(1, Jml);
            p2.setString(2, total);           
            p2.executeUpdate();
            p2.close();
            this.updatebarang(Jml, KodeBar);
            
            }else{
            String sql = "Insert into pembelian values (?,?,?,?,?,?,?)";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            p.setString(1, kode);
            p.setDate(2,  new java.sql.Date(tgl.getTime()));
            p.setString(3, KodeSup);
            p.setString(4, KodeBar);                        
            p.setString(5, Harga);                        
            p.setString(6, Jml);                        
            p.setString(7, total);                        
            p.executeUpdate();
            p.close();
            this.updatebarang(Jml, KodeBar);
            
            }
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            loadData();
        }
}
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TxtNoTransaksi = new javax.swing.JTextField();
        TxtHarga = new javax.swing.JTextField();
        TxtJumlah = new javax.swing.JTextField();
        TxtTotalHarga = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPembelian = new javax.swing.JTable();
        TxtTglTransaksi = new com.toedter.calendar.JDateChooser();
        TxtKodeSupplier = new javax.swing.JComboBox();
        TxtKodeBarang = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setTitle("TRANSAKSI PEMBELIAN");

        jLabel1.setText("Tgl Transaksi");

        jLabel2.setText("Kode Supplier");

        jLabel3.setText("No Transaksi");

        jLabel4.setText("Kode Barang");

        jLabel5.setText("Harga");

        jLabel6.setText("Jumlah");

        jLabel7.setText("Total Harga");

        TxtNoTransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNoTransaksiKeyPressed(evt);
            }
        });

        TxtHarga.setEditable(false);

        TxtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtJumlahKeyReleased(evt);
            }
        });

        TxtTotalHarga.setEditable(false);

        tblPembelian.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPembelian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPembelianMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPembelian);

        TxtKodeSupplier.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        TxtKodeBarang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        TxtKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtKodeBarangActionPerformed(evt);
            }
        });

        jButton2.setText("EXIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Add =>");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtTglTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TxtKodeSupplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TxtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)))
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TxtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TxtTglTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtKodeSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TxtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-949)/2, (screenSize.height-439)/2, 949, 439);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void TxtKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtKodeBarangActionPerformed
        int i=TxtKodeBarang.getSelectedIndex();
        if (i==-1)
        {
            return;
        }
         try {
             String nm=(String) TxtKodeBarang.getSelectedItem();
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql = "Select harga from barang WHERE kd_barang=?";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            p.setString(1, nm);
            ResultSet result=p.executeQuery();
            result.next();
            TxtHarga.setText(result.getString("harga"));
           
            
            }catch(SQLException e){
            System.out.println(e);        
         }
    }//GEN-LAST:event_TxtKodeBarangActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        TambahData();
        loadData();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void TxtJumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtJumlahKeyReleased
        String qtys=TxtJumlah.getText();       
       String totals=TxtHarga.getText();
        double qtysd=Double.parseDouble(qtys);
        double totalsd=Double.parseDouble(totals);
        DecimalFormat df = new DecimalFormat("#.##");
        TxtTotalHarga.setText(df.format(qtysd * totalsd));
    }//GEN-LAST:event_TxtJumlahKeyReleased

    private void tblPembelianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPembelianMouseClicked
        int i=tblPembelian.getSelectedRow();
        
        if(i==-1)
        {
            return;
        }
        String kode=(String) model.getValueAt(i, 0);        
        String kodesup=(String) model.getValueAt(i, 2);        
        String kodebar=(String) model.getValueAt(i, 3);
        String jml=(String) model.getValueAt(i, 5);
        
        String sql1="Update barang Set stok=stok - ? where kd_barang=?";
        String sql="Delete from pembelian where no_transaksi=? AND kd_barang=? AND kd_supplier=?";
    try {        
    Connection c=ClassDB.getkoneksi();            
            PreparedStatement st=(PreparedStatement) c.prepareStatement(sql1);
            st.setString(1, jml);
            st.setString(2, kodebar);
            st.executeUpdate();
            st.close();
            
            PreparedStatement st2=(PreparedStatement) c.prepareStatement(sql);
            st2.setString(1, kode);
            st2.setString(2, kodebar);
            st2.setString(3, kodesup);
            st2.executeUpdate();
            st2.close();
            
    }catch(SQLException e) {
        System.out.println(e);
    }finally{
        loadData();
    }
    }//GEN-LAST:event_tblPembelianMouseClicked

    private void TxtNoTransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNoTransaksiKeyPressed
        loadData();
    }//GEN-LAST:event_TxtNoTransaksiKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtHarga;
    private javax.swing.JTextField TxtJumlah;
    private javax.swing.JComboBox TxtKodeBarang;
    private javax.swing.JComboBox TxtKodeSupplier;
    private javax.swing.JTextField TxtNoTransaksi;
    private com.toedter.calendar.JDateChooser TxtTglTransaksi;
    private javax.swing.JTextField TxtTotalHarga;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPembelian;
    // End of variables declaration//GEN-END:variables
}
