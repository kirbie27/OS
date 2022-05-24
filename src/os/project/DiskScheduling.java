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
                + "Enter Choice: ");
        String answer = input.nextLine();
        System.out.println("Your choice is: "+answer);
    }    
}
