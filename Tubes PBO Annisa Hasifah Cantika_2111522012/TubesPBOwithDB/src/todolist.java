import java.util.Scanner;

//Interface
public class todolist implements CRUD {

    // int pilih;
    // int idTask;
    // String namaTask;
    // String jenisTask;
    // String urgentTask;

    static Integer id;
    static String nama_task;
    static String jenis_task;
    static String penting_task;
    

    static Scanner input = new Scanner(System.in);
    static Scanner inputan = new Scanner(System.in);

   
    public void IdTask() {
        System.out.print("\nMasukkan no ID : ");
        id =input.nextInt();
        input.nextLine();
    }

    public void NamaTask() {
        System.out.print("\nMasukkan nama Task : ");
        nama_task = input.nextLine();
    }

    public void JenisTask() {
        System.out.print("\nMasukkan jenis Task : ");
        jenis_task = input.nextLine();
    }

    public void UrgentTask() {
        System.out.print("\nApakah Task urgent? : ");
        penting_task = input.nextLine();  
    }

         
    

    

    



}
