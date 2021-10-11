package RR;
import main.Clock;
import main.Process;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
//main class that runs the Round Robin algorithm, in this case, we use a slice of time quanta as 1
public class RR {
    Clock globalClock = new Clock();
    Queue<Process> arrivalList;
    Queue<Process> currentQueue = new LinkedList<Process>();
    String runString = "";
    int finishedProcessCount = 0;
    float turnAroundTotal = 0;
    float waitTotal = 0;
    float responseTotal = 0;
    int runningProcesses = 0;
    float endTime;
    List<Process> processList = new ArrayList<Process>();

    public RR(int seed){
        Process.setSeed(seed);
        //initialize priority level queues

        //create new proceses
        processList.add(new Process("A", globalClock));
        processList.add(new Process("B", globalClock));
        processList.add(new Process("C", globalClock));
        processList.add(new Process("D", globalClock));
        processList.add(new Process("E", globalClock));
        processList.add(new Process("F", globalClock));
        processList.add(new Process("G", globalClock));
        processList.add(new Process("H", globalClock));
        processList.add(new Process("I", globalClock));
        processList.add(new Process("J", globalClock));
        processList.add(new Process("K", globalClock));
        processList.add(new Process("L", globalClock));
        processList.add(new Process("M", globalClock));
        processList.add(new Process("N", globalClock));
        processList.add(new Process("O", globalClock));
        processList.add(new Process("P", globalClock));
        processList.add(new Process("Q", globalClock));
        processList.add(new Process("R", globalClock));

        Collections.sort(processList);
        arrivalList = new LinkedList<Process>(processList);

    }


    public void run(float endTime) {
        this.endTime = endTime;
        while (checkRunCondition()) {
            //preparing arrival queues and determine the next process to run
            addArrivals();
            Process nextProcess = getNextProcess();
            //if there is a process to run
            //check if process has never been run before, if so, add it to the total of running processes
            if (nextProcess != null) {
                if (nextProcess.getExpectedRuntime() == nextProcess.getTimeLeft()) {
                    ++runningProcesses;
                }
                //run the process and return if the process is finished
                boolean finished = nextProcess.run(1);
                if (finished) {
                    //if finished then decrease its running count and update the statistics on total runtime
                    --runningProcesses;
                    tallyProcess(nextProcess);
                }
                runString += nextProcess.getName();
            } else {
                //use asterisk to represent idle time and move to next time for process
                runString += "*";
                globalClock.incrementTime(1);
            }
            //similarly use | to seperate time 100 and the process that keeps running after since they are already in run
            if (globalClock.getTime() == endTime) {
                runString += "|";
            }
        }
        System.out.println(runString);
        printStats();
    }

    //
    public Process getNextProcess(){
        if (currentQueue != null) {
            //check out current available queue for the very next process
            Process nextProcess = currentQueue.peek();

            //if process will be finished on the next run then remove it from the queue entirely for Round Robin purpose
            if (nextProcess!= null ) {
                currentQueue.remove();
                //if the process is not finished, then add it to the back of the current queue
                if(nextProcess.getTimeLeft()!=1)
                    currentQueue.add(nextProcess);
            }
            return nextProcess;
        }
        return null;
    }

    //keep running if time is less than 100 quanta or there are still partially completed processes
    public boolean checkRunCondition() {
        return getTime() < endTime || runningProcesses > 0;
    }

    public float getTime() {
        return globalClock.getTime();
    }



    public void addArrivals() {
        while ( !arrivalList.isEmpty() && arrivalList.peek().getArrivalTime() <= getTime() ) {
            Process arrivingProcess = arrivalList.remove();
            currentQueue.add(arrivingProcess);
        }
    }

    //once a process has finished its statistics are added to the running total
    public void tallyProcess(Process finishedProcess) {
        ++finishedProcessCount;
        responseTotal += finishedProcess.getFirstTime() - finishedProcess.getArrivalTime();
        waitTotal += finishedProcess.getEndTime() - finishedProcess.getArrivalTime() - finishedProcess.getExpectedRuntime();
        turnAroundTotal += finishedProcess.getEndTime() - finishedProcess.getArrivalTime();
    }

    public float getAvgResponse() {
        return responseTotal / finishedProcessCount;
    }
    public float getAvgWait() {
        return waitTotal / finishedProcessCount;
    }
    public float getAvgTurnAround() {
        return turnAroundTotal / finishedProcessCount;
    }
    public int getThroughPut() {
        return finishedProcessCount;
    }
    public void printStats() {
        System.out.println("Average Response Time: " + getAvgResponse() + " Average Wait Time: " + getAvgWait() + " Average Turn Around Time: " + getAvgTurnAround() + " Throughput: " + getThroughPut());
    }
    public void printProcesses() {
        for (int i = 0; i < arrivalList.size(); ++i) {
            System.out.println(processList.get(i));
        }
    }
}
