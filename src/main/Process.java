package main;
import java.util.Random;

public class Process  implements Comparable<Process>  {
    float arrivalTime; //time process arrives into algorithm
    float endTime; //time process finishes running
    float firstTime; //first time process is run, used to calculate response time
    int priority; 
    float expectedRuntime; //full runtime a process needs to run, is not changed as process is run
    float timeLeft; //how much running time process has left to run, decreases as process is run
    float lastTouchTime; //the time the process was last "touched", "touched" means either process was given cpu time or was moved up in the priority tree by aging algorithm
    Clock globalClock;
    String name;
    static Random rand = new Random(4); //default seed for random values is 4


    public Process(String name, Clock globalClock, float arrivalTime, int priority, float expectedRuntime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.expectedRuntime = expectedRuntime;
        timeLeft = expectedRuntime; //initialize timeleft to full runtime
        lastTouchTime = arrivalTime; //initialize last touch time to arrival time
        this.globalClock = globalClock;
    }
    //this constructor will randomly generate the values for you using the set random seed
    public Process(String name, Clock globalClock) {
        arrivalTime = (float) rand.nextInt(100); // will return numbetween 0 and 99
        expectedRuntime = (float) rand.nextInt(10) + 1; //will return num between 1 and 10
        priority = rand.nextInt(4) + 1;  //will return num between 1 and 4
        timeLeft = expectedRuntime; //initialize timeleft to full runtime
        lastTouchTime = arrivalTime; //initialize last touch time to arrival time
        this.globalClock = globalClock;
        this.name = name;
    }
    
    //set the seed for the process class' random generation
    public static void setSeed(int num) {
        rand = new Random(num);
    }

    public boolean run(float time) {
        //mark that this is the most recent time the process has been touched
        touch();
        //if timeleft == expectedRuntime then the process has never been run before, this is the first time run
        if (timeLeft == expectedRuntime) {
            firstTime = globalClock.getTime();
        }
        //if process is given less cpu time than it needs then just subtract off timeleft and nothing else
        if (time < timeLeft) {
            timeLeft -= time;
        } else {
            endTime = globalClock.getTime() + timeLeft; //if process is given enough or more than enough time to finish then mark their endtime as the time when they actually finished, not the alloted time
            timeLeft = 0; //mark process as done
        }
        globalClock.incrementTime(time); //increment clock as time goes by
        return timeLeft == 0; // if process is done then return true
    }
    public String toString() {
        return "name: " + name + " arrivalTime: " + arrivalTime + " priority: " + priority + " expectedRuntime: " + expectedRuntime;
    }
    public float getArrivalTime() {
        return arrivalTime;
    }
    public int getPriority() {
        return priority;
    }
    public float getTimeLeft() {
        return timeLeft;
    }
    public float getExpectedRuntime() {
        return expectedRuntime;
    }
    public float getEndTime() {
        return endTime;
    }
    public String getName() {
        return name;
    }
    public float getFirstTime() {
        return firstTime;
    }
    public float getLastTouchTime() {
        return lastTouchTime;
    }
    //mark the updated time the process was last touched to now, used in aging algorithms 
    public void touch() {
        lastTouchTime = globalClock.getTime();
    }

    //make the process class sort by arrival time, useful in sorting arrival list when list of processes are generated
    @Override
    public int compareTo(Process otherProcess) {
        if (getArrivalTime() > otherProcess.getArrivalTime()) {
            return 1;
        } else if (getArrivalTime() == otherProcess.getArrivalTime()) {
            return 0;
        } else {
            return -1;
        }
    }
}