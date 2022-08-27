package barang;
import koneksi.*;
import menu.*;
import java.awt.event.KeyEvent;
import java.sql.*;
import koneksi.koneksi;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class barang extends javax.swing.JFrame {
    private Connection conn = new koneksi().getKoneksi(); 
    private DefaultTableModel model;
    public barang() {
        initComponents();
        kodebarang();
        textkbarang.setEnabled(false);
        model = new DefaultTableModel();
        tableinput.setModel(model);
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
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
            Connection conn = koneksi.getKoneksi();
            Statement s = conn.createStatement();

            String sql = "SELECT * FROM barang";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                Object[] o = new Object[2];
                o[0] = r.getString("kodebarang");
                o[1] = r.getString("namabarang");
                                
                model.addRow(o);
            }
            r.close();
            s.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e);
        }
    }
    private void kodebarang() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM barang ORDER by kodebarang desc";
            ResultSet r = s.executeQuery(sql);
            
            if (r.next()) {
                String kbar = r.getString("kodebarang").substring(1);
                String AN = "" + (Integer.parseInt(kbar) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "0";
                } 
                else if (AN.length() == 2) {
                    Nol = "";
                }
                textkbarang.setText("3" + Nol + AN);
            }
            else {
                textkbarang.setText("301");
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
        bedit = new javax.swing.JButton();
        bsimpan = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        textcari = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        exit = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        textnbarang = new javax.swing.JTextField();
        textkbarang = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableinput = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Barang");
        setBackground(new java.awt.Color(0, 51, 51));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CARI");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama Barang");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

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

        textcari.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textcariKeyReleased(evt);
            }
        });
        jPanel1.add(textcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 210, 270, 30));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Barang");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        exit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        exit.setForeground(new java.awt.Color(51, 51, 255));
        exit.setText("KEMBALI");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jPanel2.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 60));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Kode Barang");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        textnbarang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(textnbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 230, -1));

        textkbarang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(textkbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 230, -1));

        tableinput.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Nama Barang"
            }
        ));
        jScrollPane2.setViewportView(tableinput);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 700, 140));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 450));

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
            textnbarang.getText().equals("")
        ){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "PT. Catur Sentosa Anugerah", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String tkodebarang   = textkbarang.getText();
            String tnamabarang   = textnbarang.getText();
                      
            try {
                //long millis=System.currentTimeMillis();  
                //java.sql.Date date=new java.sql.Date(millis);  
                //System.out.println(date); 
                //String ttanggal = date.toString();
                Connection c = koneksi.getKoneksi();
                String sql = "INSERT INTO barang VALUES (?, ?)";
                PreparedStatement p = c.prepareStatement(sql);                
                p.setString(1, tkodebarang);
                p.setString(2, tnamabarang);                          
                p.executeUpdate();
                p.close();               
            } 
            catch (SQLException e) {
                System.out.println("Terjadi Error");
            } 

            finally {
                loadData();
                kodebarang();
                textnbarang.setText("");                

                JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan", "PT. Catur Sentosa Anugerah", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_bsimpanActionPerformed
    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        try {
            String sql ="delete from barang where kodebarang='" +textkbarang.getText()+"'";
            java.sql.Connection conn=(Connection)koneksi.getKoneksi();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Berhasil Di Hapus");
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        loadData();
        kodebarang();
        textnbarang.setText("");                
    }//GEN-LAST:event_bhapusActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        if(
            textnbarang.getText().equals("")
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
            String sql = "UPDATE barang SET namabarang=? where kodebarang='"+textkbarang.getText()+"'";
            PreparedStatement p = c.prepareStatement(sql); 
            p.setString(1, textnbarang.getText());
            p.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah"); 
            textkbarang.requestFocus();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah"+e);
        }
        finally {
                loadData();
                kodebarang();
                textnbarang.setText("");

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

            String sql = "SELECT * FROM barang WHERE "
                    + "kodebarang LIKE '%" + textcari.getText() 
                    + "%' OR namabarang like'%" + textcari.getText() + "%'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[2];
                o[0] = r.getString("kodebarang");
                o[1] = r.getString("namabarang");
                               
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
    }//GEN-LAST:event_textcariKeyReleased
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new barang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bedit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableinput;
    private javax.swing.JTextField textcari;
    private javax.swing.JTextField textkbarang;
    private javax.swing.JTextField textnbarang;
    // End of variables declaration//GEN-END:variables
}
