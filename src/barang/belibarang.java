package barang;
import koneksi.*;
import menu.*;
import java.awt.event.KeyEvent;
import java.sql.*;
import koneksi.koneksi;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class belibarang extends javax.swing.JFrame {
    private DefaultTableModel model;
    private Connection conn = new koneksi().getKoneksi();
    ResultSet rs, rs1, rs2, rs3;
    PreparedStatement pst = null;
    Statement stat, stat1, stat2, stat3;
    double tot, hb, jum;
    boolean ada = false;
    public belibarang() {
        initComponents();
        textkbeli.setEnabled(false);
        texttotal.setEnabled(false);
        model = new DefaultTableModel();
        tableinput.setModel(model);
        model.addColumn("Kode Beli");
        model.addColumn("Kode Stock");
        model.addColumn("Nama Supplier");
        model.addColumn("Kategori Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Satuan Barang");
        model.addColumn("Jumlah Barang");
        model.addColumn("Harga Beli");
        model.addColumn("Total");
        model.addColumn("Tanggal");
        loadData();      
        tampilnamasupplier();
        tampilnamakategori();
        tampilnamabarang();
        tampilnamasatuan();
        kodebeli();
        kodestock();
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

            String sql = "SELECT * FROM belibarang";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                Object[] o = new Object[10];
                o[0] = r.getString("kodebeli");
                o[1] = r.getString("kodestock");
                o[2] = r.getString("namasupplier");
                o[3] = r.getString("namakategori");
                o[4] = r.getString("namabarang");
                o[5] = r.getString("namasatuan");
                o[6] = r.getString("jumlahbarang");
                o[7] = r.getString("hargabeli");
                o[8] = r.getString("total");
                o[9] = r.getString("tanggal");
                                
                model.addRow(o);
            }
            r.close();
            s.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e);
        }
    }
    private void tampilnamasupplier() {
        try{
            String sql = "SELECT * FROM supplier";
            PreparedStatement stat = conn.prepareStatement(sql);
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                cnsupplier.addItem(rs.getString(2));
            }
        }
        
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void tampilnamakategori() {
        try{
            String sql = "SELECT * FROM kategori";
            PreparedStatement stat = conn.prepareStatement(sql);
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                cnkategori.addItem(rs.getString(2));
            }
        }
        
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }    
    private void tampilnamabarang() {
        try{
            String query = "SELECT * FROM barang";
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
    private void tampilnamasatuan() {
        try{
            String query = "SELECT * FROM satuan";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){                
                cnsatuan.addItem(rs.getString("namasatuan"));
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void kodebeli() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM belibarang ORDER by kodebeli desc";
            ResultSet r = s.executeQuery(sql);
            
            if (r.next()) {
                String kbar = r.getString("kodebeli").substring(13);
                System.out.println(kbar);
                String AN = "" + (Integer.parseInt(kbar) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "0";
                } 
                else if (AN.length() == 2) {
                    Nol = "";
                }
                kbar = "6" + Nol + AN;
                String rsupplier = textksupplier.getText();
                String rkategori = textkkategori.getText();
                String rbarang = textkbarang.getText();
                String rsatuan = textksatuan.getText();
        try {
            textkbeli.setText(rsupplier + rkategori + rbarang + rsatuan + kbar);
        }
        catch (Exception e) {rkategori = "";}
            }
            else {
                String kbar = "601";
                String rsupplier = textksupplier.getText();
                String rkategori = textkkategori.getText();
                String rbarang = textkbarang.getText();
                String rsatuan = textksatuan.getText();
                try {
                    textkbeli.setText(rsupplier + rkategori + rbarang + rsatuan + kbar);
                }
                catch (Exception e) {rkategori = "";}
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void kodestock() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM stock ORDER by kodestock desc";
            ResultSet r = s.executeQuery(sql);
            
            if (r.next()) {
                String kbar = r.getString("kodestock").substring(1);
                String AN = "" + (Integer.parseInt(kbar) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "000";
                } 
                else if (AN.length() == 2) {
                    Nol = "00";
                }
                else if (AN.length() == 3) {
                    Nol = "0";
                }
                else if (AN.length() == 4) {
                    Nol = "";
                }
                textkstock.setText("7" + Nol + AN);
            }
            else {
                textkstock.setText("70001");
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void tampilkodesupplier(){
        try{
                int i = 1;
                String rsupplier = cnsupplier.getSelectedItem().toString();
                String sql = "SELECT * FROM supplier Where namasupplier LIKE '" + rsupplier + "' ORDER BY kodesupplier ASC";
                PreparedStatement stat = conn.prepareStatement(sql);
                ResultSet rs = stat.executeQuery(sql);
                
                rs.absolute(1);
                rsupplier = rs.getString("kodesupplier");
                textksupplier.setText(rsupplier);
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }        
    }
    private void tampilkodekategori(){
        try{
                int i = 1;
                String rkategori = cnkategori.getSelectedItem().toString();
                String sql = "SELECT * FROM kategori Where namakategori LIKE '" + rkategori + "' ORDER BY kodekategori ASC";
                PreparedStatement stat = conn.prepareStatement(sql);
                ResultSet rs = stat.executeQuery(sql);
                
                rs.absolute(1);
                rkategori = rs.getString("kodekategori");
                textkkategori.setText(rkategori);
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }        
    }
    private void tampilkodebarang(){
        try{
                int i = 1;
                String rbarang = cnbarang.getSelectedItem().toString();
                String sql = "SELECT * FROM barang Where namabarang LIKE '" + rbarang + "' ORDER BY kodebarang ASC";
                PreparedStatement stat = conn.prepareStatement(sql);
                ResultSet rs  = stat.executeQuery(sql);
                
                rs.absolute(i);
                rbarang = rs.getString("kodebarang");
                textkbarang.setText(rbarang);
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
    }
    private void tampilkodesatuan(){
        try{
                int i = 1;
                String rsatuan = cnsatuan.getSelectedItem().toString();
                String sql = "SELECT * FROM satuan Where namasatuan LIKE '" + rsatuan + "' ORDER BY kodesatuan ASC";
                PreparedStatement stat = conn.prepareStatement(sql);
                ResultSet rs = stat.executeQuery(sql);
                
                rs.absolute(i);
                rsatuan = rs.getString("kodesatuan");
                textksatuan.setText(rsatuan);
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtotal = new javax.swing.JLabel();
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
        textkbeli = new javax.swing.JTextField();
        cnsatuan = new javax.swing.JComboBox<>();
        cnkategori = new javax.swing.JComboBox<>();
        cnbarang = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jhbeli = new javax.swing.JLabel();
        texttotal = new javax.swing.JTextField();
        textjumlah = new javax.swing.JTextField();
        texthbeli = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        textkkategori = new javax.swing.JTextField();
        textkbarang = new javax.swing.JTextField();
        textksatuan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cnsupplier = new javax.swing.JComboBox<>();
        textksupplier = new javax.swing.JTextField();
        textkstock = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Beli Barang");
        setBackground(new java.awt.Color(0, 51, 51));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CARI");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 270, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama Barang");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, 20));

        jtotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtotal.setForeground(new java.awt.Color(255, 255, 255));
        jtotal.setText("Rp");
        jPanel1.add(jtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, -1, 20));

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
                "KodevBeli Barang", "Supplier", "Kategori", "Nama Barang", "Satuan", "Jumlah", "Harga Beli", "Total"
            }
        ));
        tableinput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableinputMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableinput);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 740, 130));

        textcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textcariKeyReleased(evt);
            }
        });
        jPanel1.add(textcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, 270, 30));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Beli Barang");
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

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 60));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Supplier");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Kategori");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, 20));

        textkbeli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textkbeliKeyTyped(evt);
            }
        });
        jPanel1.add(textkbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 230, 20));

        cnsatuan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cnsatuanItemStateChanged(evt);
            }
        });
        cnsatuan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cnsatuanMouseClicked(evt);
            }
        });
        cnsatuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnsatuanActionPerformed(evt);
            }
        });
        jPanel1.add(cnsatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 230, -1));

        cnkategori.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cnkategoriItemStateChanged(evt);
            }
        });
        cnkategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnkategoriActionPerformed(evt);
            }
        });
        jPanel1.add(cnkategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 230, -1));

        cnbarang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cnbarangItemStateChanged(evt);
            }
        });
        jPanel1.add(cnbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 230, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Satuan");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, 20));

        jhbeli.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jhbeli.setForeground(new java.awt.Color(255, 255, 255));
        jhbeli.setText("Rp");
        jPanel1.add(jhbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 30, 20));
        jPanel1.add(texttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 190, -1));
        jPanel1.add(textjumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 230, -1));

        texthbeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texthbeliActionPerformed(evt);
            }
        });
        jPanel1.add(texthbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 190, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Jumlah");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, 20));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Harga Beli");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Total");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, -1, 20));

        jButton1.setText("TOTAL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 340, -1));
        jPanel1.add(textkkategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 190, -1));
        jPanel1.add(textkbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 190, -1));
        jPanel1.add(textksatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 190, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Kode Beli Barang");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, 20));

        cnsupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnsupplierActionPerformed(evt);
            }
        });
        jPanel1.add(cnsupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 230, -1));
        jPanel1.add(textksupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 190, -1));
        jPanel1.add(textkstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 140, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 51, 255));
        jButton2.setText("STOCK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, 300, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, -1));

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
            textjumlah.getText().equals("") ||
            texthbeli.getText().equals("") ||
            texttotal.getText().equals("")
        ){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "PT. Catur Sentosa Anugerah", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String tkodebeli     = textkbeli.getText();
            String tkodestock    = textkstock.getText();
            String tsupplier     = cnsupplier.getSelectedItem().toString();
            String tkategori     = cnkategori.getSelectedItem().toString();
            String tnamabarang   = cnbarang.getSelectedItem().toString();
            String tsatuan       = cnsatuan.getSelectedItem().toString();
            String tjumlah       = textjumlah.getText();
            String thargabeli    = texthbeli.getText();
            String ttotal        = texttotal.getText();
                      
            try {
                long millis=System.currentTimeMillis();  
                java.sql.Date date=new java.sql.Date(millis);  
                System.out.println(date); 
                String ttanggal = date.toString();
                Connection c = koneksi.getKoneksi();
                String sql = "INSERT INTO belibarang VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement p = c.prepareStatement(sql);                
                p.setString(1, tkodebeli);
                p.setString(2, tkodestock);
                p.setString(3, tsupplier);
                p.setString(4, tkategori);
                p.setString(5, tnamabarang);
                p.setString(6, tsatuan);
                p.setString(7, tjumlah);
                p.setString(8, thargabeli);
                p.setString(9, ttotal);
                p.setString(10, ttanggal);   
                p.executeUpdate();
                p.close();               
            } 
            catch (SQLException e) {
                System.out.println("Terjadi Error Beli");
            }
            
            try {
                Connection c = koneksi.getKoneksi();
                String sql = "INSERT INTO stock VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement p = c.prepareStatement(sql);                
                p.setString(1, tkodestock);
                p.setString(2, tkategori);
                p.setString(3, tnamabarang);
                p.setString(4, tsatuan);
                p.setString(5, tjumlah);
                p.setString(6, thargabeli);
                p.setString(7, ttotal);  
                p.executeUpdate();
                p.close();               
            } 
            catch (SQLException e) {
                System.out.println("Terjadi Error Stock");
            } 

            finally {
                loadData();
                kodebeli();
                textjumlah.setText("");
                texthbeli.setText("");
                texttotal.setText("");

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
         
        String tablesupplier = (String) model.getValueAt(i, 2);
        cnsupplier.setSelectedItem(tablesupplier);

        String tablekategori = (String) model.getValueAt(i, 3);
        cnkategori.setSelectedItem(tablekategori);

        String tablenamabarang = (String) model.getValueAt(i, 4);
        cnsatuan.setSelectedItem(tablenamabarang);

        String tablesatuan = (String) model.getValueAt(i, 5);
        cnkategori.setSelectedItem(tablesatuan);
        
        String tablejumlah = (String) model.getValueAt(i, 6);
        textjumlah.setText(tablejumlah);
        
        String tablehargabeli = (String) model.getValueAt(i, 7);
        texthbeli.setText(tablehargabeli);
        
        String tabletotal = (String) model.getValueAt(i, 8);
        texttotal.setText(tabletotal);
        
        String tablekodebeli = (String) model.getValueAt(i, 0);
        textkbeli.setText(tablekodebeli);
        textkbeli.setEnabled(false);
        
        String tablekodestock = (String) model.getValueAt(i, 1);
        textkstock.setText(tablekodestock);
        textkstock.setEnabled(false);
        
    }//GEN-LAST:event_tableinputMouseClicked
    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        try {
            String sql ="delete from belibarang where kodebeli='" +textkbeli.getText()+"'";
            java.sql.Connection conn=(Connection)koneksi.getKoneksi();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Berhasil Di Hapus");
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        try {
            String sql ="delete from stock where kodestock='" +textkstock.getText()+"'";
            java.sql.Connection conn=(Connection)koneksi.getKoneksi();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Stock Berhasil Di Hapus");
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        loadData();
        kodebeli();
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
            try{
                Connection conn = koneksi.getKoneksi();
                String sql = "UPDATE belibarang SET kodestock=?, namasupplier=?, namakategori=?, namabarang=?, namasatuan=?, jumlahbarang=?, hargabeli=?, total=? where kodebeli='"+textkbeli.getText()+"'";
                PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, textkstock.getText());
                p.setString(2, cnsupplier.getSelectedItem().toString());
                p.setString(3, cnkategori.getSelectedItem().toString());
                p.setString(4, cnbarang.getSelectedItem().toString());
                p.setString(5, cnsatuan.getSelectedItem().toString());
                p.setString(6, textjumlah.getText());
                p.setString(7, texthbeli.getText());
                p.setString(8, texttotal.getText());
                p.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah"); 
                textkbeli.requestFocus();
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah"+e);
            }
            try{
                Connection conn = koneksi.getKoneksi();
                String sql = "UPDATE stock SET namakategori=?, namabarang=?, namasatuan=?, jumlahbarang=?, hargabeli=?, total=? where kodestock='"+textkstock.getText()+"'";
                PreparedStatement p = conn.prepareStatement(sql); 
                p.setString(1, cnkategori.getSelectedItem().toString());
                p.setString(2, cnbarang.getSelectedItem().toString());
                p.setString(3, cnsatuan.getSelectedItem().toString());
                p.setString(4, textjumlah.getText());
                p.setString(5, texthbeli.getText());
                p.setString(6, texttotal.getText());
                p.executeUpdate();
                JOptionPane.showMessageDialog(null, "Stock Data Berhasil Diubah"); 
                textkbeli.requestFocus();
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Stock Data Gagal Diubah"+e);
            }
            finally {
                loadData();
                kodebeli();
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
            Connection conn = koneksi.getKoneksi();
            Statement s = conn.createStatement();

            String sql = "SELECT * FROM belibarang WHERE "
                    + "kodebeli LIKE '%" + textcari.getText() 
                    + "%' OR kodestock LIKE'%" + textcari.getText() 
                    + "%' OR namasupplier LIKE'%" + textcari.getText() 
                    + "%' OR namakategori LIKE'%" + textcari.getText() 
                    + "%' OR namabarang like'" + textcari.getText() 
                    + "%' OR namasatuan like'%" + textcari.getText()
                    + "%' OR jumlah like'%" + textcari.getText()
                    + "%' OR hargabeli like'%" + textcari.getText()
                    + "%' OR total like'%" + textcari.getText()
                    + "%' OR tanggal like'%" + textcari.getText() + "%'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[10];
                o[0] = r.getString("kodebeli");
                o[1] = r.getString("kodestock");
                o[2] = r.getString("namasupplier");
                o[3] = r.getString("namakategori");
                o[4] = r.getString("namabarang");
                o[5] = r.getString("namasatuan");
                o[6] = r.getString("jumlahbarang");
                o[7] = r.getString("hargabeli");
                o[8] = r.getString("total");
                o[9] = r.getString("tanggal");
                               
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
    }//GEN-LAST:event_textcariKeyReleased

    private void textkbeliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textkbeliKeyTyped
    }//GEN-LAST:event_textkbeliKeyTyped

    private void cnsatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnsatuanActionPerformed

    }//GEN-LAST:event_cnsatuanActionPerformed

    private void cnsatuanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cnsatuanItemStateChanged
        tampilkodesatuan();
        kodebeli();
    }//GEN-LAST:event_cnsatuanItemStateChanged

    private void cnsatuanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cnsatuanMouseClicked

    }//GEN-LAST:event_cnsatuanMouseClicked

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

    private void cnkategoriItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cnkategoriItemStateChanged
        tampilkodekategori();
        kodebeli();
    }//GEN-LAST:event_cnkategoriItemStateChanged

    private void cnkategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnkategoriActionPerformed

    }//GEN-LAST:event_cnkategoriActionPerformed

    private void cnbarangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cnbarangItemStateChanged
        tampilkodebarang();
        kodebeli();
    }//GEN-LAST:event_cnbarangItemStateChanged

    private void cnsupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnsupplierActionPerformed
        tampilkodesupplier();
        kodebeli();
    }//GEN-LAST:event_cnsupplierActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        stock fb = new stock();
        fb.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new belibarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bedit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bsimpan;
    private javax.swing.JComboBox<String> cnbarang;
    private javax.swing.JComboBox<String> cnkategori;
    private javax.swing.JComboBox<String> cnsatuan;
    private javax.swing.JComboBox<String> cnsupplier;
    private javax.swing.JButton exit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jhbeli;
    private javax.swing.JLabel jtotal;
    private javax.swing.JTable tableinput;
    private javax.swing.JTextField textcari;
    private javax.swing.JTextField texthbeli;
    private javax.swing.JTextField textjumlah;
    private javax.swing.JTextField textkbarang;
    private javax.swing.JTextField textkbeli;
    private javax.swing.JTextField textkkategori;
    private javax.swing.JTextField textksatuan;
    private javax.swing.JTextField textkstock;
    private javax.swing.JTextField textksupplier;
    private javax.swing.JTextField texttotal;
    // End of variables declaration//GEN-END:variables
    private void konek(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/barang","root","");
            stat=conn.createStatement(rs.TYPE_SCROLL_SENSITIVE,rs.CONCUR_UPDATABLE);
            rs=stat.executeQuery("select * from belibarang");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }
    }
    private void display(){
        try{
            String sql = "select * from belibarang";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            tableinput.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
