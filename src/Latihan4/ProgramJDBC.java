package Latihan4;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author G.Kristha Program by : Gerardus Kristha_215314004
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
        System.out.println("Menu");
        System.out.println("1. Lihat Isi Tabel");
        System.out.println("2. Tambah Data");
        System.out.println("3. Hapus 1 Data");
        System.out.println("5. Keluar");
        int input = 0;
        while (input != 5) {
            System.out.print("Pilih Menu?");
            Scanner sc = new Scanner(System.in);
            input = sc.nextInt();
            switch (input) {
                case 1:
                    p.showData();
                    break;
                case 2:
                    p.inputData();
                    break;
                case 3:
                    p.hapusData();
                    break;
                default:
                    break;
            }
        }

    }

    private Connection getConnection() {
        String host = "localhost";
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
            conn = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + "/" + db, usr, pwd);
        } catch (SQLException e) {
            System.out.println("Maaf, koneksi tidak berhasil");
            System.out.println(e.getMessage());
        }
        if (conn != null) {
            System.out.println("Koneksi ke database berhasil terbentuk");
        } else {
            System.out.println("Maaf, koneksi ke database gagal terbentuk");
        }
        return conn;
    }

    public void showData() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        conn = this.getConnection();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from mahasiswa");

            System.out.println("Nim\t\tNama\t\tIPK");
            while (rs.next()) {
                System.out.println(rs.getString(1)
                        + "\t" + rs.getString(2) + " \t\t" + rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void inputData() {
        Connection conn = null;
        PreparedStatement ps = null;

        conn = this.getConnection();

        Scanner sc = new Scanner(System.in);
        System.out.println("\nINPUT DATA");
        System.out.print("Masukkan NIM: ");
        String nim = sc.next();
        System.out.print("Masukkan NAMA: ");
        String nama = sc.next();
        System.out.print("Masukkan IPK: ");
        double ipk = sc.nextDouble();

        try {
            ps = conn.prepareStatement("insert into mahasiswa values(?,?,?)");
            ps.setString(1, nim);
            ps.setString(2, nama);
            ps.setDouble(3, ipk);
            ps.executeUpdate();
//            conn.commit();
            System.out.println("\nData sudah ditambahkan");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void hapusData() {
        Connection conn = null;
        PreparedStatement ps = null;

        conn = this.getConnection();
        Scanner sc = new Scanner(System.in);
        System.out.println("\nDELETE DATA");
        System.out.print("Masukkan NIM yang akan dihapus: ");
        String nim = sc.next();

        try {
            ps = conn.prepareStatement("delete from mahasiswa where nim = ?");
            ps.setString(1, nim);
            ps.executeUpdate();
//            conn.commit();
            System.out.println("\nData sudah di hapus");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
