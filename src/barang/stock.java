package barang;
import koneksi.*;
import menu.*;
import java.awt.event.KeyEvent;
import java.sql.*;
import koneksi.koneksi;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class stock extends javax.swing.JFrame {
    private DefaultTableModel model;
    private Connection conn = new koneksi().getKoneksi();
    ResultSet rs, rs1, rs2, rs3;
    PreparedStatement pst = null;
    Statement stat, stat1, stat2, stat3;
    double tot, hb, jum;
    public stock() {
        initComponents();
        textkstock.setEnabled(false);
        texttotal.setEnabled(false);
        model = new DefaultTableModel();
        tableinput.setModel(model);
        model.addColumn("Kode Stock");
        model.addColumn("Kategori Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Satuan Barang");
        model.addColumn("Jumlah Barang");
        model.addColumn("Harga Beli");
        model.addColumn("Total");
        loadData();      
    }
    public void FilterHuruf(KeyEvent a){
        if(Character.isDigit(a.getKeyChar())){
            a.consume();
            JOptionPane.showMessageDialog(null, "Masukan Huruf Saja!", "PERINGATAN", JOptionPane.WARNING_MESSAGE);
        }
    }
    public void FilterAngka(KeyEvent a){
        if(Character.isAlphabetic(a.getKeyChar())){
            a.consume();
            JOptionPane.showMessageDialog(null, "Masukan Angka Saja!", "PERINGATAN", JOptionPane.WARNING_MESSAGE);
        }
    }
    public final void loadData() {
        bhapus.setEnabled(false);
        bedit.setEnabled(false);
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection conn = koneksi.getKoneksi();
            Statement s = conn.createStatement();

            String sql = "SELECT * FROM stock";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                Object[] o = new Object[7];
                o[0] = r.getString("kodestock");
                o[1] = r.getString("namakategori");
                o[2] = r.getString("namabarang");
                o[3] = r.getString("namasatuan");
                o[4] = r.getString("jumlahbarang");
                o[5] = r.getString("hargabeli");
                o[6] = r.getString("total");
                                
                model.addRow(o);
            }
            r.close();
            s.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtotal = new javax.swing.JLabel();
        bedit = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableinput = new javax.swing.JTable();
        textcari = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        exit = new javax.swing.JButton();
        textkstock = new javax.swing.JTextField();
        jhbeli = new javax.swing.JLabel();
        texttotal = new javax.swing.JTextField();
        textjumlah = new javax.swing.JTextField();
        texthbeli = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Stock Barang");
        setBackground(new java.awt.Color(0, 51, 51));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CARI");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 200, -1, -1));

        jtotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtotal.setForeground(new java.awt.Color(255, 255, 255));
        jtotal.setText("Rp");
        jPanel1.add(jtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, -1, 20));

        bedit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bedit.setForeground(new java.awt.Color(51, 51, 255));
        bedit.setText("EDIT");
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });
        jPanel1.add(bedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 80, 130, 50));

        bhapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bhapus.setForeground(new java.awt.Color(51, 51, 255));
        bhapus.setText("HAPUS");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });
        jPanel1.add(bhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 120, 50));

        tableinput.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Beli Barang", "Jumlah", "Harga Beli", "Total"
            }
        ));
        tableinput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableinputMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableinput);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 740, 160));

        textcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textcariKeyReleased(evt);
            }
        });
        jPanel1.add(textcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, 270, 30));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Stock Barang");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        exit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        exit.setForeground(new java.awt.Color(51, 51, 255));
        exit.setText("KEMBALI");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jPanel2.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 60));

        textkstock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textkstockKeyTyped(evt);
            }
        });
        jPanel1.add(textkstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 230, 20));

        jhbeli.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jhbeli.setForeground(new java.awt.Color(255, 255, 255));
        jhbeli.setText("Rp");
        jPanel1.add(jhbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 30, 20));
        jPanel1.add(texttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, 190, -1));
        jPanel1.add(textjumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 230, -1));

        texthbeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texthbeliActionPerformed(evt);
            }
        });
        jPanel1.add(texthbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 190, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Jumlah");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, -1, 20));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Harga Beli");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Total");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, -1, 20));

        jButton1.setText("TOTAL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 340, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Kode Beli Barang");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, 20));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 51, 255));
        jButton2.setText("BELI BARANG");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 270, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 460));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed

        formmenu fb = new formmenu();
        fb.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_exitActionPerformed

    private void tableinputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableinputMouseClicked
        bedit.setEnabled(true);
        bhapus.setEnabled(true);
        int i = tableinput.getSelectedRow();
        if (i == -1) {
            return;
        }
        String tablekodestock = (String) model.getValueAt(i, 0);
        textkstock.setText(tablekodestock);
        textkstock.setEnabled(false);
               
        String tablejumlah = (String) model.getValueAt(i, 4);
        textjumlah.setText(tablejumlah);
        
        String tablehargabeli = (String) model.getValueAt(i, 5);
        texthbeli.setText(tablehargabeli);
        
        String tabletotal = (String) model.getValueAt(i, 6);
        texttotal.setText(tabletotal);
    }//GEN-LAST:event_tableinputMouseClicked
    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        try {
            String sql ="delete from stock where kodestock='" +textkstock.getText()+"'";
            java.sql.Connection conn=(Connection)koneksi.getKoneksi();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Berhasil Di Hapus");
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        loadData();
        textjumlah.setText("");
        texthbeli.setText("");
        texttotal.setText("");                
    }//GEN-LAST:event_bhapusActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        if(
            textjumlah.getText().equals("") ||
            texthbeli.getText().equals("") ||
            texttotal.getText().equals("")
        )
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "PT. Catur Sentosa Anugerah", JOptionPane.INFORMATION_MESSAGE);
        else{
            int i = tableinput.getSelectedRow();
            if (i == -1) {
            return;
        }
        String user = (String) model.getValueAt(i, 0);
        try{
            Connection c = koneksi.getKoneksi();
            String sql = "UPDATE stock SET jumlahbarang=?, hargabeli=?, total=? where kodestock='"+textkstock.getText()+"'";
            PreparedStatement p = c.prepareStatement(sql); 
            p.setString(1, textjumlah.getText());
            p.setString(2, texthbeli.getText());
            p.setString(3, texttotal.getText());
            p.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah"); 
            textkstock.requestFocus();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah"+e);
        }
        finally {
                loadData();
                textjumlah.setText("");
                texthbeli.setText("");
                texttotal.setText("");

                JOptionPane.showMessageDialog(null, "Data Berhasil Diedit", "PT. Catur Sentosa Anugerah", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_beditActionPerformed

    private void textcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcariKeyReleased
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM stock WHERE "
                    + "kodestock LIKE '%" + textcari.getText() 
                    + "%' OR namakategori LIKE'%" + textcari.getText() 
                    + "%' OR namabarang like'" + textcari.getText() 
                    + "%' OR namasatuan like'%" + textcari.getText()
                    + "%' OR jumlah like'%" + textcari.getText()
                    + "%' OR hargabeli like'%" + textcari.getText()
                    + "%' OR total like'%" + textcari.getText() + "%'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[7];
                o[0] = r.getString("kodestock");
                o[1] = r.getString("namakategori");
                o[2] = r.getString("namabarang");
                o[3] = r.getString("namasatuan");
                o[4] = r.getString("jumlahbarang");
                o[5] = r.getString("hargabeli");
                o[6] = r.getString("total");
                               
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
    }//GEN-LAST:event_textcariKeyReleased

    private void textkstockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textkstockKeyTyped
    }//GEN-LAST:event_textkstockKeyTyped

    private void texthbeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texthbeliActionPerformed
        
    }//GEN-LAST:event_texthbeliActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        hb  = Integer.parseInt(texthbeli.getText()); 
        jum = Integer.parseInt(textjumlah.getText()); 
        //tot = Integer.parseInt(texttotal.getText()); 
        tot = hb * jum;
        //String hbeli = String.valueOf(hb);
        //String jumlah = String.valueOf(jum);
        String total = String.valueOf(tot);
        texttotal.setText(total);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        belibarang fb = new belibarang();
        fb.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new stock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bedit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton exit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jhbeli;
    private javax.swing.JLabel jtotal;
    private javax.swing.JTable tableinput;
    private javax.swing.JTextField textcari;
    private javax.swing.JTextField texthbeli;
    private javax.swing.JTextField textjumlah;
    private javax.swing.JTextField textkstock;
    private javax.swing.JTextField texttotal;
    // End of variables declaration//GEN-END:variables
    private void konek(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/barang","root","");
            stat=conn.createStatement(rs.TYPE_SCROLL_SENSITIVE,rs.CONCUR_UPDATABLE);
            rs=stat.executeQuery("select * from stock");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }
    }
    private void display(){
        try{
            String sql = "select * from stock";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            tableinput.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
