package pelanggan;
import koneksi.*;
import menu.*;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class pelanggan extends javax.swing.JFrame {
    private DefaultTableModel model;
    public pelanggan() {
        initComponents();
        kodepelanggan();
        textkpelanggan.setEnabled(false);
        model = new DefaultTableModel();
        tableinput.setModel(model);
        model.addColumn("Kede Pelanggan");
        model.addColumn("Nama Pelanggan");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Alamat");
        model.addColumn("Nomor Telepon");
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

            String sql = "SELECT * FROM pelanggan";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                Object[] o = new Object[5];
                o[0] = r.getString("kodepelanggan");
                o[1] = r.getString("namapelanggan");
                o[2] = r.getString("jeniskelamin");
                o[3] = r.getString("nomortelepon");
                o[4] = r.getString("alamat");
                                
                model.addRow(o);
            }
            r.close();
            s.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e);
        }
    }
    private void kodepelanggan() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM pelanggan ORDER by kodepelanggan desc";
            ResultSet r = s.executeQuery(sql);

            if (r.next()) {
                String ksupp = r.getString("kodepelanggan").substring(1);
                String AN = "" + (Integer.parseInt(ksupp) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "0";
                } 
                else if (AN.length() == 2) {
                    Nol = "";
                }
                textkpelanggan.setText("4" + Nol + AN);
            }
            else {
                textkpelanggan.setText("401");
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        texttelepon = new javax.swing.JTextField();
        textalamat = new javax.swing.JTextField();
        bedit = new javax.swing.JButton();
        bsimpan = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableinput = new javax.swing.JTable();
        textcari = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        exit = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        textnpelanggan = new javax.swing.JTextField();
        textkpelanggan = new javax.swing.JTextField();
        cjeniskelamin = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Pelanggan");
        setBackground(new java.awt.Color(0, 51, 51));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CARI");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 270, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Jenis Kelamin");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nomor Telepon");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Alamat");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1));

        texttelepon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textteleponKeyTyped(evt);
            }
        });
        jPanel1.add(texttelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 230, 20));

        textalamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textalamatKeyTyped(evt);
            }
        });
        jPanel1.add(textalamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 230, 20));

        bedit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bedit.setForeground(new java.awt.Color(51, 51, 255));
        bedit.setText("EDIT");
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });
        jPanel1.add(bedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 100, 50));

        bsimpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bsimpan.setForeground(new java.awt.Color(51, 51, 255));
        bsimpan.setText("SIMPAN");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });
        jPanel1.add(bsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 100, 50));

        bhapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bhapus.setForeground(new java.awt.Color(51, 51, 255));
        bhapus.setText("HAPUS");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });
        jPanel1.add(bhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 100, 50));

        tableinput.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Pelanggan", "Nama Pelanggan", "Jenis Kelamin", "Nomor Telepon", "Alamat"
            }
        ));
        tableinput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableinputMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableinput);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 740, 140));

        textcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textcariKeyReleased(evt);
            }
        });
        jPanel1.add(textcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, 270, 30));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Pelanggan");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        exit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        exit.setForeground(new java.awt.Color(51, 51, 255));
        exit.setText("KEMBALI");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jPanel2.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 60));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Kode Pelanggan");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nama Pelanggan");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        textnpelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textnpelangganKeyTyped(evt);
            }
        });
        jPanel1.add(textnpelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 230, 20));

        textkpelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textkpelangganKeyTyped(evt);
            }
        });
        jPanel1.add(textkpelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 230, 20));

        cjeniskelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan" }));
        jPanel1.add(cjeniskelamin, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 230, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 480));

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
            textnpelanggan.getText().equals("") ||
            textalamat.getText().equals("") ||
            texttelepon.getText().equals("") || 
            textalamat.getText().equals("")
        ){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "PT. Catur Sentosa Anugerah", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String tkodepelanggan = textkpelanggan.getText();
            String tnamapelanggan = textnpelanggan.getText();
            String tjeniskelamin  = cjeniskelamin.getSelectedItem().toString();
            String ttelepon       = texttelepon.getText();
            String talamat        = textalamat.getText();

            try {
                long millis=System.currentTimeMillis();  
                java.sql.Date date=new java.sql.Date(millis);  
                System.out.println(date); 
                String ttanggal = date.toString();
                Connection c = koneksi.getKoneksi();
                String sql = "INSERT INTO pelanggan VALUES (?, ?, ?, ?, ?)";
                PreparedStatement p = c.prepareStatement(sql);                
                p.setString(1, tkodepelanggan);
                p.setString(2, tnamapelanggan);
                p.setString(3, tjeniskelamin);
                p.setString(4, ttelepon);
                p.setString(5, talamat);                         
                p.executeUpdate();
                p.close();               
            } 
            catch (SQLException e) {
                System.out.println("Terjadi Error");
            } 

            finally {
                loadData();
                kodepelanggan();
                textnpelanggan.setText("");             
                texttelepon.setText("");
                textalamat.setText("");

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
        String tablekodepelanggan = (String) model.getValueAt(i, 0);
        textkpelanggan.setText(tablekodepelanggan);
        textkpelanggan.setEnabled(false);

        String tablenamapelanggan = (String) model.getValueAt(i, 1);
        textnpelanggan.setText(tablenamapelanggan);

        String tablealamat = (String) model.getValueAt(i, 2);
        cjeniskelamin.getSelectedItem().toString();

        String tabletelepon = (String) model.getValueAt(i, 3);
        texttelepon.setText(tabletelepon);

        String tablerekening = (String) model.getValueAt(i, 4);
        textalamat.setText(tablerekening);
    }//GEN-LAST:event_tableinputMouseClicked
    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        try {
            String sql ="delete from pelanggan where kodepelanggan='" +textkpelanggan.getText()+"'";
            java.sql.Connection conn=(Connection)koneksi.getKoneksi();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Berhasil Di Hapus");
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        loadData();
        kodepelanggan();
        textnpelanggan.setText("");             
        texttelepon.setText("");
        textalamat.setText("");
    }//GEN-LAST:event_bhapusActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        if(
            textnpelanggan.getText().equals("") ||
            texttelepon.getText().equals("") || 
            textalamat.getText().equals("")
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
            String sql = "UPDATE pelanggan SET namapelanggan=?, jeniskelamin=?, nomortelepon=?, alamat=? where kodepelanggan='"+textkpelanggan.getText()+"'";
            PreparedStatement p = c.prepareStatement(sql); 
            p.setString(1, textnpelanggan.getText());
            p.setString(2, cjeniskelamin.getSelectedItem().toString());
            p.setString(3, texttelepon.getText());
            p.setString(4, textalamat.getText());
            p.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah"); 
            textkpelanggan.requestFocus();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah"+e);
        }
        finally {
                loadData();
                kodepelanggan();
                textnpelanggan.setText("");             
                texttelepon.setText("");
                textalamat.setText("");

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

            String sql = "SELECT * FROM pelanggan WHERE "
                    + "kodepelanggan LIKE '%" + textcari.getText() 
                    + "%' OR namapelanggan LIKE'%" + textcari.getText() 
                    + "%' OR jeniskelamin like'" + textcari.getText() 
                    + "%' OR nomortelepon like'%" + textcari.getText()
                    + "%' OR alamat like'%" + textcari.getText() + "%'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[8];
                o[0] = r.getString("kodepelanggan");
                o[1] = r.getString("namapelanggan");
                o[2] = r.getString("jeniskelamin");
                o[3] = r.getString("nomortelepon");
                o[4] = r.getString("alamat");
                               
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
    }//GEN-LAST:event_textcariKeyReleased

    private void textteleponKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textteleponKeyTyped
        FilterAngka(evt);
    }//GEN-LAST:event_textteleponKeyTyped
    private void textalamatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textalamatKeyTyped
    }//GEN-LAST:event_textalamatKeyTyped
    private void textnpelangganKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textnpelangganKeyTyped
        FilterHuruf(evt);
    }//GEN-LAST:event_textnpelangganKeyTyped
    private void textkpelangganKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textkpelangganKeyTyped
    }//GEN-LAST:event_textkpelangganKeyTyped
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bedit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bsimpan;
    private javax.swing.JComboBox<String> cjeniskelamin;
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableinput;
    private javax.swing.JTextField textalamat;
    private javax.swing.JTextField textcari;
    private javax.swing.JTextField textkpelanggan;
    private javax.swing.JTextField textnpelanggan;
    private javax.swing.JTextField texttelepon;
    // End of variables declaration//GEN-END:variables
}
