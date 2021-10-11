package SJF;
import java.util.*;
 
class Process{
    Integer id;                 // Process ID
    String name;                // Process name
    Integer response;
    Integer completion_time;    //Time when process completely executes (According to System Clock)
    Integer arrival_time;       //Time when a process arrives (According to System Clock)
    Integer burst_time;         //Time during which a process holds the CPU
    Integer tat;                //Turn-around time
    Integer wt;                 //Waiting time (Process has arrived, but not executing)
    Integer priority;
    Integer f = 0;              //Flag to indicate whether process has finished execution
    static Random rand = new Random(4);
    
    public Process(int pid, String name) //Constructor
    { 
        this.id = pid; 
        this.name = name;
        this.arrival_time = rand.nextInt(100); // will return numbetween 0 and 99
        this.burst_time = rand.nextInt(10) + 1; //will return num between 1 and 10
        this.priority = rand.nextInt(4) + 1;  //will return num between 1 and 4
    } 
    public static void setSeed(int seed) {
        rand = new Random(seed);
    }

};

public class SJF {
    
    public static void Schedule(int seed){
        Process.setSeed(seed);
        Process proc[] = { 
            new Process(1, "A"),  
            new Process(2,"B" ), 
            new Process(3,"C" ),  
            new Process(4,"D" ),
            new Process(5,"E" ),
            new Process(6,"F" ),
            new Process(7,"G" ),
            new Process(8,"H" ),
            new Process(9,"I" ),
            new Process(10,"J" ),
            new Process(11,"K" ),
            new Process(12,"L" ),
            new Process(13,"M"),
            new Process(14,"N"),
            new Process(15,"O"),
            new Process(16,"P"),
            new Process(17,"Q"),
            new Process(18,"R") 
        }; 
        int system_clock = 0, 
            executed_processes = 0;

		float avg_wt=0, avg_ta=0, avg_response=0;
        
        int n = proc.length;
	
		while(true)
		{
			int c = n,                //Currently executing process array index
                min = 999;           //Setting min to higher number, to compare first minimum burst_time
            
			if (system_clock > 99)        //if the next process would start after time 99 then we can end the simulation
				break;                  
			
			for (int i = 0; i < n; i++)
			{
				/*
                    If the process arrives before or during the current system clock counter, and it's burst is less than current minimum, then c is set to index
				 */ 
				if ((proc[i].arrival_time <= system_clock) && (proc[i].f == 0) && (proc[i].burst_time < min))
				{
					min = proc[i].burst_time;
					c = i;
				}
			}
			
			/*Keep on increasing system clock to look for next processes, since c can't be update further of n */
			if (c == n) 
				system_clock++;
			else
			{
				proc[c].completion_time =    system_clock  +   proc[c].burst_time;  
                proc[c].response = system_clock - proc[c].arrival_time;
				system_clock += proc[c].burst_time;                                 //Increment system clock after process execution
				proc[c].tat  =   proc[c].completion_time   -   proc[c].arrival_time; 
				proc[c].wt =   proc[c].tat  -   proc[c].burst_time; 
				proc[c].f   =   1;                                                  //Set flag to 1, since process has been executed
				executed_processes++;                                                  //increment executed processses
			}
		}


       //ArrayList<String> resultarr = Collections.nCopies(100, "_").(new ArrayList<String>());   
       List<String> resultarr = new ArrayList<String>(Collections.nCopies(200 , "_"));

       // String[] resultarr = Collections.nCopies(100, "_").toArray(new String[0]);      //Array to indicate execution timeline, of size 100

        for(int i = 0; i < n; i++){
            if (proc[i].f == 1) { //check if process was actually run
                for(int k = proc[i].completion_time - proc[i].burst_time; k < proc[i].completion_time; k++){        //Iterate over each process, and store corresponding name in timeline
                    resultarr.set(k, proc[i].name);
                }
            }
            
        }
        
        for(int i = 0; i < resultarr.size(); i++){
            System.out.print(resultarr.get(i));
        }
		
		//Average Waiting and Turn-around times
		for(int i = 0; i < n; i++)
		{
            if (proc[i].f == 1) { //check if process was actually run
                avg_wt += proc[i].wt;
                avg_ta += proc[i].tat;
                avg_response += proc[i].response;
            }
		}
		System.out.println ("\nAverage TurnAround time is: "+ (float)(avg_ta/n));
		System.out.println ("Average Waiting is: "+ (float)(avg_wt/n));
        System.out.println("Average Response Time: " + avg_response);
        System.out.println("Throughput: " + executed_processes);
    }
}