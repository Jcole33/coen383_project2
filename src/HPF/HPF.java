package HPF;

import main.Clock;
import main.Process;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

//main class that runs the HPF algorithm, prempt and nonprempt HPF are children of this
public abstract class HPF {
    Clock globalClock = new Clock(); //keeps track of how many quanta have been run
    List<Queue<Process>> queueList = new ArrayList<Queue<Process>>(); //list of priority queues
    Queue<Process> arrivalList; //process list sorted by arrival time, used to mimic incoming processes
    String runString = "";
    int[] finishedProcessCount = {0, 0, 0, 0};
    List<Float> turnAroundTotal = new ArrayList<Float>();
    List<Float> waitTotal = new ArrayList<Float>();
    List<Float> responseTotal = new ArrayList<Float>();
    int runningProcesses = 0;
    float endTime; //how many quanta do you want to run this test for?
    boolean aging; //decides if this algorithm uses aging
    List<Process> processList = new ArrayList<Process>();

    public HPF(int seed, boolean aging) {
        this.aging = aging; //does this algorithm use aging?
        Process.setSeed(seed); //sets seed for random function used to reliably create random processes
        //initialize priority level queues
        for (int i = 0; i < 4; ++i) {
            //initialize statistic arrays
            turnAroundTotal.add(0.0f);
            waitTotal.add(0.0f);
            responseTotal.add(0.0f);
            queueList.add(new LinkedList<Process>());
        }
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

        //sorts processes by arrival time
        Collections.sort(processList);
        arrivalList = new LinkedList<Process>(processList);

    }

    public void run(float endTime) {
        this.endTime = endTime;
        while (checkRunCondition()) {
            //if the HPF ages its processes up in priorities then run aging algorithm
            if (aging) {
                checkForAge();
            }
            //add arriving processes to their proper priority queue
            addArrivals();
            //select the next process to run
            Process nextProcess = getNextProcess();
            if (nextProcess != null) {
                //if there is a process to run
                //check if process has never been run before, if so, add it to the total of running processes
                if (nextProcess.getExpectedRuntime() == nextProcess.getTimeLeft()) {
                    ++runningProcesses;
                }
                //run the process and return if the process is finished
                boolean finished = nextProcess.run(1);
                if (finished) {
                    //if process finished then decrement number of running processes and add its stats to total tally
                    --runningProcesses;
                    tallyProcess(nextProcess);
                }
                //add ran process to the run sequence that will be output at the end
                runString += nextProcess.getName();
            } else {
                //if no process run then put a * and move to the next quanta
                runString += "*";
                globalClock.incrementTime(1);
            }
            //mark the end of the last quanta for NEW processes with a |, processes previously run will start finishing up now
            if (globalClock.getTime() == endTime) {
                runString += "|";
            }
        }
        System.out.println(runString);
        printStats();
    }

    //implemented in nonprempt and prempt HPF subclasses
    public abstract Process getNextProcess();

    //keep running if time is less than 100 quanta or there are still partially completed processes
    public boolean checkRunCondition() {
        return getTime() < endTime || runningProcesses > 0;
    }

    public float getTime() {
        return globalClock.getTime();
    }
    //get the queue whose head shall be the next process to run
    public Queue<Process> getNextQueue() {
        for (int i = 0; i < 4; ++i) {
            Queue<Process> currentQueue = queueList.get(i);
            //get rid of any processes that have 0 runtime, this should be unnecessary, but is left just in case
            while (!currentQueue.isEmpty() && currentQueue.peek().getTimeLeft() < 1) {
                currentQueue.remove();
            }
            if (!currentQueue.isEmpty()) {
                //if current queue isnt empty and we havent hit endtime yet then return it since its head is the oldest, highest priority process
                if (getTime() < endTime) {
                    return currentQueue;
                } else {
                    //once endtime is hit, start only grabbing processes that have been run before
                    if (currentQueue.peek().getExpectedRuntime() != currentQueue.peek().getTimeLeft()) {
                        return currentQueue;
                    }
                }
            }
        }
        return null;
    }
    
