package koneksi;

import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {
     private static java.sql.Connection koneksi;
     
      public static java.sql.Connection getKoneksi(){
        if(koneksi == null){
          try{
              //String url = "jdbc:mysql://localhost/barang";
              String url = "jdbc:mysql://localhost:3308/barang";
              String user = "root";
              String password = "";
              
              DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
              
              koneksi = DriverManager.getConnection(url, user, password);
          }catch(SQLException t){
              System.out.println("Error Membuat Koneksi");
          }  
        }
        return koneksi;

    }
}
