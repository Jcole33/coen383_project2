package main;
import java.util.Random;

public class Process  implements Comparable<Process>  {
    float arrivalTime;
    float endTime;
    float firstTime;
    int priority;
    float expectedRuntime;
    float timeLeft;
    Clock globalClock;
    String name;
    static Random rand = new Random(0);


    public Process(String name, Clock globalClock, float arrivalTime, int priority, float expectedRuntime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.expectedRuntime = expectedRuntime;
        this.timeLeft = expectedRuntime;
        this.globalClock = globalClock;
    }
    public Process(String name, Clock globalClock) {
        arrivalTime = (float) rand.nextInt(100); // will return numbetween 0 and 99
        expectedRuntime = (float) rand.nextInt(10) + 1; //will return num between 1 and 10
        priority = rand.nextInt(4) + 1;  //will return num between 1 and 4
        timeLeft = expectedRuntime;
        this.globalClock = globalClock;
        this.name = name;
    }
    
    public boolean run(float time) {
        if (timeLeft == expectedRuntime) {
            firstTime = globalClock.getTime();
        }
        if (time < timeLeft) {
            timeLeft -= time;
        } else {
            endTime = globalClock.getTime() + timeLeft;
            timeLeft = 0;
        }
        globalClock.incrementTime(time);
        return timeLeft == 0;
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
    public static void setSeed(int num) {
        rand = new Random(num);
    }

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