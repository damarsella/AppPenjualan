package menu;
import koneksi.*;
import menu.*;
import barang.*;
import kategori.*;
import pelanggan.*;
import satuan.*;
import supplier.*;
import javax.swing.JOptionPane;
public class formmenu extends javax.swing.JFrame {
    public formmenu() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jregister = new javax.swing.JButton();
        jjualbarang = new javax.swing.JButton();
        jlaporan = new javax.swing.JButton();
        jbelibarang = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jsatuan = new javax.swing.JButton();
        jsupplier = new javax.swing.JButton();
        jkategori = new javax.swing.JButton();
        jbarang = new javax.swing.JButton();
        jpelanggan = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Menu");
        setBackground(new java.awt.Color(0, 51, 51));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jregister.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jregister.setForeground(new java.awt.Color(51, 51, 255));
        jregister.setText("Register");
        jregister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jregisterActionPerformed(evt);
            }
        });
        jPanel2.add(jregister, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 160, 60));

        jjualbarang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jjualbarang.setForeground(new java.awt.Color(51, 51, 255));
        jjualbarang.setText("Jual Barang");
        jjualbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jjualbarangActionPerformed(evt);
            }
        });
        jPanel2.add(jjualbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 160, 60));

        jlaporan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlaporan.setForeground(new java.awt.Color(51, 51, 255));
        jlaporan.setText("Laporan");
        jlaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlaporanActionPerformed(evt);
            }
        });
        jPanel2.add(jlaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 160, 60));

        jbelibarang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbelibarang.setForeground(new java.awt.Color(51, 51, 255));
        jbelibarang.setText("Beli Barang");
        jbelibarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbelibarangActionPerformed(evt);
            }
        });
        jPanel2.add(jbelibarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 160, 60));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(51, 51, 255));
        jButton4.setText("LOG OUT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 120, 30));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 60));

        jsatuan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jsatuan.setForeground(new java.awt.Color(51, 51, 255));
        jsatuan.setText("Input Satuan");
        jsatuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsatuanActionPerformed(evt);
            }
        });
        jPanel2.add(jsatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, 130, 60));

        jsupplier.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jsupplier.setForeground(new java.awt.Color(51, 51, 255));
        jsupplier.setText("Input Supplier");
        jsupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsupplierActionPerformed(evt);
            }
        });
        jPanel2.add(jsupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 130, 60));

        jkategori.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jkategori.setForeground(new java.awt.Color(51, 51, 255));
        jkategori.setText("Input Kategori");
        jkategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkategoriActionPerformed(evt);
            }
        });
        jPanel2.add(jkategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 130, 60));

        jbarang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbarang.setForeground(new java.awt.Color(51, 51, 255));
        jbarang.setText("Input Barang");
        jbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbarangActionPerformed(evt);
            }
        });
        jPanel2.add(jbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 130, 60));

        jpelanggan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jpelanggan.setForeground(new java.awt.Color(51, 51, 255));
        jpelanggan.setText("Input Pelanggan");
        jpelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpelangganActionPerformed(evt);
            }
        });
        jPanel2.add(jpelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 150, 60));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 330));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jregisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jregisterActionPerformed
        formregister fb = new formregister();
        fb.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jregisterActionPerformed

    private void jjualbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jjualbarangActionPerformed
        jualbarang fb = new jualbarang();
        fb.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jjualbarangActionPerformed

    private void jlaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlaporanActionPerformed
        formlaporan fb = new formlaporan();
        fb.setVisible(true);
        this.setVisible(false);                 
    }//GEN-LAST:event_jlaporanActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

         if (JOptionPane.showConfirmDialog(null, "Yakin ingin keluar?", "KHANZA App", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                 this.setVisible(false);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jbelibarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbelibarangActionPerformed
        belibarang fb = new belibarang();
        fb.setVisible(true);
        this.setVisible(false); 
    }//GEN-LAST:event_jbelibarangActionPerformed

    private void jsatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsatuanActionPerformed
        satuan fb = new satuan();
        fb.setVisible(true);
        this.setVisible(false); 
    }//GEN-LAST:event_jsatuanActionPerformed

    private void jsupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsupplierActionPerformed
        supplier fb = new supplier();
        fb.setVisible(true);
        this.setVisible(false); 
    }//GEN-LAST:event_jsupplierActionPerformed

    private void jkategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkategoriActionPerformed
        kategori fb = new kategori();
        fb.setVisible(true);
        this.setVisible(false); 
    }//GEN-LAST:event_jkategoriActionPerformed

    private void jbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbarangActionPerformed
        barang fb = new barang();
        fb.setVisible(true);
        this.setVisible(false); 
    }//GEN-LAST:event_jbarangActionPerformed

    private void jpelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpelangganActionPerformed
        pelanggan fb = new pelanggan();
        fb.setVisible(true);
        this.setVisible(false); 
    }//GEN-LAST:event_jpelangganActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formmenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbarang;
    private javax.swing.JButton jbelibarang;
    private javax.swing.JButton jjualbarang;
    private javax.swing.JButton jkategori;
    private javax.swing.JButton jlaporan;
    private javax.swing.JButton jpelanggan;
    private javax.swing.JButton jregister;
    private javax.swing.JButton jsatuan;
    private javax.swing.JButton jsupplier;
    // End of variables declaration//GEN-END:variables
}
