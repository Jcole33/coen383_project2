package FCFS;
import java.util.*;

public class FCFS {
    public static void run(int seed) {
        int n = 18;
        String name[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R"};
        int p[] = new int[n]; //priority
        int pid[] = new int[n]; // process id
        int arr[] = new int[n]; // arrival time
        int bt[] = new int[n]; // burst time
        int compt[] = new int[n]; // completion time
        int tat[] = new int[n]; // turn around time
        int wt[] = new int[n]; // waiting time
        int rt[] = new int[n]; // response time
        float throughput = 0;
        int temp;
        float avgwt = 0, avgta = 0, avgrt = 0;
        String runString = ""; //execution order string

       
        Random rand = new Random(seed);
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(100); // will return numbetween 0 and 99
            bt[i] = rand.nextInt(10) + 1; //will return num between 1 and 10
            p[i] = rand.nextInt(4) + 1;  //will return num between 1 and 4
            pid[i] = i + 1;
        }


        // Sorting according to arrival time
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - (i + 1); j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;
                    temp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = temp;
                    String nameTemp = name[j];
                    name[j] = name[j + 1];
                    name[j + 1] = nameTemp;
                }
            }
        }
        // finding completion times
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                for (int ii = 0; ii < arr[i]; ++ii) {
                    runString += "*";
                }
                compt[i] = arr[i] + bt[i];
            } else {
                if (arr[i] > compt[i - 1]) {
                    for (int ii = 0; ii < arr[i] - compt[i-1]; ++ii) {
                        runString += "*";
                    }
                    compt[i] = arr[i] + bt[i];
                } else {
                    compt[i] = compt[i - 1] + bt[i];
                }
            }
            for (int ii = 0; ii < bt[i]; ++ii) {
                runString += name[i];
            }
            
            if (i == 0) {
                rt[0] = 0;
            } else {
                rt[i] = Math.abs((tat[i - 1] + arr[i - 1]) - arr[i]);
            }
            tat[i] = compt[i] - arr[i]; // Calculating Turn around Time which is Completion Time- Arrival time
            wt[i] = tat[i] - bt[i]; // Calculating Waiting Time which is Turn around Time- Burst Time
            avgwt += wt[i]; // Calculate Total Waiting Time
            avgta += tat[i]; // Calculate Total Turn around Time
            avgrt += rt[i];
            ++throughput;
            if (compt[i] >= 99) break; //stop the simulation after last job completes either at end of time or right after it
        }
        System.out.println("\nname pid  arrival  burst  complete turn waiting response");
        for (int i = 0; i < n; i++) {
            System.out.println(name[i] + "  \t" + pid[i] + "  \t " + arr[i] + "\t" + bt[i] + "\t" + compt[i] + "\t" + tat[i] + "\t" + wt[i]
                    + "\t" + rt[i]);
        }
        System.out.println("\naverage response time: " + (avgrt / throughput)); // Print average waiting time
        System.out.println("\naverage waiting time: " + (avgwt / throughput)); // Print average waiting time
        System.out.println("average turnaround time:" + (avgta / throughput)); // Print average Turn around time
        System.out.println("Throughput: " + throughput); //print out throughput
        System.out.println(runString);
    }
}
