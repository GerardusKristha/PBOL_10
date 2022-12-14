package Latihan1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author G.Kristha
 * Program by : Gerardus Kristha_215314004
 */
public class ProgramJDBC {
    public static void main(String[] args) {
        System.out.println("PROGRAM LATIHAN JDBC");
        System.out.println("====================");
        
        System.out.println("Mencoba membuat koneksi ke database...");
        ProgramJDBC p = new ProgramJDBC();
        Connection conn = p.getConnection();
        
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }

    private Connection getConnection() {
        String host ="localhost";
        String port = "1521";
        String db = "xepdb1";
        String usr = "hr";
        String pwd = "kristhabayu10";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Maaf, driver class tidak ditemukan");
            System.out.println(e.getMessage());
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@"+host+":"+port+"/"+db,usr,pwd);
        } catch (SQLException e) {
            System.out.println("Maaf, koneksi tidak berhasil");
            System.out.println(e.getMessage());
        }
        if(conn!=null){
            System.out.println("Koneksi ke database berhasil terbentuk");
        }
        else{
            System.out.println("Maaf, koneksi ke database gagal terbentuk");
        }
        return conn;
    }
}
