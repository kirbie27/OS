package os.project;
import java.util.*;

public class CpuScheduling {
    public static void runCpuScheduling(){ 
        
        Scanner input = new Scanner(System.in);
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.print("Welcome to the CPU Scheduling Simulator.\n"
                + "Please Choose between the options\n"
                + "[A] Preemptive\n"
                + "[B] Non-Preemptive\n"
                + "Enter Choice: ");
        String answer = input.nextLine();
        System.out.println("Your choice is: "+answer);
        if(answer.equalsIgnoreCase("A"))
            preemptiveCpuScheduling();
        else if(answer.equalsIgnoreCase("B"))
            nonpreemptiveCpuScheduling();
        else
            System.out.println("Invalid Input. Please try again!");
    }
    
    public static void preemptiveCpuScheduling(){
        Scanner input = new Scanner(System.in);
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "Preemptive CPU Scheduling, please choose an algorithm to simulate.\n"
                + "[A] Shortest Job First (SJF)\n"
                + "[B} Priority Scheduling\n"
                + "Enter choice: ");
        String answer = input.nextLine();
        System.out.println("You chose: "+answer);
        if (answer.equalsIgnoreCase("A")) {
            CpuSchedilingPreemptive.SJFP();
        } else if (answer.equalsIgnoreCase("B")) {
            CpuSchedilingPreemptive.priorityP();
        } else {
            System.out.println("Invalid Input. Please try again!");
        }
    }
    
    public static void nonpreemptiveCpuScheduling(){
        Scanner input = new Scanner(System.in);
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "Preemptive CPU Scheduling, please choose an algorithm to simulate.\n"
                + "[A] First Come First Serve (FCFS)\n"
                + "[B} Shortest Job First (SJG)\n"
                + "Enter choice: ");
        String answer = input.nextLine();
        System.out.println("You chose: "+answer);
        if (answer.equalsIgnoreCase("A")) {
            FCFSNP();
        } else if (answer.equalsIgnoreCase("B")) {
            SJFNP();
        } else {
            System.out.println("Invalid Input. Please try again!");
        }
 
    }

    
    public static void FCFSNP(){
        //First come first served non preemptive
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
              + "This is for First Come First Serve Non Preemptive");
    }
    
    public static void SJFNP(){
         //Shortest Job First Non-Preemptive
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
              + "This is for Shortest Job First Non Preemptive");
    }
    
}
