package os.project;
import java.util.*;

public class DiskScheduling {
    static void runDiskScheduling(){
        Scanner input = new Scanner(System.in);
        System.out.print("Welcome to the Disk Scheduling Simulator.\n"
                + "Please Choose between the options\n"
                + "[A] First Come First Serve (FCFS)\n"
                + "[B] Shortest Seek Time First (SSTF)\n"
                + "[C] Scan\n"
                + "[D] C-Scan\n"
                + "Enter Choice: ");
        String answer = input.nextLine();
        System.out.println("Your choice is: "+answer);
    }    
   
    public static void FCFSD(){
        //First Come First Serve Disk Scheduling
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "This is for First Come First Serve Disk Scheduling");
    }

    public static void SSTFD(){
        //Shortest Seek Time First Disk Scheduling
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "This is for Shortest Seek Time First Disk Scheduling");
    }
    
    public static void SCAND(){
        //Scan Disk Scheduling
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "This is for Scan Disk Scheduling Disk Scheduling");
    }
    
    public static void CSCAND(){
        //C-Scan Disk Scheduling
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "This is for C-Scan Disk Scheduling");
    }
    
}
