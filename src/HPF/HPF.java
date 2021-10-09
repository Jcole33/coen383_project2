package HPF;

import main.Clock;
import main.Process;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public abstract class HPF {
    Clock globalClock = new Clock();
    List<Queue<Process>> queueList = new ArrayList<Queue<Process>>();
    Queue<Process> arrivalList;
    String runString = "";
    int finishedProcessCount = 0;
    float turnAroundTotal = 0;
    float waitTotal = 0;
    float responseTotal = 0;

    public HPF(int num) {
        Process.setSeed(num);
        //initialize priority level queues
        for (int i = 0; i < 4; ++i) {
            queueList.add(new LinkedList<Process>());
        }
        List<Process> processList = new ArrayList<Process>();
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
        processList.add(new Process("S", globalClock));
        processList.add(new Process("T", globalClock));
        processList.add(new Process("U", globalClock));
        processList.add(new Process("V", globalClock));
        processList.add(new Process("W", globalClock));
        processList.add(new Process("X", globalClock));
        processList.add(new Process("Y", globalClock));
        processList.add(new Process("Z", globalClock));
        processList.add(new Process("1", globalClock));
        processList.add(new Process("2", globalClock));
        processList.add(new Process("3", globalClock));
        processList.add(new Process("4", globalClock));
        processList.add(new Process("5", globalClock));
        processList.add(new Process("6", globalClock));
        processList.add(new Process("7", globalClock));
        processList.add(new Process("8", globalClock));
        processList.add(new Process("9", globalClock));



        //sorts processes by arrival time
        Collections.sort(processList);
        arrivalList = new LinkedList<Process>(processList);
        /*System.out.println("Processes:");
        for (int i = 0; i < arrivalList.size(); ++i) {
            System.out.println(processList.get(i));
        }*/

    }

    public abstract void run(float endTime);

    public float getTime() {
        return globalClock.getTime();
    }
    public Queue<Process> getNextQueue() {
        for (int i = 0; i < 4; ++i) {
            Queue<Process> currentQueue = queueList.get(i);
            if (!currentQueue.isEmpty()) {
                return currentQueue;
            }
        }
        return null;
    }
    
    public void addArrivals() {
        while ( !arrivalList.isEmpty() && arrivalList.peek().getArrivalTime() <= getTime() ) {
            Process arrivingProcess = arrivalList.remove();
            queueList.get(arrivingProcess.getPriority() - 1).add(arrivingProcess);
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
}
