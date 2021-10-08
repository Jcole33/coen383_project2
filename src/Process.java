public class Process {
    float arrivalTime;
    float endTime;
    int priority;
    float expectedRuntime;
    float timeLeft;
    Clock globalClock;

    Process(float arrivalTime, int priority, float expectedRuntime, Clock globalClock) {
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.expectedRuntime = expectedRuntime;
        this.timeLeft = expectedRuntime;
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
