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
              + "This is for Priority Preemptive\n");
         
       System.out.print("Input no. of processes [2-9]: ");
       int processes = input.nextInt();
       while (processes < 2 || processes > 9) {
            System.out.println("Invalid Input, try again.");
            System.out.print("Input number of processes: ");
            processes = input.nextInt();
        }
       
       int[] arrivalsArray = new int[processes];
       int[] burstsArray = new int[processes];
       int[] priorityNumbersArray = new int[processes];
       
       System.out.println("Input individual arrival time: ");
       for (int i=0;i<processes;i++){
           System.out.print("Arrival Time ["+(char) (64 + (i + 1))+"]: ");
           int arrivalTime = input.nextInt();
           arrivalsArray[i] = arrivalTime;
       }
       System.out.println("\nInput individual burst time: ");
       for (int i=0;i<processes;i++){
           System.out.print("Burst Time ["+(char) (64 + (i + 1))+"]: ");
           int burstTime = input.nextInt();
           burstsArray[i] = burstTime;
       }
       System.out.println("\nInput individual priority number: ");
       for (int i=0;i<processes;i++){
           System.out.print("Priority number ["+(char) (64 + (i+ 1))+"]: ");
           int priorityNumber = input.nextInt();
           priorityNumbersArray[i] = priorityNumber;
       }
       
       Priority priorityArray[] = new Priority[processes];
       for (int i = 0; i < processes; i++){
           priorityArray[i] = new Priority(i+1, arrivalsArray[i], burstsArray[i], priorityNumbersArray[i], 0, 0, 0, 0, burstsArray[i], false);
       }
       
       int totalWaitingTime = 0;
       int totalTurnAroundTime = 0;
       float averageWaitingTime = 0;
       float averageTurnAroundTime = 0;
       
       int currentTime = 0;
       int completed = 0;
       //int previous = 0;
       
       //process
       ArrayList<Integer> seekQ = new ArrayList<>();
       boolean processing = false;
       int prev = -1;
       while (completed != processes){
           //find the process with the minimum priority time among the processes that are in ready queue at currentTime
           int index = -1;
           int min = 999;
           //if the process is found
           for (int i=0; i< processes; i++){
               if ((priorityArray[i].arrivalTime <= currentTime) && priorityArray[i].isCompleted == false){
                   if (priorityArray[i].priorityNumber < min) {
                       min = priorityArray[i].priorityNumber;
                       index = i;
                   }
                   if (priorityArray[i].priorityNumber == min) {
                       if (priorityArray[i].arrivalTime < priorityArray[index].arrivalTime) {
                           min = priorityArray[i].priorityNumber;
                           index = i;
                       }
                   }
               }
           }
           if (index != -1) {
               if(!processing || (processing && prev != index)){
                   seekQ.add(index);
                   processing = true;
                   prev = index;
               }
             
                
               if (priorityArray[index].remainingBurst == priorityArray[index].burstTime) {
                  priorityArray[index].startTime = currentTime;
               }
           }
           
           
           priorityArray[index].remainingBurst -= 1;
           currentTime++;
           //previous = currentTime;
           if (priorityArray[index].remainingBurst == 0) {
               priorityArray[index].completionTime = currentTime;
               priorityArray[index].turnAroundTime = priorityArray[index].completionTime - priorityArray[index].arrivalTime;
               priorityArray[index].waitingTime = priorityArray[index].turnAroundTime - priorityArray[index].burstTime;
               
               totalWaitingTime += priorityArray[index].waitingTime;
               totalTurnAroundTime += priorityArray[index].turnAroundTime;
               
               priorityArray[index].isCompleted = true;
               completed++;
           }
       }
       averageWaitingTime = (float)totalWaitingTime/processes;
       averageTurnAroundTime = (float)totalTurnAroundTime/processes;
       
       //output
        System.out.println("\nWaiting time: \t\t\t Turnaround time: ");
        for (int i=0;i<priorityArray.length;i++){
            System.out.println("Process ["+(char) (64 + (i + 1))+"]:"+priorityArray[i].waitingTime+"\t\t\t Process ["+(char) (64 + (i + 1))+"]:"+priorityArray[i].turnAroundTime);
        }
        System.out.println("Average Waiting Time: " + averageWaitingTime + "\t Average Turnaround Time: " + averageTurnAroundTime);
        
        //print gant
        System.out.println("\nCPU Scheduling Gantt Chart:\n");
        for(int i = 0; i < seekQ.size(); i++){
            if(i == 0)
                System.out.printf("IDLE -> [%s]",(char) (64 + (seekQ.get(i) + 1)));
            else
                System.out.printf(" -> [%s]",(char) (64 + (seekQ.get(i) + 1)));
        }
    }
    
}

class Priority {
    int processNumber, arrivalTime, burstTime, priorityNumber, startTime, completionTime, turnAroundTime, waitingTime, remainingBurst;
    boolean isCompleted;
    Priority(int processNumber, int arrivalTime, int burstTime, int priorityNumber, int startTime, int completionTime, int turnAroundTime, int waitingTime, int remainingBurst, boolean isCompleted) {
        this.processNumber = processNumber;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priorityNumber = priorityNumber;
        this.startTime = startTime;
        this.completionTime = completionTime;
        this.turnAroundTime = turnAroundTime;
        this.waitingTime = waitingTime;
        this.remainingBurst = remainingBurst;
        this.isCompleted = isCompleted;
    }
}

