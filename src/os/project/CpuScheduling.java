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
            CpuSchedulingPreemptive.SJFP();
        } else if (answer.equalsIgnoreCase("B")) {
            CpuSchedulingPreemptive.priorityP();
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
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
              + "This is for First Come First Serve Non Preemptive");
            npScheduling(false);
        } else if (answer.equalsIgnoreCase("B")) {
            System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
              + "This is for Shortest Job First Non Preemptive");
            npScheduling(true);
        } else {
            System.out.println("Invalid Input. Please try again!");
        }
 
    }
    
    static void npScheduling(boolean isSJF){
        char[] symbols = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        Scanner input = new Scanner(System.in);
        int nNpProcesses = 0;
        
        while (nNpProcesses > 9 || nNpProcesses < 2){
            System.out.print("Input number of Processes [2-9]: ");
            nNpProcesses = Integer.parseInt(input.nextLine());
        }
        
        //Arrival Time I/O
        ArrayList<Integer> arrivalTimes = new ArrayList<>();
        System.out.println("Input individual arrival time: ");
        for(int i = 0; i<nNpProcesses; i++){
            int arrival = 0;
            System.out.print(symbols[i] + ": ");
            arrival = Integer.parseInt(input.nextLine());
            arrivalTimes.add(arrival);
        }
        
        System.out.println("");
        
        //Burst Time I/O
        ArrayList<Integer> burstTimes = new ArrayList<>();
        System.out.println("Input individual burst time: ");
        for(int i = 0; i<nNpProcesses; i++){
            int burst = 0;
            System.out.print(symbols[i] + ": ");
            burst = Integer.parseInt(input.nextLine());
            burstTimes.add(burst);
        }
        
        ArrayList<NpProcess> waitingNpProcesses = new ArrayList<>();
        for (int i=0; i<nNpProcesses; i++){
            waitingNpProcesses.add(new NpProcess(symbols[i], arrivalTimes.get(i), burstTimes.get(i)));
        }
        
        Collections.sort(waitingNpProcesses, new SortByArrivalTime());
        
        ArrayList<NpProcess> queue = new ArrayList<>();
        ArrayList<NpProcess> finishedNpProcesses = new ArrayList<>();
        
        int currentTime = waitingNpProcesses.get(0).getATime();
        
        if(isSJF){
            while(!waitingNpProcesses.isEmpty() && currentTime == waitingNpProcesses.get(0).getATime()){
                queue.add(waitingNpProcesses.get(0));
                waitingNpProcesses.remove(waitingNpProcesses.get(0));
            }
            Collections.sort(queue, new SortByBurstTime());
        }
        
//        queue.add(waitingNpProcesses.get(0));
        
//        waitingNpProcesses.remove(0);
        
        ArrayList<String> sequence = new ArrayList<>();
        sequence.add("IDLE");
        
        //Scheduling 
        //1 loop  = 1ms
        //Will run until queue and waiting NpProcess is empty
        while (!queue.isEmpty() || !waitingNpProcesses.isEmpty()){
            
            while(!waitingNpProcesses.isEmpty() && currentTime == waitingNpProcesses.get(0).getATime()){
                queue.add(waitingNpProcesses.get(0));
                waitingNpProcesses.remove(waitingNpProcesses.get(0));
            }
            
            currentTime++;
            
            
            
            //Current NpProcess in Queue to NpProcess
            if(!queue.isEmpty()){
                NpProcess currentNpProcess = queue.get(0);
                currentNpProcess.subtractBurst();

                //If NpProcess in Queue Finishes Execution
                if(currentNpProcess.getBTime() == 0){
                    currentNpProcess.finish(currentTime);
                    finishedNpProcesses.add(currentNpProcess);
                    queue.remove(currentNpProcess);

                    //Shortest Job: Sort the Queue by its Burst Time; 
                    //FCFS: No need to Sort
                    if(isSJF)
                        Collections.sort(queue, new SortByBurstTime());
                    
//                    System.out.println("C: " + currentNpProcess.getCTime() + "  -  Ta: " + currentNpProcess.getTaTime() 
//                            + "  -  W: " + currentNpProcess.getWTime());
                }
                sequence.add(currentNpProcess.getID() + "");
            } else {
                sequence.add("IDLE");
            }
        }
        
        printResults(finishedNpProcesses, sequence);
        
        
    }
    
    static void printResults(ArrayList<NpProcess> NpProcesses, ArrayList<String> sequence){
        Collections.sort(NpProcesses, new SortByID());
        double wAvg = 0;
        double tAvg = 0;
        System.out.println("\n\tTurnaround Time: \tWaiting Time:");
        for (int i = 0; i < NpProcesses.size(); i++) {
            NpProcess p = NpProcesses.get(i);
            wAvg += p.getWTime();
            tAvg += p.getTaTime();
            System.out.println(p.getID() + ":\t\t" + p.getTaTime() + "\t\t\t" + p.getWTime());
        }
        
        wAvg /= NpProcesses.size();
        tAvg /= NpProcesses.size();
        
        System.out.println("");
        System.out.println("Average Turnaround Time: " + tAvg + "\nAverage Waiting Time: " + wAvg);
        
        System.out.println("\nCPU Scheduling Gantt Chart: ");
        
//        boolean isSimilar = false;
        String s = "IDLE";
        for (int i = 0; i < sequence.size(); i++) {
            if(s.equalsIgnoreCase(sequence.get(i))){
                System.out.print("[" + sequence.get(i) + "]");
            } else {
                System.out.print("=>" + "[" + sequence.get(i) + "]");
            }
            s = sequence.get(i);
        }
        
        System.out.println("");
        
    }
}

class NpProcess{
    char id;
    int aTime;
    int initialBTime;
    int bTime;
    int cTime = 0;
    int taTime = 0;
    int wTime = 0;
    
    NpProcess(char id, int aTime, int bTime){
        this.id = id;
        this.aTime = aTime;
        this.bTime = bTime;
        this.initialBTime = bTime;
    }
    
    void subtractBurst() {
        this.bTime = this.bTime - 1;
    }
    
    void finish(int cTime){
        this.cTime = cTime;
        this.taTime = this.cTime - this.aTime;
        this.wTime = this.taTime - this.initialBTime;
    }
    
    char getID(){
        return this.id;
    }
    
    int getATime(){
        return this.aTime;
    }
    
    int getInitialBTime(){
        return this.initialBTime;
    }
    
    int getBTime(){
        return this.bTime;
    }
    
    int getCTime(){
        return this.cTime;
    }
    
    int getTaTime(){
        return this.taTime;
    }
    
    int getWTime(){
        return this.wTime;
    }
}

class SortByArrivalTime implements Comparator<NpProcess> {
    @Override
    public int compare (NpProcess a, NpProcess b){
        return a.aTime - b.aTime;
    }
}

class SortByBurstTime implements Comparator<NpProcess> {
    @Override
    public int compare (NpProcess a, NpProcess b){
        return a.bTime - b.bTime;
    }
}

class SortByID implements Comparator<NpProcess> {
    @Override
    public int compare (NpProcess a, NpProcess b){
        return a.id - b.id;
    }
}