    //implements aging algorithm
    void checkForAge() {
        //i > 0 since 0 cannot be promoted from
        for (int i = queueList.size() - 1; i > 0; --i) {
            //get queue that is being checked
            Queue<Process> currentQueue = queueList.get(i);
            //get queue that will be promoted to
            Queue<Process> nextQueue = queueList.get(i - 1);
            //if a process has been waiting for 5 quanta or longer then go ahead and promote it, 
            //since the process at the head of a queue is the oldest process in that queue we know that we have found all the processes ready for promotion if the head of the queue is either empty or too young to be promoted
            while (!currentQueue.isEmpty() && getTime() - currentQueue.peek().getLastTouchTime() >= 5) {
                Process promotedProcess = currentQueue.remove();
                //once a process has been promoted then reset its age timer so we know how long it has been waiting in a given queue
                promotedProcess.touch();
                //promote the process up a priority level
                nextQueue.add(promotedProcess);
            }
        }
    }

    //add arrivals to the priority queues
    public void addArrivals() {
        //if new arrival should have arrived then add it to the priority queue
        while ( !arrivalList.isEmpty() && arrivalList.peek().getArrivalTime() <= getTime() ) {
            Process arrivingProcess = arrivalList.remove();
            //add to proper priority queue based on its priority (-1 because priority is has a 1 start and list of queues has a 0 start)
            queueList.get(arrivingProcess.getPriority() - 1).add(arrivingProcess);
        }
    }

    //once a process has finished its statistics are added to the running total
    public void tallyProcess(Process finishedProcess) {
        int queueNum = finishedProcess.getPriority() - 1; // -1 because priorities are 1-4 and queues are 0-3
        ++finishedProcessCount[queueNum]; //used for throughput
        responseTotal.set(queueNum, responseTotal.get(queueNum)  + finishedProcess.getFirstTime() - finishedProcess.getArrivalTime()); //will be divided by throughput later to get average
        waitTotal.set(queueNum, waitTotal.get(queueNum) +  finishedProcess.getEndTime() - finishedProcess.getArrivalTime() - finishedProcess.getExpectedRuntime()); //will be divided by throughput later to get average
        turnAroundTotal.set(queueNum, turnAroundTotal.get(queueNum) + finishedProcess.getEndTime() - finishedProcess.getArrivalTime()); //will be divided by thorughput later to get average
    }
    Float getTotal(List<Float> queue) {
        Float total = 0f;
        for (int i = 0; i < 4; ++i) {
            total += queue.get(i);
        }
        return total;
    }
    //get avg response time for a single queue
    public float getAvgResponse(int queue) {
        if (finishedProcessCount[queue] == 0) return 0; //just in case no proceesses were finished on queue
        return responseTotal.get(queue) / finishedProcessCount[queue];
    }
    //get avg response time for all queues
    public float getTotalAvgResponse() {
        return getTotal(responseTotal) / getTotalThroughPut();
    }
    //get average wait time for a single queue
    public float getAvgWait(int queue) {
        if (finishedProcessCount[queue] == 0) return 0; //just in case no proceesses were finished on queue
        return waitTotal.get(queue) / finishedProcessCount[queue];
    }
    //get average wait time for all queues
    public float getTotalAvgWait() {
        return getTotal(waitTotal) / getTotalThroughPut();
    }
    //get average turn around time for a single queue
    public float getAvgTurnAround(int queue) {
        if (finishedProcessCount[queue] == 0) return 0; //just in case no proceesses were finished on queue
        return turnAroundTotal.get(queue) / finishedProcessCount[queue];
    }
    //get average turn around time for all queues
    public float getTotalAvgTurnAround() {
        return getTotal(turnAroundTotal) / getTotalThroughPut();
    }
    //get throughput for a single queue
    public int getThroughPut(int queue) {
        return finishedProcessCount[queue];
    }
    //get throughput for all queues
    public int getTotalThroughPut() {
        int total = 0;
        for (int i = 0; i < 4; ++i) {
            total += finishedProcessCount[i];
        }
        return total;
    }
    public void printStats() {
        for (int queue = 0; queue < 4; ++queue) {
            System.out.println("Priority: " + (queue + 1) + " Average Response Time: " + getAvgResponse(queue) + " Average Wait Time: " + getAvgWait(queue) + " Average Turn Around Time: " + getAvgTurnAround(queue) + " Throughput: " + getThroughPut(queue));
        }
        System.out.println("Total Average Response Time: " + getTotalAvgResponse() + " Total Average Wait Time: " + getTotalAvgWait() + " Total Average Turn Around Time: " + getTotalAvgTurnAround() + " Total Throughput: " + getTotalThroughPut());
    }
    public void printProcesses() {
        for (int i = 0; i < arrivalList.size(); ++i) {
            System.out.println(processList.get(i));
        }
    }
}
