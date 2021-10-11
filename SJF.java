import java.util.*;
 
class Process{
    Integer id;                 // Process ID
    String name;                // Process name
    Integer completion_time;    //Time when process completely executes (According to System Clock)
    Integer arrival_time;       //Time when a process arrives (According to System Clock)
    Integer burst_time;         //Time during which a process holds the CPU
    Integer tat;                //Turn-around time
    Integer wt;                 //Waiting time (Process has arrived, but not executing)
    Integer f = 0;              //Flag to indicate whether process has finished execution
    
    public Process(int pid, String name, int bt, int at) //Constructor
    { 
        this.id = pid; 
        this.name = name;
        this.burst_time = bt; 
        this.arrival_time = at;
    } 

};

public class SJF {

    public static void Schedule(Process proc[]){
        int system_clock = 0, 
            executed_processes = 0;

		float avg_wt=0, avg_ta=0;
        
        int n = proc.length;
	
		while(true)
		{
			int c = n,                //Currently executing process array index
                min = 999;           //Setting min to higher number, to compare first minimum burst_time
            
			if (executed_processes == n)        //All processes in the queue are executed
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
            for(int k = proc[i].completion_time - proc[i].burst_time; k < proc[i].completion_time; k++){        //Iterate over each process, and store corresponding name in timeline
                resultarr.set(k, proc[i].name);
            }
        }
        
        for(int i = 0; i < resultarr.size(); i++){
            System.out.print(resultarr.get(i) + " ");
        }
		
		//Average Waiting and Turn-around times
		for(int i = 0; i < n; i++)
		{
			avg_wt += proc[i].wt;
			avg_ta += proc[i].tat;
			
		}
		System.out.println ("\nAverage TurnAround time is: "+ (float)(avg_ta/n));
		System.out.println ("Average Waiting is: "+ (float)(avg_wt/n));
    }

	public static void main(String args[])
	{   
        // Process proc[] = { 
        //     new Process(1, "A", 6, 5),  
        //     new Process(2, "B" , 2, 5), 
        //     new Process(3,"C" , 8, 27),  
        //     new Process(4,"D" , 3, 0),
        //     new Process(5, "E" , 4, 20)
        
        // };
        int a_min = 0;      //Minimum arrival time
        int a_max = 99;     //Maximum Arrival time
        int s_min = 1;      //Minimum service time
        int s_max = 9;      //Maximum service time
        

            //GENERATE PROCESSESS WITH RANDOM ARRIVAL AND SERVICE TIMES
               Process proc[] = { new Process(1, "A", (int)Math.floor(Math.random()*(s_max-s_min+1)+s_min), (int)Math.floor(Math.random()*(a_max-a_min+1)+a_min) ),  
                          new Process(2,"B" ,(int)Math.floor(Math.random()*(s_max-s_min+1)+s_min), (int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)), 
                          new Process(3,"C" ,(int)Math.floor(Math.random()*(s_max-s_min+1)+s_min), (int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),  
                          new Process(4,"D" ,(int)Math.floor(Math.random()*(s_max-s_min+1)+s_min), (int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                          new Process(5,"E" ,(int)Math.floor(Math.random()*(s_max-s_min+1)+s_min), (int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                          new Process(6,"F" ,(int)Math.floor(Math.random()*(s_max-s_min+1)+s_min), (int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                          new Process(7,"G" ,(int)Math.floor(Math.random()*(s_max-s_min+1)+s_min), (int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                          new Process(8,"H" ,(int)Math.floor(Math.random()*(s_max-s_min+1)+s_min), (int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                          new Process(9,"I" ,(int)Math.floor(Math.random()*(s_max-s_min+1)+s_min), (int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                          new Process(10,"J" ,(int)Math.floor(Math.random()*(s_max-s_min+1)+s_min), (int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                          new Process(11,"K" ,(int)Math.floor(Math.random()*(s_max-s_min+1)+s_min), (int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                          new Process(12,"L" ,(int)Math.floor(Math.random()*(s_max-s_min+1)+s_min), (int)Math.floor(Math.random()*(a_max-a_min+1)+a_min))
                                                  
                           }; 
		
        Schedule(proc);
		
		
	}
}