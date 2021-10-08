public class Process extends Clock {
    float arrivalTime;
    float endTime;
    int priority;
    float expectedRuntime;
    float timeLeft;
    Process(float arrivalTime, int priority, float expectedRuntime) {
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.expectedRuntime = expectedRuntime;
        this.timeLeft = expectedRuntime;
    }
    boolean run(float time) {
        float runtime;
        if (time <= this.timeLeft) {
            this.timeLeft -= time;
            runtime = time;
        } else {
            runtime = this.timeLeft;
            this.timeLeft = 0;
        }
        if (this.timeLeft == 0) {
            this.endTime = globalClock + runtime;
        }
        globalClock += time;
        return this.timeLeft == 0;
    }

}
