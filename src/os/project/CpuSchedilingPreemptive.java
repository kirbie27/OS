package os.project;
import java.util.*;

public class CpuSchedilingPreemptive {
    
    public static void SJFP(){
        Scanner input = new Scanner(System.in);
        //Shortest Job First Preemptive
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "This is for Shortest Job First Preemptive\n");
        
        
       System.out.print("Input number of processses (Minimum of 2, Maximum of 9): ");
        int processes = Integer.parseInt(input.nextLine());
        while (processes < 2 || processes > 9) {
            System.out.println("Invalid Input, try again.");
            System.out.print("Input number of processes: ");
            processes = Integer.parseInt(input.nextLine());
        }
       
     
        SJFP.run(processes);
        System.out.print("baby");

    }
    
    public static void priorityP(){
        //Priority Preemptive
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
              + "This is for Priority Preemptive");
    }
    
}
