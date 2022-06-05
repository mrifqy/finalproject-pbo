/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toko;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HomePembeli extends javax.swing.JFrame {

    String id_produk;
    Connector connector = new Connector();
    public HomePembeli() {
        initComponents();
        tampil();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tambah = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        keranjang = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Produk", "Nama Barang", "Stok", "Harga (Rp)"
            }
        ));
        jScrollPane1.setViewportView(tabel);

        jLabel1.setFont(new java.awt.Font("Trajan Pro", 0, 24)); // NOI18N
        jLabel1.setText("Daftar Barang");

        tambah.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tambah.setText("Tambah Keranjang");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        logout.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        keranjang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        keranjang.setText("Lihat Keranjang");
        keranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keranjangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(148, 148, 148)
                        .addComponent(logout)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(tambah)
                .addGap(86, 86, 86)
                .addComponent(keranjang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(logout)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambah)
                    .addComponent(keranjang))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tampil(){
        try{
                DefaultTableModel tbModel = (DefaultTableModel)tabel.getModel();
                int baris = tabel.getRowCount();
                for(int a=0;a<baris;a++){
                    tbModel.removeRow(0);  //menghapus isi tabel (jika sebelumnya sudah ada data)
                }
                String query = "SELECT * FROM products WHERE stock>0"; //menampilkan data yang stoknya masih ada
                
                connector.statement = connector.koneksi.createStatement();
                ResultSet rs = connector.statement.executeQuery(query);
                
                while(rs.next()){
                    String id_produk = rs.getString("id_product");
                    String nama_barang = rs.getString("product_name");
                    String stock = rs.getString("stock");
                    String harga = rs.getString("price");
                    
                    String tbData[] = {id_produk,nama_barang,stock,harga};
                    
                    tbModel.addRow(tbData);//menambah isi tabel
                    
                }
         }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
        int baris = tabel.getSelectedRow();
        Object id_produk = tabel.getValueAt(baris, 0);
        this.id_produk = id_produk.toString();
        try{
            String query = "SELECT * FROM products WHERE id_product = '"+this.id_produk+"'"; //menambah barang yang dipilih ke keranjang
            connector.statement = connector.koneksi.createStatement();
            ResultSet rs = connector.statement.executeQuery(query);
            rs.next();
            String nama_barang = rs.getString("product_name");
            int jumlah = 1;
            int harga = Integer.parseInt(rs.getString("price"));
            String query2 = "INSERT INTO keranjang VALUES ('"+this.id_produk+"','"+ nama_barang +"','"+jumlah+"','"+harga+"')";
            
            connector.statement = connector.koneksi.createStatement();
            connector.statement.executeUpdate(query2);

            System.out.println("Barang dimasukkan keranjang");
            JOptionPane.showMessageDialog(null,"Barang dimasukkan keranjang");
         }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_tambahActionPerformed

    private void keranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keranjangActionPerformed
        // TODO add your handling code here:
        Keranjang keranjang = new Keranjang();  //menuju halaman keranjang
        keranjang.setVisible(true);
        dispose();
    }//GEN-LAST:event_keranjangActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        Home home = new Home();  //menuju halaman home
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_logoutActionPerformed

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
            java.util.logging.Logger.getLogger(HomePembeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePembeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePembeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePembeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePembeli().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton keranjang;
    private javax.swing.JButton logout;
    private javax.swing.JTable tabel;
    private javax.swing.JButton tambah;
    // End of variables declaration//GEN-END:variables
}