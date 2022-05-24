package os.project;
import java.util.*;

public class CpuScheduling {
    public static void runCpuScheduling(){
        Scanner input = new Scanner(System.in);
        System.out.print("Welcome to the CPU Scheduling Simulator.\n"
                + "Please Choose between the options\n"
                + "[A] Preemptive\n"
                + "[B] Non-Preemptive\n"
                + "Enter Choice: ");
        String answer = input.nextLine();
        System.out.println("Your choice is: "+answer);
    }
    
    public void preemptiveCpuScheduling(){
        
    }
    
    public void nonpreemptiveCpuScheduling(){
        
    }
    
    
}
