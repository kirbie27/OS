package os.project;
import java.util.*;

public class DiskScheduling {
    static void runDiskScheduling(){
        //Ask user to choose from an option and redirects them to the proper functions.
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        Scanner input = new Scanner(System.in);
        System.out.print("Welcome to the Disk Scheduling Simulator.\n"
                + "Please Choose between the options\n"
                + "[A] First Come First Serve (FCFS)\n"
                + "[B] Shortest Seek Time First (SSTF)\n"
                + "[C] C-Scan\n"
                + "[D] C-Look\n"
                + "Enter Choice: ");
        String answer = input.nextLine();
        System.out.println("Your choice is: "+answer);
        if (answer.equalsIgnoreCase("A")) {
            FCFSD();
        } else if (answer.equalsIgnoreCase("B")) {
            SSTFD();
        } else if (answer.equalsIgnoreCase("C")){
            CSCAND();
        } else if(answer.equalsIgnoreCase("D")) {
            CLOOKD();
        } else {
            System.out.println("Invalid Input. Please try again!");
        }
    }    
   
    public static void FCFSD(){
        Scanner input = new Scanner(System.in);
        //First Come First Serve Disk Scheduling
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "This is for First Come First Serve Disk Scheduling\n");
        //inputs: current position, track size, Requests (Max of 10), location of requests
        //outputs: Total Head Movement, Average Seek Time, Gant Chart
        System.out.print("Input track starting position: ");
        int start = Integer.parseInt(input.nextLine());
        System.out.print("Input track size: ");
        int size = Integer.parseInt(input.nextLine());
        System.out.print("Input number of requests (Maximum of 10): ");
        int requests = Integer.parseInt(input.nextLine());
        while (requests < 1 || requests > 10) {
            System.out.println("Invalid Input, try again.");
            System.out.print("Input number of requests (Maximum of 10): ");
            requests = Integer.parseInt(input.nextLine());
        }
        ArrayList<Request> requestQ = new ArrayList<>();
        ArrayList<Request> seekQ = new ArrayList<>();
        char name = 'A';
        
        //getting request track positions
        for(int i = 0; i < requests; i++) {
            System.out.printf("Track [%s]: ", String.valueOf(name));
            int requestPosition = Integer.parseInt(input.nextLine());
            while(requestPosition < 0 || requestPosition > size) {
                System.out.println("Invalid Input, try again.");
                System.out.printf("Track [%s]: ", String.valueOf(name));
                requestPosition = Integer.parseInt(input.nextLine());
            }
            requestQ.add(new Request(String.valueOf(name), requestPosition));
            name++;
        }
        
