package HPF;

import main.Process;

import java.util.Queue;

public class HPFNonPrempt extends HPF {
    
    public void run(float endTime) {
        while (getTime() < endTime) {
            addArrivals();
            Process nextProcess = getNextProcess();
            if (nextProcess != null) {
                nextProcess.run(nextProcess.getExpectedRuntime());
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
