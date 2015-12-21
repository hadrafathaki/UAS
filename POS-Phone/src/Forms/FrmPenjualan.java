/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Frmpenjualan.java
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
public final class FrmPenjualan extends javax.swing.JInternalFrame {

    private DefaultTableModel model;
    public FrmPenjualan() {
        initComponents();
        loadcombodata();
        model=new DefaultTableModel();
        tblPenjualan.setModel(model);
        model.addColumn("No. Transaksi");
        model.addColumn("Tgl. Transaksi");  
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
    
    
    
}
public void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {            
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql="Select * from penjualan where no_transaksi='" + this.TxtNoTransaksi.getText() + "'";
            ResultSet r=s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o=new Object[6];
                o[0]=r.getString("no_transaksi");
                o[1]=r.getString("tgl_transaksi");               
                o[2]=r.getString("kd_barang");
                o[3]=r.getString("harga");
                o[4]=r.getString("jumlah");                
                o[5]=r.getString("totalharga");
                
                model.addRow(o);
            }
            r.close();
            s.close();
            
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan");
        }
    }
public void updatebarang(String jml,String kode) {
    String sql1="Update barang Set stok=stok - ? where kd_barang=?";
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

    String KodeBar=(String) this.TxtKodeBarang.getSelectedItem();
    String Harga=this.TxtHarga.getText();
    String Jml=this.TxtJumlah.getText();
    String total=this.TxtTotalHarga.getText();
        try {
            String sql1="Select * from penjualan where no_transaksi='" + kode + "' AND kd_barang='" + KodeBar + "'";
            Connection c=ClassDB.getkoneksi();            
            PreparedStatement st=(PreparedStatement) c.prepareStatement(sql1);
            ResultSet rs=st.executeQuery(sql1);
            if (rs.next())
            {
            String sql = "UPDATE penjualan Set jumlah=jumlah + ?,totalharga= totalharga + ? where no_transaksi='" + kode + "' AND kd_barang='" + KodeBar + "'";
            PreparedStatement p2=(PreparedStatement) c.prepareStatement(sql);
            
            p2.setString(1, Jml);
            p2.setString(2, total);           
            p2.executeUpdate();
            p2.close();
            this.updatebarang(Jml, KodeBar);
            
            }else{
            String sql = "Insert into penjualan values (?,?,?,?,?,?)";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            p.setString(1, kode);
            p.setDate(2,  new java.sql.Date(tgl.getTime()));          
            p.setString(3, KodeBar);                        
            p.setString(4, Harga);                        
            p.setString(5, Jml);                        
            p.setString(6, total);                        
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
        tblPenjualan = new javax.swing.JTable();
        TxtTglTransaksi = new com.toedter.calendar.JDateChooser();
        TxtKodeBarang = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        TxtStok = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setTitle("TRANSAKSI PEMBELIAN");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Tgl Transaksi");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 54, 72, 23));

        jLabel3.setText("No Transaksi");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 13, -1, 23));

        jLabel4.setText("Kode Barang");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 89, 72, 23));

        jLabel5.setText("Harga");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 122, 63, 23));

        jLabel6.setText("Jumlah");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 156, 63, 23));

        jLabel7.setText("Total Harga");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 63, 23));

        TxtNoTransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNoTransaksiKeyPressed(evt);
            }
        });
        getContentPane().add(TxtNoTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 11, 112, -1));

        TxtHarga.setEditable(false);
        getContentPane().add(TxtHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 129, 112, -1));

        TxtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtJumlahKeyReleased(evt);
            }
        });
        getContentPane().add(TxtJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 64, -1));

        TxtTotalHarga.setEditable(false);
        getContentPane().add(TxtTotalHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 193, 112, -1));

        tblPenjualan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPenjualanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPenjualan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 470, 293));
        getContentPane().add(TxtTglTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 48, 128, -1));

        TxtKodeBarang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        TxtKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtKodeBarangActionPerformed(evt);
            }
        });
        getContentPane().add(TxtKodeBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 141, -1));

        jButton2.setText("EXIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 336, 98, 32));

        jButton3.setText("Add =>");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(333, 86, -1, 32));

        TxtStok.setEditable(false);
        TxtStok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtStokKeyReleased(evt);
            }
        });
        getContentPane().add(TxtStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 160, 64, -1));

        jLabel8.setText("tersedia");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 159, -1, 23));

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-924)/2, (screenSize.height-439)/2, 924, 439);
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
            String sql = "Select harga,stok from barang WHERE kd_barang=?";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            p.setString(1, nm);
            ResultSet result=p.executeQuery();
            result.next();
            TxtHarga.setText(result.getString("harga"));
            TxtStok.setText(result.getString("stok"));
            
            }catch(SQLException e){
            System.out.println(e);        
         }
    }//GEN-LAST:event_TxtKodeBarangActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        TambahData();
        loadData();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void TxtJumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtJumlahKeyReleased
        int q1=Integer.parseInt(this.TxtJumlah.getText());
        int q2=Integer.parseInt(this.TxtStok.getText());
        if (q1 <= q2)
        {
        String qtys=TxtJumlah.getText();       
       String totals=TxtHarga.getText();
        double qtysd=Double.parseDouble(qtys);
        double totalsd=Double.parseDouble(totals);
        DecimalFormat df = new DecimalFormat("#.##");
        TxtTotalHarga.setText(df.format(qtysd * totalsd));
        }else{
            TxtJumlah.setText(null);
        }
    }//GEN-LAST:event_TxtJumlahKeyReleased

    private void tblPenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPenjualanMouseClicked
        int i=tblPenjualan.getSelectedRow();
        
        if(i==-1)
        {
            return;
        }
        String kode=(String) model.getValueAt(i, 0);        
               
        String kodebar=(String) model.getValueAt(i, 2);
        String jml=(String) model.getValueAt(i, 4);
        
        String sql1="Update barang Set stok=stok + ? where kd_barang=?";
        String sql="Delete from penjualan where no_transaksi=? AND kd_barang=?";
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
            st2.executeUpdate();
            st2.close();
            
    }catch(SQLException e) {
        System.out.println(e);
    }finally{
        loadData();
    }
    }//GEN-LAST:event_tblPenjualanMouseClicked

    private void TxtNoTransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNoTransaksiKeyPressed
        loadData();
    }//GEN-LAST:event_TxtNoTransaksiKeyPressed

    private void TxtStokKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtStokKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtStokKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtHarga;
    private javax.swing.JTextField TxtJumlah;
    private javax.swing.JComboBox TxtKodeBarang;
    private javax.swing.JTextField TxtNoTransaksi;
    private javax.swing.JTextField TxtStok;
    private com.toedter.calendar.JDateChooser TxtTglTransaksi;
    private javax.swing.JTextField TxtTotalHarga;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPenjualan;
    // End of variables declaration//GEN-END:variables
}
