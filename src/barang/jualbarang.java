package barang;
import koneksi.*;
import menu.*;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import koneksi.koneksi;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class jualbarang extends javax.swing.JFrame {
    private DefaultTableModel model;
    private Connection conn = new koneksi().getKoneksi();
    ResultSet rs;
    PreparedStatement pst = null;
    Statement Stat;
    int bay, bel, jum, hj, jj, kem, unt, tot, totbel;
    public jualbarang() {
        initComponents();
        textfaktur.setEnabled(false);
        textkpelanggan.setEnabled(false);
        textkbarang.setEnabled(false);
        //textbayar.setEnabled(false);
        textalamat.setEnabled(false);
        textjkelamin.setEnabled(false);
        textntelepon.setEnabled(false);
        textnsatuan.setEnabled(false);
        texthbeli.setEnabled(false);
        texttbeli.setEnabled(false);
        textkembali.setEnabled(false);
        textuntung.setEnabled(false);
        model = new DefaultTableModel();
        tableinput.setModel(model);
        model.addColumn("Faktur");
        model.addColumn("Nama Pelanggan");
        model.addColumn("Nama Barang");
        model.addColumn("Satuan Barang");
        model.addColumn("Harga Jual");
        model.addColumn("Jumlah Jual");
        model.addColumn("Harga Jual Total");
        model.addColumn("Untung");
        model.addColumn("Tanggal");
        loadData();
        tampilnamapelanggan();
        tampilnamabarang();
        kodejual();
        tampilcetak();
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
        //bhapus.setEnabled(false);
        //bedit.setEnabled(false);
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM jualbarang";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                Object[] o = new Object[9];
                o[0] = r.getString("faktur");
                o[1] = r.getString("namapelanggan");
                o[2] = r.getString("namabarang");
                o[3] = r.getString("namasatuan");
                o[4] = r.getString("hargajual");
                o[5] = r.getString("jumlahjual");
                o[6] = r.getString("hargajualtotal");
                o[7] = r.getString("untung");
                o[8] = r.getString("tanggal");
                                
                model.addRow(o);
            }
            r.close();
            s.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e);
        }
    }
    private void tampilcetak() {
        try{
            String query = "SELECT * FROM jualbarang";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){                
                ccetak.addItem(rs.getString("faktur"));
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void tampilnamapelanggan() {
        try{
            String query = "SELECT * FROM pelanggan";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){                
                cnpelanggan.addItem(rs.getString("namapelanggan"));
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void tampilkodepelanggan(){
        try{
                int i = 1;
                String rnpelanggan = cnpelanggan.getSelectedItem().toString();
                String sql = "SELECT * FROM pelanggan Where namapelanggan LIKE '" + rnpelanggan + "' ORDER BY kodepelanggan ASC";
                PreparedStatement stat = conn.prepareStatement(sql);
                ResultSet rs  = stat.executeQuery(sql);
                
                rs.absolute(i);
                rnpelanggan = rs.getString("kodepelanggan");
                textkpelanggan.setText(rnpelanggan);
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
    }
    private void tampilatributpelanggan(){
        try{
                int i = 1;
                String rpelanggan = cnpelanggan.getSelectedItem().toString();
                String sql = "SELECT * FROM pelanggan Where namapelanggan LIKE '" + rpelanggan + "' ORDER BY kodepelanggan ASC";
                PreparedStatement stat1 = conn.prepareStatement(sql);
                PreparedStatement stat2 = conn.prepareStatement(sql);
                PreparedStatement stat3 = conn.prepareStatement(sql);
                ResultSet rs1  = stat1.executeQuery(sql);
                ResultSet rs2  = stat2.executeQuery(sql);
                ResultSet rs3  = stat3.executeQuery(sql);
                
                rs1.absolute(i);
                String ralamat = rs1.getString("alamat");
                textalamat.setText(ralamat);
                
                rs2.absolute(i);
                String rjk = rs2.getString("jeniskelamin");
                textjkelamin.setText(rjk);
                
                rs3.absolute(i);
                String rnt = rs3.getString("nomortelepon");
                textntelepon.setText(rnt);
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
    }
    private void tampilnamabarang() {
        try{
            String query = "SELECT * FROM belibarang";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){                
                cnbarang.addItem(rs.getString("namabarang"));
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void tampilkodebeli(){
        try{
                int i = 1;
                String rbbarang = cnbarang.getSelectedItem().toString();
                String sql = "SELECT * FROM stock Where namabarang LIKE '" + rbbarang + "' ORDER BY kodestock ASC";
                PreparedStatement stat = conn.prepareStatement(sql);
                ResultSet rs  = stat.executeQuery(sql);
                
                rs.absolute(i);
                rbbarang = rs.getString("kodestock");
                textkbarang.setText(rbbarang);
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
    }
    private void tampilatributbarang(){
        try{
                int i = 1;
                String rbarang = cnbarang.getSelectedItem().toString();
                String sql = "SELECT * FROM belibarang Where namabarang LIKE '" + rbarang + "' ORDER BY kodebeli ASC";
                PreparedStatement stat1 = conn.prepareStatement(sql);
                PreparedStatement stat2 = conn.prepareStatement(sql);
                PreparedStatement stat3 = conn.prepareStatement(sql);
                ResultSet rs1  = stat1.executeQuery(sql);
                ResultSet rs2  = stat2.executeQuery(sql);
                ResultSet rs3  = stat3.executeQuery(sql);
                
                rs1.absolute(i);
                String rsatuan = rs1.getString("namasatuan");
                textnsatuan.setText(rsatuan);
                
                rs2.absolute(i);
                String rhbeli = rs2.getString("hargabeli");
                texthbeli.setText(rhbeli);
                
                rs3.absolute(i);
                String rtanggal = rs3.getString("tanggal");
                texttbeli.setText(rtanggal);
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
    }
    private void kodejual() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM jualbarang ORDER by faktur desc";
            ResultSet r = s.executeQuery(sql);
            
            if (r.next()) {
                String kbar = r.getString("faktur").substring(9);
                System.out.println(kbar);
                String AN = "" + (Integer.parseInt(kbar) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "0";
                } 
                else if (AN.length() == 2) {
                    Nol = "";
                }
                textawal.setText("8" + Nol + AN);
            }
            else {
                textawal.setText("801");
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        String rpelanggan = textkpelanggan.getText();
        String rbeli      = textkbarang.getText();
        String awal      = textawal.getText();
        try {
            textfaktur.setText(rpelanggan + rbeli + awal);
        }
        catch (Exception e) {rpelanggan = "";}
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bsimpan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableinput = new javax.swing.JTable();
        textcari = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        exit = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        textfaktur = new javax.swing.JTextField();
        cnpelanggan = new javax.swing.JComboBox<>();
        cnbarang = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        texthbeli = new javax.swing.JTextField();
        texttbeli = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btotal = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        texthjual = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        textuntung = new javax.swing.JTextField();
        textnsatuan = new javax.swing.JTextField();
        textkembali = new javax.swing.JTextField();
        textjjual = new javax.swing.JTextField();
        textbayar = new javax.swing.JTextField();
        textkbarang = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        textalamat = new javax.swing.JTextField();
        textjkelamin = new javax.swing.JTextField();
        textntelepon = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        texttbarang = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jumlah = new javax.swing.JButton();
        textkpelanggan = new javax.swing.JTextField();
        bcetak = new javax.swing.JButton();
        textawal = new javax.swing.JTextField();
        ccetak = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Jual Barang");
        setBackground(new java.awt.Color(0, 51, 51));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CARI");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 310, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama Barang");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, 20));

        bsimpan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bsimpan.setForeground(new java.awt.Color(51, 51, 255));
        bsimpan.setText("SIMPAN");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });
        jPanel1.add(bsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 340, 230, 20));

        tableinput.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Faktur", "NamaPelanggan", "ALamat", "JenisKelamin", "NomorTelepon", "NamaBarang", "NamaSatuan", "HargaBeli", "TanggalBeli", "HargaBeli", "JumlahJual", "Total", "Pembayaran", "Kembalian", "Keuntungan"
            }
        ));
        tableinput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableinputMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableinput);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 770, 120));

        textcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textcariKeyReleased(evt);
            }
        });
        jPanel1.add(textcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 310, 230, -1));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Jual Barang");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        exit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        exit.setForeground(new java.awt.Color(51, 51, 255));
        exit.setText("KEMBALI");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jPanel2.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 60));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Faktur");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nomor Telepon");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, 20));

        textfaktur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textfakturKeyTyped(evt);
            }
        });
        jPanel1.add(textfaktur, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 230, 20));

        cnpelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnpelangganActionPerformed(evt);
            }
        });
        jPanel1.add(cnpelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 130, -1));

        cnbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnbarangActionPerformed(evt);
            }
        });
        jPanel1.add(cnbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 130, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nama Satuan");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, 20));
        jPanel1.add(texthbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 230, -1));

        texttbeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texttbeliActionPerformed(evt);
            }
        });
        jPanel1.add(texttbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 230, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Harga Beli");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, 20));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tanggal Beli");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, 20));

        btotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btotal.setForeground(new java.awt.Color(51, 51, 255));
        btotal.setText("TOTAL");
        btotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btotalActionPerformed(evt);
            }
        });
        jPanel1.add(btotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, 230, 20));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Harga Jual");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, -1, 20));

        texthjual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                texthjualKeyTyped(evt);
            }
        });
        jPanel1.add(texthjual, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 230, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Pembayaran");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, -1, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Kembalian");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 250, -1, 20));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Keuntungan");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, -1, 20));
        jPanel1.add(textuntung, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, 230, -1));
        jPanel1.add(textnsatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 230, -1));
        jPanel1.add(textkembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, 230, -1));

        textjjual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textjjualKeyTyped(evt);
            }
        });
        jPanel1.add(textjjual, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 230, -1));

        textbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textbayarKeyTyped(evt);
            }
        });
        jPanel1.add(textbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 230, -1));
        jPanel1.add(textkbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 100, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Nama Pelanggan");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, 20));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Alamat");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 20));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Jenis Kelamin");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 20));
        jPanel1.add(textalamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 230, -1));
        jPanel1.add(textjkelamin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 230, -1));
        jPanel1.add(textntelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 230, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, -1, 20));
        jPanel1.add(texttbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 160, 230, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Jumlah Jual");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, -1, 20));

        jumlah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jumlah.setForeground(new java.awt.Color(51, 51, 255));
        jumlah.setText("JUMLAH");
        jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahActionPerformed(evt);
            }
        });
        jPanel1.add(jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 230, -1));
        jPanel1.add(textkpelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 100, -1));

        bcetak.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bcetak.setForeground(new java.awt.Color(51, 51, 255));
        bcetak.setText("CETAK");
        bcetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcetakActionPerformed(evt);
            }
        });
        jPanel1.add(bcetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 100, 20));
        jPanel1.add(textawal, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 40, -1));

        jPanel1.add(ccetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 230, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, -1));

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
            texthjual.getText().equals("") ||
            textjjual.getText().equals("") ||
            textbayar.getText().equals("")
        ){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "PT. Catur Sentosa Anugerah", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String tfaktur       = textfaktur.getText();
            String tkpelanggan   = textkpelanggan.getText();
            String tnpelanggan   = cnpelanggan.getSelectedItem().toString();
            String talamat       = textalamat.getText();
            String tjk           = textjkelamin.getText();
            String tnt           = textntelepon.getText();
            String tkbarang      = textkbarang.getText();
            String tnbarang      = cnbarang.getSelectedItem().toString();
            String tnsatuan      = textnsatuan.getText();
            String thjual        = texthjual.getText();
            String tjjual        = textjjual.getText();
            String thjtotal      = texttbarang.getText();
            String tbayar        = textbayar.getText();
            String tkembali      = textkembali.getText();
            String tuntung       = textuntung.getText();
                      
            try {
                long millis=System.currentTimeMillis();  
                java.sql.Date date=new java.sql.Date(millis);  
                System.out.println(date); 
                String ttanggal = date.toString();
                
                Connection c = koneksi.getKoneksi();
                String sql = "INSERT INTO jualbarang VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement p = c.prepareStatement(sql);                
                p.setString(1, tfaktur);
                p.setString(2, tkpelanggan);
                p.setString(3, tnpelanggan);
                p.setString(4, talamat);
                p.setString(5, tjk);
                p.setString(6, tnt);
                p.setString(7, tkbarang);
                p.setString(8, tnbarang);
                p.setString(9, tnsatuan);
                p.setString(10, thjual);
                p.setString(11, tjjual);
                p.setString(12, thjtotal);
                p.setString(13, tbayar);
                p.setString(14, tkembali);
                p.setString(15, tuntung);
                p.setString(16, ttanggal);   
                p.executeUpdate();
                p.close();
                
            } 
            catch (SQLException e) {
                System.out.println("Terjadi Error");
            } 
            
            try {
                String tkbarang2      = textkbarang.getText();
                String tjjual2        = textjjual.getText();
                
                Connection c = koneksi.getKoneksi();
                String sql = "Update stock SET jumlahbarang = jumlahbarang - "+tjjual2+" WHERE kodestock ='"+tkbarang2+"' ";
                PreparedStatement p = c.prepareStatement(sql);                 
                p.executeUpdate();
                p.close();
                
            } 
            catch (SQLException e) {
                System.out.println("Terjadi Error Update");
            } 

            finally {
                loadData();
                kodejual();
                texthjual.setText("");
                textjjual.setText("");
                texttbarang.setText("");
                textbayar.setText("");
                textkembali.setText("");
                textuntung.setText("");

                JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan", "PT. Catur Sentosa Anugerah", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_bsimpanActionPerformed
    private void tableinputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableinputMouseClicked
        bsimpan.setEnabled(false);
        //bedit.setEnabled(true);
        //bhapus.setEnabled(true);
        int i = tableinput.getSelectedRow();
        if (i == -1) {
            return;
        }
        String tablefaktur = (String) model.getValueAt(i, 0);
        textfaktur.setText(tablefaktur);
        textfaktur.setEnabled(false);
        
        String tablenpelanggan = (String) model.getValueAt(i, 1);
        cnpelanggan.setSelectedItem(tablenpelanggan);

        String tablenbarang = (String) model.getValueAt(i, 2);
        cnbarang.setSelectedItem(tablenbarang);
        
        String tablensatuan = (String) model.getValueAt(i, 3);
        textnsatuan.setText(tablensatuan);

        String tablehjual = (String) model.getValueAt(i, 4);
        texthjual.setText(tablehjual);
        
        String tablejjual = (String) model.getValueAt(i, 5);
        textjjual.setText(tablejjual);
        
        String tablehjtotal = (String) model.getValueAt(i, 6);
        texttbarang.setText(tablehjtotal);
        
        String tableuntung = (String) model.getValueAt(i, 7);
        textuntung.setText(tableuntung);
        
    }//GEN-LAST:event_tableinputMouseClicked

    private void textcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcariKeyReleased
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM jualbarang WHERE "
                    + "faktur LIKE '%" + textcari.getText() 
                    + "%' OR namapelanggan LIKE'%" + textcari.getText() 
                    + "%' OR namabarang like'" + textcari.getText() 
                    + "%' OR namasatuan like'%" + textcari.getText()
                    + "%' OR hargajual like'%" + textcari.getText()
                    + "%' OR jumlahjual like'%" + textcari.getText()
                    + "%' OR hargatotal like'%" + textcari.getText()
                    + "%' OR untung like'%" + textcari.getText()
                    + "%' OR tanggal like'%" + textcari.getText() + "%'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[9];
                o[0] = r.getString("faktur");
                o[1] = r.getString("namapelanggan");
                o[2] = r.getString("namabarang");
                o[3] = r.getString("namasatuan");
                o[4] = r.getString("hargajual");
                o[5] = r.getString("jumlahjual");
                o[6] = r.getString("hargajualtotal");
                o[7] = r.getString("untung");
                o[8] = r.getString("tanggal");
                               
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
    }//GEN-LAST:event_textcariKeyReleased

    private void textfakturKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfakturKeyTyped
    }//GEN-LAST:event_textfakturKeyTyped

    private void texttbeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texttbeliActionPerformed
        
    }//GEN-LAST:event_texttbeliActionPerformed

    private void btotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btotalActionPerformed
        tot  = Integer.parseInt(texttbarang.getText()); 
        bay = Integer.parseInt(textbayar.getText()); 
        hj  = Integer.parseInt(texthjual.getText());
        jj = Integer.parseInt(textjjual.getText()); 
        bel = Integer.parseInt(texthbeli.getText()); 
        kem = bay - tot;
        totbel = bel * jj;
        unt = tot - totbel;
        String kembali = String.valueOf(kem);
        textkembali.setText(kembali);
        String untung = String.valueOf(unt);
        textuntung.setText(untung);
    }//GEN-LAST:event_btotalActionPerformed

    private void texthjualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_texthjualKeyTyped
        FilterAngka(evt);
    }//GEN-LAST:event_texthjualKeyTyped

    private void jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahActionPerformed
        hj  = Integer.parseInt(texthjual.getText()); 
        jj = Integer.parseInt(textjjual.getText()); 
        jum = hj * jj;
        String jumlah = String.valueOf(jum);
        texttbarang.setText(jumlah);
    }//GEN-LAST:event_jumlahActionPerformed

    private void cnpelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnpelangganActionPerformed
        tampilkodepelanggan();
        tampilatributpelanggan();
        kodejual();
    }//GEN-LAST:event_cnpelangganActionPerformed

    private void cnbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnbarangActionPerformed
        tampilkodebeli();
        tampilatributbarang();
        kodejual();
    }//GEN-LAST:event_cnbarangActionPerformed

    private void bcetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcetakActionPerformed
        relasicetak();
    }//GEN-LAST:event_bcetakActionPerformed

    private void textbayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textbayarKeyTyped
        FilterAngka(evt);
    }//GEN-LAST:event_textbayarKeyTyped

    private void textjjualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textjjualKeyTyped
        FilterAngka(evt);
    }//GEN-LAST:event_textjjualKeyTyped
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jualbarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bcetak;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton btotal;
    private javax.swing.JComboBox<String> ccetak;
    private javax.swing.JComboBox<String> cnbarang;
    private javax.swing.JComboBox<String> cnpelanggan;
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jumlah;
    private javax.swing.JTable tableinput;
    private javax.swing.JTextField textalamat;
    private javax.swing.JTextField textawal;
    private javax.swing.JTextField textbayar;
    private javax.swing.JTextField textcari;
    private javax.swing.JTextField textfaktur;
    private javax.swing.JTextField texthbeli;
    private javax.swing.JTextField texthjual;
    private javax.swing.JTextField textjjual;
    private javax.swing.JTextField textjkelamin;
    private javax.swing.JTextField textkbarang;
    private javax.swing.JTextField textkembali;
    private javax.swing.JTextField textkpelanggan;
    private javax.swing.JTextField textnsatuan;
    private javax.swing.JTextField textntelepon;
    private javax.swing.JTextField texttbarang;
    private javax.swing.JTextField texttbeli;
    private javax.swing.JTextField textuntung;
    // End of variables declaration//GEN-END:variables

    private void konek(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/barang","root","");
            Stat=conn.createStatement(rs.TYPE_SCROLL_SENSITIVE,rs.CONCUR_UPDATABLE);
            rs=Stat.executeQuery("select * from jualbarang");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }
    }
    private void display(){
        try{
            String sql = "select * from jualbarang";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            tableinput.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    protected void relasicetak(){
        try{
            String rcetak = ("%" + ccetak.getSelectedItem().toString() + "%");       
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = koneksi.getKoneksi();
            HashMap param = new HashMap();
            param.put("fakturdano", rcetak);
            InputStream file = getClass().getResourceAsStream("/report/report1.jrxml");
            JasperDesign JasperDesign = JRXmlLoader.load(file);
            JasperReport JasperReport = JasperCompileManager.compileReport(JasperDesign);
            JasperPrint JasperPrint = JasperFillManager.fillReport(JasperReport, param, conn );
            JasperViewer.viewReport(JasperPrint, false);           
        }
        catch(ClassNotFoundException | 
            IllegalAccessException | 
            InstantiationException | 
            JRException ex){
        JOptionPane.showMessageDialog(null, "Data tidak dapat dicetak!","Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
        /*try{
            String NamaFile = "src/report/report1.jasper";   
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/barang","root","");    
            HashMap param = new HashMap();
            param.put("fakturdano", rcetak);
            JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, param, koneksi );
            JasperViewer.viewReport(JPrint, false);
        }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException | JRException ex){
            JOptionPane.showMessageDialog(null, "Data tidak dapat dicetak!","Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
	        
        try{
            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) conn;
            reportSource = "src/report/report1.jrxml";
            //reportDest = System.getProperty("user.dir") + "/src/report/report1.jasper";
            //String text = cetakfaktur.getText();
            HashMap parameter = new HashMap ();
            parameter.put("fakturdano", rcetak);
            System.out.println(rcetak);     
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameter,c);
            JasperViewer.viewReport(jasperPrint,false);
            }
        catch(Exception e){
            System.out.println(e);
        }*/
    }
}
