package HPF;

import main.Process;

import java.util.Queue;

public class HPFNonPrempt extends HPF {
    
    public HPFNonPrempt(int seed) {
        super(seed);
    }

    public void run(float endTime) {
        while (getTime() < endTime) {
            addArrivals();
            Process nextProcess = getNextProcess();
            if (nextProcess != null) {
                boolean finished = nextProcess.run(nextProcess.getExpectedRuntime());
                if (finished) {
                    tallyProcess(nextProcess);
                }
                for (int i = 0; i < nextProcess.getExpectedRuntime(); ++i) {
                    runString += nextProcess.getName();
                }
            } else {
                runString += "*";
                globalClock.incrementTime(1);
            }
            
        }
        System.out.println(runString);
    }

    public Process getNextProcess() {
        Queue<Process> currentQueue = getNextQueue();
        if (currentQueue != null) {
            return currentQueue.remove();
        }
        return null;
    }
 

}
