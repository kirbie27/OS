package os.project;
import java.util.*;

public class CpuSchedulingPreemptive {
    
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
        Scanner input = new Scanner(System.in);
        //Priority Preemptive
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
              + "This is for Priority Preemptive");
         
       System.out.print("Input no. of processes [2-9]: ");
       int processes = input.nextInt();
       HashMap<Integer, Integer> arrivalsMap = new HashMap<>();
       ArrayList<Integer> arrivalsArray = new ArrayList<Integer>();
       ArrayList<Integer> burstsArray = new ArrayList<Integer>();
       ArrayList<Integer> priorityArray = new ArrayList<Integer>();
       System.out.println("Input individual arrival time: ");
       for (int i=0;i<processes;i++){
           System.out.println("Arrival Time ["+(i+1)+"]: ");
           int arrivalTime = input.nextInt();
           arrivalsArray.add(arrivalTime);
           arrivalsMap.put(i, arrivalTime);
       }
       System.out.println("Input individual burst time: ");
       for (int i=0;i<processes;i++){
           System.out.println("Burst Time ["+(i+1)+"]: ");
           int burstTime = input.nextInt();
           burstsArray.add(burstTime);
       }
       System.out.println("Input individual priority number: ");
       for (int i=0;i<processes;i++){
           System.out.println("Priority number ["+(i+1)+"]: ");
           int priorityNumber = input.nextInt();
           priorityArray.add(priorityNumber);
       }
       
       ArrayList<Integer> waitingArray = new ArrayList<Integer>();
       ArrayList<Integer> turnaroundArray = new ArrayList<Integer>();
       int averageWaitingTime = 0;
       int averageTurnaroundTime = 0;
       int currentTime = 0;
       
       //process
       //start with lowest arrival
       // if same arrival check
       
       //turnaround time = burst time + waiting time
       
       //output
        System.out.println("Waiting time: \t\tTurnaround time: ");
        for (int i=0;i<waitingArray.size();i++){
            System.out.println("Process ["+(i+1)+"]:"+waitingArray.get(i)+"\t\t Process ["+(i+1)+"]:"+turnaroundArray.get(i));
        }
        System.out.println("Average Waiting Time: " + averageWaitingTime + "\t\tAverage Turnaround Time: " + averageTurnaroundTime);
       
    }
    
}
