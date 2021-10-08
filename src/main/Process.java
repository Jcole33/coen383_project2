package main;
import java.util.Random;

public class Process {
    float arrivalTime;
    float endTime;
    int priority;
    float expectedRuntime;
    float timeLeft;
    Clock globalClock;

    public Process(Clock globalClock, float arrivalTime, int priority, float expectedRuntime) {
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.expectedRuntime = expectedRuntime;
        this.timeLeft = expectedRuntime;
        this.globalClock = globalClock;
    }
    public Process(Clock globalClock) {
        Random rand = new Random(0);
        arrivalTime = (float) rand.nextInt(100); // will return numbetween 0 and 99
        expectedRuntime = (float) rand.nextInt(10) + 1; //will return num between 1 and 10
        priority = rand.nextInt(4) + 1;  //will return num between 1 and 4
        timeLeft = expectedRuntime;
        this.globalClock = globalClock;
    }
    
    public boolean run(float time) {
        if (time < timeLeft) {
            timeLeft -= time;
        } else {
            endTime = globalClock.time + timeLeft;
            timeLeft = 0;
        }
        globalClock.time += time;
        return timeLeft == 0;
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

}
