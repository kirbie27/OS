package os.project;
import java.util.*;
public class OSProject {

    static Scanner input = new Scanner(System.in);  
    public static void main(String[] args) {
        System.out.println("ICS2012 - Operating Systems\n"
                + "Final Exam - Alternative Assessment\n"
                + "Group 9\n"
                + "Members:\n"
                + "-Jan Andrei Carlos\n"
                + "-Marylaine Lumacad\n"
                + "-John Koby Reodica\n"
                + "-John Jeco Villanueva\n"
                + "-Joan Kirby C. Wenceslao");
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        String a;
       
        do 
        {
            System.out.println("Choose a scheduling simulation!");
            System.out.println("[A] CPU Scheduling");
            System.out.println("[B] Disk Scheduling");
            System.out.print("Enter A or B: ");
            String schedulingChoice = input.nextLine();
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            if(schedulingChoice.equalsIgnoreCase("A"))
            {
                CpuScheduling.runCpuScheduling();
            }
            else if (schedulingChoice.equalsIgnoreCase("B"))
            {
                DiskScheduling.runDiskScheduling();
            }
            System.out.println("\nDo you want to try again? (Y/N)");
             a = input.nextLine();
        }
        while(a.equalsIgnoreCase("Y"));
        
        
    }
 
}
