package SRT;
import java.util.Random;

class Process
{
    int pid; // Process ID
    int burst_time; // Burst Time
    int arrival_time; // Arrival Time
    char name;
    int priority;
    static Random rand = new Random(4);
     
    public Process(int pid, char name)
    {
    
        this.pid = pid; 
	this.name = name;
	this.arrival_time = rand.nextInt(100); // will return numbetween 0 and 99
	this.burst_time = rand.nextInt(10) + 1; //will return num between 1 and 10
	this.priority = rand.nextInt(4) + 1; //will return num between 1 and 4 
    }
    public static void setSeed(int seed) {
        rand = new Random(seed);
    }
    
}
 
public class SRT
{
    static int complete = 0;
    static void WaitingTime(Process proc[], int n,
                                     int wt[])
    {
        int rt[] = new int[n];
      
        // Copy the burst time into rt[]
        for (int i = 0; i < n; i++)
            rt[i] = proc[i].burst_time;//remaining time
      
        int t = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;
      	int started_processes=0;
      	int res_total=0;
        int throughput=0;
        // Process until all processes gets completed
        while (t<100 || started_processes!=0) {
      
            // Find minimum burst time            
            for (int j = 0; j < n; j++)
            {
                if ((proc[j].arrival_time <= t) &&
                  (rt[j] < minm) && rt[j] > 0 && (t<100 || rt[j]!=proc[j].burst_time)) {
                  
                    minm = rt[j];
                    shortest = j;
                    check = true;
                    
                }
            }
      		
            if (check == false) {
                System.out.print("*");
                t++;
                continue;
            }
      
      	    if(rt[shortest]==proc[shortest].burst_time)
            	{
                    throughput++;
            		started_processes++;
            		res_total=res_total+(t - proc[shortest].arrival_time);
            	}
            // decrement rt
            rt[shortest]--;
            System.out.print(proc[shortest].name);
      
            // Update minimum
            minm = rt[shortest];
            if (minm == 0)
                minm = Integer.MAX_VALUE;
      
            // If a process gets completely executed
            if (rt[shortest] == 0) {
      
                // Increment complete
                complete++;
                check = false;
      		 started_processes--;
                // Find finish time of current process
                finish_time = t + 1;
      
                // Calculate waiting time
                wt[shortest] = finish_time -
                             proc[shortest].burst_time -
                             proc[shortest].arrival_time;
      
                if (wt[shortest] < 0)
                    wt[shortest] = 0;
            }
            // Increment time
            t++;            
        }
        System.out.println("\nthroughput="+ throughput);
        System.out.println("\nAverage response time="+ (float)res_total/(float)complete);
    }
      
    // Method to calculate turn around time
    static void TurnAroundTime(Process proc[], int n,
                            int wt[], int tat[])
    {
        // calculating turnaround time
        for (int i = 0; i < n; i++)
            tat[i] = proc[i].burst_time + wt[i];
    }
      
    // Method to calculate average time
    static void avgTime(Process proc[], int n)
    {
        int wt[] = new int[n], tat[] = new int[n];
        int  total_wt = 0, total_tat = 0;
      
        // Function to find waiting time
        WaitingTime(proc, n, wt);
      
        // Function to find turn around time
        TurnAroundTime(proc, n, wt, tat);
      
        System.out.println("\nProcesses " + "Arrival time"+
                           " Burst time " +
                           " Waiting time " +
                           " Turn around time");
      
        // Calculate total waiting time and total turnaround time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(" " + proc[i].name + "\t\t" + proc[i].arrival_time+"\t"
                             + proc[i].burst_time + "\t\t " + wt[i]
                             + "\t\t" + tat[i]);
        }
      
        System.out.println("Average waiting time = " +
                          (float)total_wt / (float)complete);
        System.out.println("Average turn around time = " +
                           (float)total_tat / (float)complete);
        
                               }
     
    // Driver Method
    public static void run(int seed)
    {
    Process.setSeed(seed);
        Process proc[] = { new Process(1, 'A'),
                            new Process(2, 'B'),
                            new Process(3, 'C'),
                            new Process(4, 'D'),
                            new Process(5,'E'),
                            new Process(6,'F'),
                            new Process(7,'G'),
                            new Process(8,'H'),
                            new Process(9,'I'),
                            new Process(10,'J'),
                            new Process(10,'K'),
                            new Process(10,'L'),
                            new Process(10,'M'),
                            new Process(10,'N'),
                            new Process(10,'O'),
                            new Process(10,'P'),
                            new Process(10,'Q'),
                            new Process(10,'R')
                            };
        /*Process proc[] = { new Process(1, 'A',(int)Math.floor(Math.random()*(s_max-s_min+1)+s_min), (int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                            new Process(2, 'B',(int)Math.floor(Math.random()*(s_max-s_min+1)+s_min), (int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                            new Process(3, 'C',(int)Math.floor(Math.random()*(s_max-s_min+1)+s_min), (int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                            new Process(4, 'D',(int)Math.floor(Math.random()*(s_max-s_min+1)+s_min), (int)Math.floor(Math.random()*(a_max-a_min+1)+a_min))};*/
        
         /*Process proc[] = { new Process(1,'A',(int)Math.floor(Math.random()*(s_max-s_min+1)+s_min),(int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                            new Process(2,'B', (int)Math.floor(Math.random()*(a_max-a_min+1)+a_min),(int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                            new Process(3,'C', (int)Math.floor(Math.random()*(a_max-a_min+1)+a_min),(int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                            new Process(4,'D',(int)Math.floor(Math.random()*(a_max-a_min+1)+a_min),(int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                            new Process(5,'E',(int)Math.floor(Math.random()*(a_max-a_min+1)+a_min),(int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                            new Process(6,'F',(int)Math.floor(Math.random()*(a_max-a_min+1)+a_min),(int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                            new Process(7,'G',(int)Math.floor(Math.random()*(a_max-a_min+1)+a_min),(int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                            new Process(8,'H',(int)Math.floor(Math.random()*(a_max-a_min+1)+a_min),(int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                            new Process(9,'I',(int)Math.floor(Math.random()*(a_max-a_min+1)+a_min),(int)Math.floor(Math.random()*(a_max-a_min+1)+a_min)),
                            new Process(10,'J',(int)Math.floor(Math.random()*(a_max-a_min+1)+a_min),(int)Math.floor(Math.random()*(a_max-a_min+1)+a_min))};*/
                           
         
         avgTime(proc, proc.length);
    }
}
/*
hivani@shivani-HP-Pavilion-Gaming-Laptop-15-dk0xxx:~$ java SRT
DDDAAAAAACCCCCCCBBBBBBBB
Processes Arrival time Burst time  Waiting time  Turn around time
 A		2	6		 1		7
 B		4	8		 12		20
 C		1	7		 8		15
 D		0	3		 0		3
Average waiting time = 5.25
Average turn around time = 11.25
*/
