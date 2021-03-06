/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toko;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Keranjang extends javax.swing.JFrame {

    String id_produk;
    int total=0, jumlah, harga;
    Connector connector = new Connector();
    
    public Keranjang() {
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
        bayar = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        ubah = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        kembali = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        ltotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Produk", "Nama Produk", "Jumlah", "Harga (Rp)"
            }
        ));
        jScrollPane1.setViewportView(tabel);

        bayar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bayar.setText("Bayar");
        bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarActionPerformed(evt);
            }
        });

        hapus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        ubah.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ubah.setText("Ubah Jumlah");
        ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Trajan Pro", 1, 24)); // NOI18N
        jLabel1.setText("Daftar Keranjang");

        kembali.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        kembali.setText("Kembali");
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Total Harga     Rp.");

        ltotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ltotal.setText("Total");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(ubah)
                .addGap(93, 93, 93)
                .addComponent(hapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bayar)
                .addGap(100, 100, 100))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(119, 119, 119)
                                .addComponent(kembali)))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kembali)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ltotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ubah)
                    .addComponent(hapus)
                    .addComponent(bayar))
                .addGap(28, 28, 28))
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
                String query = "SELECT * FROM keranjang"; //menampilkan data keranjang ke dalam tabel
                connector.statement = connector.koneksi.createStatement();
                ResultSet rs = connector.statement.executeQuery(query);
                
                while(rs.next()){
                    String id_produk = rs.getString("id_product");
                    String nama = rs.getString("product_name");
                    String jumlah = rs.getString("jumlah");
                    String harga = rs.getString("price");
                    this.jumlah = Integer.parseInt(jumlah);
                    this.harga = Integer.parseInt(harga);
                    this.harga = this.jumlah*this.harga;
                    this.total = this.total+this.harga;
                    harga = Integer.toString(this.harga);
                    String tbData[] = {id_produk,nama,jumlah,harga};
                    
                    tbModel.addRow(tbData);//menambah isi tabel
                    
                }
         }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        String total;
        total=Integer.toString(this.total);
        ltotal.setText(total);
    }
        
    private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
        // TODO add your handling code here:
        int baris = tabel.getSelectedRow();
        Object id_produk = tabel.getValueAt(baris, 0);
        this.id_produk = id_produk.toString();
        EditJumlah edit = new EditJumlah(this.id_produk); //menuju ke halaman edit jumlah
        edit.setVisible(true);
        dispose();
    }//GEN-LAST:event_ubahActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tabel.getModel();
        String cek = tabel.getValueAt(tabel.getSelectedRow(),0).toString();
        int row = tabel.getSelectedRow();
         try {
            PreparedStatement pStatement = null;
            String query ="DELETE FROM `keranjang` WHERE id_product=? "; //menghapus barang dari keranjang
            pStatement = connector.koneksi.prepareStatement(query);
            pStatement.setString(1, cek);
            int intTambah= pStatement.executeUpdate();
            if (intTambah>0){
                model.removeRow(row);
                JOptionPane.showMessageDialog(this,"Hapus data sukses", "Informasi",JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(this,"Hapus data gagal", "Informasi",JOptionPane.INFORMATION_MESSAGE);
            pStatement.close();
         }catch (SQLException e){
            System.out.println("koneksi gagal " + e.toString());
        }
    }//GEN-LAST:event_hapusActionPerformed

    private void bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarActionPerformed
        // TODO add your handling code here:
        try{
                String query = "SELECT * FROM `keranjang`";
                connector.statement = connector.koneksi.createStatement();
                ResultSet rs = connector.statement.executeQuery(query);
                
                while(rs.next()){
                    String id_produk = rs.getString("id_product");
                    this.jumlah = Integer.parseInt(rs.getString("jumlah"));
                    String query2 = "UPDATE `products` SET `stock` = `stock`-'"+this.jumlah+"' WHERE id_product = '"+id_produk+"'"; //untuk mengurangi stok sesuai jumlah yang dibeli

                    connector.statement = connector.koneksi.createStatement();
                    connector.statement.executeUpdate(query2);
                }
         }catch(NumberFormatException | SQLException ex){
            System.out.println(ex.getMessage());
        }
        Struk struk = new Struk(); 
        struk.setVisible(true);
        dispose();
    }//GEN-LAST:event_bayarActionPerformed

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        // TODO add your handling code here:
        HomePembeli pembeli = new HomePembeli(); //kembali ke halaman home pembeli
        pembeli.setVisible(true);
        dispose();
    }//GEN-LAST:event_kembaliActionPerformed

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
            java.util.logging.Logger.getLogger(Keranjang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Keranjang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Keranjang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Keranjang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Keranjang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bayar;
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kembali;
    private javax.swing.JLabel ltotal;
    private javax.swing.JTable tabel;
    private javax.swing.JButton ubah;
    // End of variables declaration//GEN-END:variables
}
