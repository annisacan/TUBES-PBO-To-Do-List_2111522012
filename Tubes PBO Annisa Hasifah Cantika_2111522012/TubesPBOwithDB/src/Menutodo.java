import java.util.InputMismatchException;
import java.util.Scanner;

import java.sql.*;

// Tugas Besar PBO 
// Nama   : Annisa Hasifah Cantika (2111522012)
// Kelas  : B

//inheritance
public class Menutodo extends todolist {

    // static Scanner scanner;
    static Connection conn;

    public static void main(String[] args) {

        // CRUD
        try (Scanner input = new Scanner(System.in)) {
            String pilihanUser;
            boolean isLanjutkan = true;

            String url = "jdbc:mysql://localhost:3306/todolist";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, "root", "");
                System.out.println("Class Driver ditemukan\n");
                System.out.println("=!! Hello Guys !!=\n");

                while (isLanjutkan) {
                    System.out.println("---------------------------------------");
                    System.out.println("Produktifkan Hari Anda dengan Aplikasi");
                    System.out.println("              To-Do List");
                    System.out.println("---------------------------------------");
                    System.out.println("    1. Lihat  To-Do List");
                    System.out.println("    2. Tambah Task");
                    System.out.println("    3. Edit Task");
                    System.out.println("    4. Hapus  Task");
                    System.out.print("\n> Pilihan anda (1/2/3/4): ");
                    pilihanUser = input.next();
                    input.nextLine();

                    switch (pilihanUser) {
                        case "1":
                            lihatdata();
                            break;
                        case "2":
                            tambahdata();
                            break;
                        case "3":
                            ubahdata();
                            break;
                        case "4":
                            hapusdata();
                            break;
                        default:
                            System.err.println("\nInput anda tidak ditemukan\nInputkan [1-4]");
                    }

                    System.out.print("\n> Apakah Anda ingin melanjutkan [y/n]? ");
                    pilihanUser = input.next();
                    input.nextLine();
                    isLanjutkan = pilihanUser.equalsIgnoreCase("y");
                }
                System.out.println("\nIngatlah deadline selalu menunggumu\n");

            } catch (ClassNotFoundException ex) {
                System.err.println("\nDriver Error\n");
                System.exit(0);
            } catch (SQLException e) {
                System.err.println("Tidak Ada Koneksi");
            }
        }
    }

    private static void lihatdata() throws SQLException {
        String sql = "SELECT * FROM task";
        try {
            System.out.println("\n=======================================================");
                   String text1 = "                    List Task User";
            System.out.println(text1.toUpperCase());
            System.out.println("=======================================================");

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            System.out.println("| ID |   Kategori   |      Nama Task     | Urgent Task |");
            System.out.println("--------------------------------------------------------");
            while (result.next()) {

                int id = result.getInt("id");
                String nama_task = result.getString("nama_task");
                String jenis_task = result.getString("jenis_task");
                String penting_task = result.getString("penting_task");

                System.out.println(
                        String.format("|  %-1d | %-12s | %-17s  | %-11s |", id, jenis_task, nama_task, penting_task));

            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static void tambahdata() throws SQLException {
        System.out.println("\n==================================");
        String text2 = "      Tambah Task";
        System.out.println(text2.toUpperCase());
        System.out.println("==================================");

        try {

            todolist trans = new todolist();
            // menginput data
            trans.IdTask();
            trans.NamaTask();
            trans.JenisTask();
            trans.UrgentTask();

            String sql = "INSERT INTO task (id, nama_task, jenis_task, penting_task) VALUES ('" + id + "','" + nama_task
                    + "','" + jenis_task + "','" + penting_task + "')";

            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("\nBerhasil input data");

        } catch (SQLException e) {
            System.err.println("\nTerjadi Kesalahan Input Data");
        } catch (InputMismatchException e) {
            System.err.println("\nInputlah dengan angka saja");
        }
    }

    private static void ubahdata() throws SQLException {
        System.out.println("\n==================================");
        String text3 = "        Edit Task";
        System.out.println(text3.toUpperCase());
        System.out.println("==================================");

        try {
            try {
                lihatdata();
                {
                    System.out.print("\n> Masukkan ID task yang akan di ubah atau update : ");
                    Integer id = Integer.parseInt(inputan.nextLine());

                    String sql = "SELECT * FROM task WHERE id = " + id;

                    Statement statement = conn.createStatement();
                    ResultSet result = statement.executeQuery(sql);

                    if (result.next()) {

                        todolist trans = new todolist();
                        // menginput data
                        trans.NamaTask();
                        trans.JenisTask();
                        trans.UrgentTask();

                        sql = "UPDATE task SET nama_task='" + nama_task + "',jenis_task = '"
                                + jenis_task + "',penting_task= '" + penting_task + "' WHERE id='" + id + "'";
                        // System.out.println(sql);

                        if (statement.executeUpdate(sql) > 0) {
                            System.out.println("\nBerhasil memperbaharui Task (ID " +id+ ")");
                        }
                    }
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println("\nTerjadi kesalahan dalam mengedit data");
                System.err.println(e.getMessage());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void hapusdata() {
        System.out.println("\n==================================");
        String text4 = "         Hapus Data To-do List";
        System.out.println(text4.toUpperCase());
        System.out.println("==================================");

        try {
            try {
                lihatdata();
                System.out.print("\n> Ketik ID task yang akan Anda Hapus : ");
                Integer id = Integer.parseInt(inputan.nextLine());

                String sql = "DELETE FROM task WHERE id = " + id;
                Statement statement = conn.createStatement();
                // ResultSet result = statement.executeQuery(sql);

                if (statement.executeUpdate(sql) > 0) {
                    System.out.println("\nBerhasil menghapus data task (ID " + id + ")");
                }
            } catch (SQLException e) {
                System.out.println("\nTerjadi kesalahan dalam menghapus data task");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