        int currentPosition = start;
        int totalHeadMovement = 0;
        double averageSeekTime = 0; 
        //iterates through the input request list and puts them into the seeqQ list as the algorithm is FCFS.
        for (int i = 0; i < requests; i++) {
            int track = requestQ.get(i).location;
            totalHeadMovement+= Math.abs(currentPosition - track);
            currentPosition = track;
            seekQ.add(requestQ.get(i));
        }
        averageSeekTime = (double)totalHeadMovement / (double) requests;
        displayResults(start, totalHeadMovement, averageSeekTime, seekQ);
    }
    
    public static void displayResults(int start, int totalHeadMovement, double averageSeekTime, ArrayList<Request> seekQ) {
        //Takes in the inputs that shall be displayed to test the results of the algorithm.
        System.out.println("Total Head Movement: "+totalHeadMovement);
        System.out.println("Average Seek Time: "+averageSeekTime);
        System.out.println("Track Movement:");
        for (int i = 0; i < seekQ.size(); i++) {
            if(i == 0)
                System.out.printf("%d -> [%s = %d]",start, seekQ.get(i).name, seekQ.get(i).location);
            else
                System.out.printf(" -> [%s = %d]", seekQ.get(i).name, seekQ.get(i).location);
        }
        System.out.println(" -> END");
    }
    
    public static void SSTFD(){
        Scanner input = new Scanner(System.in);
        //Shortest Seek Time First Disk Scheduling
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "This is for Shortest Seek Time First Disk Scheduling\n");
        //inputs: current position, track size, Requests (Max of 10), location of requests
        //outputs: Total Head Movement, Average Seek Time, Gant Chart
        System.out.print("Input track starting position: ");
        int start = Integer.parseInt(input.nextLine());
        System.out.print("Input track size: ");
        int size = Integer.parseInt(input.nextLine());
        System.out.print("Input number of requests (Maximum of 10): ");
        int requests = Integer.parseInt(input.nextLine());
        while (requests < 1 || requests > 10) {
            System.out.println("Invalid Input, try again.");
            System.out.print("Input number of requests (Maximum of 10): ");
            requests = Integer.parseInt(input.nextLine());
        }
        ArrayList<Request> requestQ = new ArrayList<>();
        ArrayList<Request> seekQ = new ArrayList<>();
        char name = 'A';
        
        //getting request track positions
        for(int i = 0; i < requests; i++) {
            System.out.printf("Track [%s]: ", String.valueOf(name));
            int requestPosition = Integer.parseInt(input.nextLine());
            while(requestPosition < 0 || requestPosition > size) {
                System.out.println("Invalid Input, try again.");
                System.out.printf("Track [%s]: ", String.valueOf(name));
                requestPosition = Integer.parseInt(input.nextLine());
            }
            requestQ.add(new Request(String.valueOf(name), requestPosition));
            name++;
        }
        
        int currentPosition = start;
        int totalHeadMovement = 0;
        double averageSeekTime = 0; 
        //iterates through the request list, and finds the minimum seek time from the current position, and adds it to the seekQ list.
        //computes the totalHeadMovement as it iterates.
        for (int i = 0; i < requests; i++) {
            Request r = findMinRequest(currentPosition, requestQ);
            int track = r.location;
            totalHeadMovement+= Math.abs(currentPosition - track);
            currentPosition = track;
            seekQ.add(r);
        }
        averageSeekTime = (double)totalHeadMovement / (double) requests;
        displayResults(start, totalHeadMovement, averageSeekTime, seekQ);
    }
    
    public static Request findMinRequest(int currentPosition, ArrayList<Request> requestQ){
        //the purpose of this functoin is to get the minimum difference of the current position with the remaining requests
        //then returns the request that is nearest to the head.
       int min = currentPosition;
       int minIndex = 0;
       Request minR = requestQ.get(minIndex);
       for(int i = 0; i < requestQ.size(); i++){
          
           Request r = requestQ.get(i);
           int difference = Math.abs(currentPosition - r.location);
           if(difference < min) {
               //System.out.println(difference);
               min = difference;
               minR = r;
               minIndex = i;
           }
       }
       requestQ.remove(minIndex);
       return minR;
    }
    
    public static void CSCAND(){
        Scanner input = new Scanner(System.in);
        //C-Scan Disk Scheduling
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "This is for C-Scan Disk Scheduling\n");
        //inputs: current position, track size, Requests (Max of 10), location of requests
        //outputs: Total Head Movement, Average Seek Time, Gant Chart
        System.out.print("Input track starting position: ");
        int start = Integer.parseInt(input.nextLine());
        System.out.print("Input track size: ");
        int size = Integer.parseInt(input.nextLine());
        System.out.print("Input number of requests (Maximum of 10): ");
        int requests = Integer.parseInt(input.nextLine());
        while (requests < 1 || requests > 10) {
            System.out.println("Invalid Input, try again.");
            System.out.print("Input number of requests (Maximum of 10): ");
            requests = Integer.parseInt(input.nextLine());
        }
        ArrayList<Request> requestQ = new ArrayList<>();
        ArrayList<Request> seekQ = new ArrayList<>();
        char name = 'A';
        
        //getting request track positions
        for(int i = 0; i < requests; i++) {
            System.out.printf("Track [%s]: ", String.valueOf(name));
            int requestPosition = Integer.parseInt(input.nextLine());
            while(requestPosition < 0 || requestPosition > size) {
                System.out.println("Invalid Input, try again.");
                System.out.printf("Track [%s]: ", String.valueOf(name));
                requestPosition = Integer.parseInt(input.nextLine());
            }
            requestQ.add(new Request(String.valueOf(name), requestPosition));
            name++;
        }
        
        int currentPosition = start;
        int totalHeadMovement = 0;
        double averageSeekTime = 0; 
        //sorts the requests by ascending position for better processing.
        Collections.sort(requestQ, new Ascending());
        ArrayList<Request> removeList = new ArrayList();
        //adds the requests that are less than the current position to the removeList then proceeds to add the ones greater to the seekQ list
        for (int i = 0; i < requests; i++) {
            
            Request r = requestQ.get(i);
            int track = r.location;
            if(start > r.location)
                removeList.add(r);
            else {
                    
                totalHeadMovement += Math.abs(currentPosition - track);
                currentPosition = track;
                seekQ.add(r);
            }
        }
        
        //since the C-Scan continues to the end of the disk, the max size and min size of the disk is added to the seek and head movement.
        seekQ.add(new Request("END OF DISK", size-1));
        totalHeadMovement += Math.abs(currentPosition - (size-1));
        currentPosition = size-1;
        seekQ.add(new Request("START OF DISK", 0));
        totalHeadMovement += Math.abs(currentPosition - 0);
        currentPosition = 0;

        //iterates through the remaining items on the list to complete the C scan
        for(int i = 0; i < removeList.size(); i++) {
            Request r = removeList.get(i);
            int track = r.location;
            totalHeadMovement += Math.abs(currentPosition - track);
            currentPosition = track;
            seekQ.add(r);
        }
  
        averageSeekTime = (double)totalHeadMovement / (double) requests;
        displayResults(start, totalHeadMovement, averageSeekTime, seekQ);
    }
    
    public static void CLOOKD(){
       Scanner input = new Scanner(System.in);
        //C-Look Disk Scheduling
        System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "This is for C-Look Disk Scheduling\n");
        //inputs: current position, track size, Requests (Max of 10), location of requests
        //outputs: Total Head Movement, Average Seek Time, Gant Chart
        System.out.print("Input track starting position: ");
        int start = Integer.parseInt(input.nextLine());
        System.out.print("Input track size: ");
        int size = Integer.parseInt(input.nextLine());
        System.out.print("Input number of requests (Maximum of 10): ");
        int requests = Integer.parseInt(input.nextLine());
        while (requests < 1 || requests > 10) {
            System.out.println("Invalid Input, try again.");
            System.out.print("Input number of requests (Maximum of 10): ");
            requests = Integer.parseInt(input.nextLine());
        }
        ArrayList<Request> requestQ = new ArrayList<>();
        ArrayList<Request> seekQ = new ArrayList<>();
        char name = 'A';
        
        //getting request track positions
        for(int i = 0; i < requests; i++) {
            System.out.printf("Track [%s]: ", String.valueOf(name));
            int requestPosition = Integer.parseInt(input.nextLine());
            while(requestPosition < 0 || requestPosition > size) {
                System.out.println("Invalid Input, try again.");
                System.out.printf("Track [%s]: ", String.valueOf(name));
                requestPosition = Integer.parseInt(input.nextLine());
            }
            requestQ.add(new Request(String.valueOf(name), requestPosition));
            name++;
        }
        
        int currentPosition = start;
        int totalHeadMovement = 0;
        double averageSeekTime = 0; 
        Collections.sort(requestQ, new Ascending());
        ArrayList<Request> removeList = new ArrayList();
        
         //adds the requests that are less than the current position to the removeList then proceeds to add the ones greater to the seekQ list
        for (int i = 0; i < requests; i++) {
            
            Request r = requestQ.get(i);
            int track = r.location;
            if(start > r.location)
                removeList.add(r);
            else {
                    
                totalHeadMovement += Math.abs(currentPosition - track);
                currentPosition = track;
                seekQ.add(r);
            }
        }
     
        for(int i = 0; i < removeList.size(); i++) {
            Request r = removeList.get(i);
            int track = r.location;
            totalHeadMovement += Math.abs(currentPosition - track);
            currentPosition = track;
            seekQ.add(r);
        }
  
        averageSeekTime = (double)totalHeadMovement / (double) requests;
        displayResults(start, totalHeadMovement, averageSeekTime, seekQ);
    }
    
    
    //the class that contains the name and location of the request.
    static class Request {
        String name;
        int location;
        public Request(String name, int location) {
            this.name = name;
            this.location = location;
        }
    }
    
    //the class that sorts the arraylist of request in ascending location/position.
    static class Ascending implements Comparator<Request> {
        public int compare(Request firstPlayer, Request secondPlayer) {
            return Integer.compare(firstPlayer.location, secondPlayer.location);
        }
    }
    
}
