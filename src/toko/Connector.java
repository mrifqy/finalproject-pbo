
package toko;

import java.sql.*;

public class Connector {
    String DBurl = "jdbc:mysql://localhost/tokokomputer";
    String DBusername = "root";
    String DBpassword = "";
    Connection koneksi;
    Statement statement;
    public Connector() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = (Connection) DriverManager.getConnection(DBurl,DBusername,DBpassword);
            System.out.println("Koneksi Berhasil");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Koneksi gagal");
        }
    }

    
}
