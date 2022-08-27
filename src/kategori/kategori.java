package kategori;
import koneksi.*;
import menu.*;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class kategori extends javax.swing.JFrame {
    private DefaultTableModel model;
    public kategori() {
        initComponents();
        kodekategori();
        textkkategori.setEnabled(false);
        model = new DefaultTableModel();
        tableinput.setModel(model);
        model.addColumn("Kode Kategori");
        model.addColumn("Nama Kategori");
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
        bsimpan.setEnabled(true);
        bhapus.setEnabled(false);
        bedit.setEnabled(false);
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM kategori";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                Object[] o = new Object[8];
                o[0] = r.getString("kodekategori");
                o[1] = r.getString("namakategori");
                                
                model.addRow(o);
            }
            r.close();
            s.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e);
        }
    }
    private void kodekategori() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM kategori ORDER by kodekategori desc";
            ResultSet r = s.executeQuery(sql);

            if (r.next()) {
                String ksat = r.getString("kodekategori").substring(1);
                String AN = "" + (Integer.parseInt(ksat) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "0";
                } 
                else if (AN.length() == 2) {
                    Nol = "";
                }
                textkkategori.setText("2" + Nol + AN);
            }
            else {
                textkkategori.setText("201");
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        bedit = new javax.swing.JButton();
        textcari = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        exit = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        bedit1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        exit1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        textkkategori = new javax.swing.JTextField();
        textnkategori = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableinput = new javax.swing.JTable();
        bsimpan = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Satuan Barang");
        setBackground(new java.awt.Color(0, 51, 51));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CARI");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, -1, -1));

        bedit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bedit.setForeground(new java.awt.Color(51, 51, 255));
        bedit.setText("EDIT");
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });
        jPanel1.add(bedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 100, 50));

        textcari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textcariKeyReleased(evt);
            }
        });
        jPanel1.add(textcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 230, 20));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Kategori Barang");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        exit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        exit.setForeground(new java.awt.Color(51, 51, 255));
        exit.setText("KEMBALI");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jPanel2.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 60));

        jPanel3.setBackground(new java.awt.Color(0, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bedit1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bedit1.setForeground(new java.awt.Color(255, 255, 255));
        bedit1.setText("EDIT");
        bedit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bedit1ActionPerformed(evt);
            }
        });
        jPanel3.add(bedit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 100, 50));

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Form Satuan Barang");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        exit1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        exit1.setForeground(new java.awt.Color(255, 255, 255));
        exit1.setText("KEMBALI");
        exit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit1ActionPerformed(evt);
            }
        });
        jPanel4.add(exit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, -1, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 60));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Kode Kategori");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        textkkategori.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textkkategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textkkategoriKeyTyped(evt);
            }
        });
        jPanel3.add(textkkategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 230, 20));

        textnkategori.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textnkategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textnkategoriKeyTyped(evt);
            }
        });
        jPanel3.add(textnkategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 230, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nama Kategori");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        tableinput.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Kategori", "Nama Kategori"
            }
        ));
        tableinput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableinputMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableinput);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 730, 160));

        bsimpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bsimpan.setForeground(new java.awt.Color(51, 51, 255));
        bsimpan.setText("SIMPAN");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });
        jPanel3.add(bsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 100, 50));

        bhapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bhapus.setForeground(new java.awt.Color(51, 51, 255));
        bhapus.setText("HAPUS");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });
        jPanel3.add(bhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 100, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 450));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        formmenu fb = new formmenu();
        fb.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_exitActionPerformed

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        if(
            textnkategori.getText().equals("")
        ){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "PT. Catur Sentosa Anugerah", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String tkodekategori = textkkategori.getText();
            String tnamakategori = textnkategori.getText();
                      
            try {
                long millis=System.currentTimeMillis();  
                //java.sql.Date date=new java.sql.Date(millis);  
                //System.out.println(date); 
                //String ttanggal = date.toString();
                Connection c = koneksi.getKoneksi();
                String sql = "INSERT INTO kategori VALUES (?, ?)";
                PreparedStatement p = c.prepareStatement(sql);                
                p.setString(1, tkodekategori);
                p.setString(2, tnamakategori);                        
                p.executeUpdate();
                p.close();               
            } 
            catch (SQLException e) {
                System.out.println("Terjadi Error");
            } 

            finally {
                loadData();
                kodekategori();
                textnkategori.setText("");

                JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan", "PT. Catur Sentosa Anugerah", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_bsimpanActionPerformed
    private void tableinputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableinputMouseClicked
        bsimpan.setEnabled(false);
        bedit.setEnabled(true);
        bhapus.setEnabled(true);
        int i = tableinput.getSelectedRow();
        if (i == -1) {
            return;
        }
        String tablekodesupplier = (String) model.getValueAt(i, 0);
        textkkategori.setText(tablekodesupplier);
        textkkategori.setEnabled(false);

        String tablenamasupplier = (String) model.getValueAt(i, 1);
        textnkategori.setText(tablenamasupplier);
    }//GEN-LAST:event_tableinputMouseClicked
    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        try {
            String sql ="delete from kategori where kodekategori='" +textkkategori.getText()+"'";
            java.sql.Connection conn=(Connection)koneksi.getKoneksi();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Berhasil Di Hapus");
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        loadData();
        kodekategori();
        textnkategori.setText("");
    }//GEN-LAST:event_bhapusActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        if(
            textnkategori.getText().equals("")
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
            String sql = "UPDATE kategori SET namakategori=? where kodekategori='"+textkkategori.getText()+"'";
            PreparedStatement p = c.prepareStatement(sql); 
            p.setString(1, textnkategori.getText());
            p.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah"); 
            textnkategori.requestFocus();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah"+e);
        }
        finally {
                loadData();
                kodekategori();
                textnkategori.setText("");

                JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan", "PT. Catur Sentosa Anugerah", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_beditActionPerformed

    private void textcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcariKeyReleased
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM kategori WHERE kodekategori LIKE '%" + textcari.getText() + "%' OR namakategori like'%" + textcari.getText() + "%'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[2];
                o[0] = r.getString("kodekategori");
                o[1] = r.getString("namakategori");
                               
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
    }//GEN-LAST:event_textcariKeyReleased

    private void textnkategoriKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textnkategoriKeyTyped
    }//GEN-LAST:event_textnkategoriKeyTyped

    private void bedit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bedit1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bedit1ActionPerformed

    private void exit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exit1ActionPerformed

    private void textkkategoriKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textkkategoriKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_textkkategoriKeyTyped
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new kategori().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bedit;
    private javax.swing.JButton bedit1;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton exit;
    private javax.swing.JButton exit1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableinput;
    private javax.swing.JTextField textcari;
    private javax.swing.JTextField textkkategori;
    private javax.swing.JTextField textnkategori;
    // End of variables declaration//GEN-END:variables
}
