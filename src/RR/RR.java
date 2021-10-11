package RR;
import main.Clock;
import main.Process;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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

            addArrivals();
            Process nextProcess = getNextProcess();
            if (nextProcess != null) {
                if (nextProcess.getExpectedRuntime() == nextProcess.getTimeLeft()) {
                    ++runningProcesses;
                }
                boolean finished = nextProcess.run(1);
                if (finished) {
                    --runningProcesses;
                    tallyProcess(nextProcess);
                }
                runString += nextProcess.getName();
            } else {
                runString += "*";
                globalClock.incrementTime(1);
            }
            if (globalClock.getTime() == endTime) {
                runString += "|";
            }
        }
        System.out.println(runString);
        printStats();
    }


    public Process getNextProcess(){
        if (currentQueue != null) {
            Process nextProcess = currentQueue.peek();

            //if process will be finished on the next run then remove it from the queue entirely, otherwise leave it for later runs
            if (nextProcess!= null ) {
                currentQueue.remove();
                if(nextProcess.getTimeLeft()!=1)
                    currentQueue.add(nextProcess);
            }
            return nextProcess;
        }
        return null;
    }

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
