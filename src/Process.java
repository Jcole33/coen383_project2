public class Process {
    int arrivalTime;
    int endTime;
    int priority;
    int expectedRuntime;
    int timeLeft;
    static int globalClock = 0;
    Process(int arrivalTime, int priority, int expectedRuntime) {
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.expectedRuntime = expectedRuntime;
        this.timeLeft = expectedRuntime;
    }
    boolean run(int time) {
        int runtime;
        if (time <= this.timeLeft) {
            this.timeLeft -= time;
            runtime = time;
        } else {
            runtime = this.timeLeft;
            this.timeLeft = 0;
        }
        if (this.timeLeft == 0) {
            this.endTime = Process.globalClock + runtime;
        }
        Process.globalClock += time;
        return this.timeLeft == 0;
    }

}
