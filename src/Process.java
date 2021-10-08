import java.util.Random;

public class Process {
    float arrivalTime;
    float endTime;
    int priority;
    float expectedRuntime;
    float timeLeft;
    Clock globalClock;

    Process(Clock globalClock, float arrivalTime, int priority, float expectedRuntime) {
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.expectedRuntime = expectedRuntime;
        this.timeLeft = expectedRuntime;
        this.globalClock = globalClock;
    }
    Process(Clock globalClock) {
        Random rand = new Random(0);
        this.arrivalTime = (float) rand.nextInt(100); // will return numbetween 0 and 99
        this.expectedRuntime = (float) rand.nextInt(10) + 1; //will return num between 1 and 10
        this.priority = rand.nextInt(4) + 1;  //will return num between 1 and 4
        this.timeLeft = this.expectedRuntime;
        this.globalClock = globalClock;
    }
    
    boolean run(float time) {
        if (time < this.timeLeft) {
            this.timeLeft -= time;
        } else {
            this.endTime = this.globalClock.time + this.timeLeft;
            this.timeLeft = 0;
        }
        this.globalClock.time += time;
        return this.timeLeft == 0;
    }

}
