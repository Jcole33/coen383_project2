package HPF;

import main.Process;

import java.util.Queue;


public class HPFPrempt extends HPF {
    
    public void run(float endTime) {
        while (getTime() < endTime) {
            addArrivals();
            Process nextProcess = getNextProcess();
            if (nextProcess != null) {
                nextProcess.run(1);
                runString += nextProcess.getName();
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
            Process nextProcess = currentQueue.peek();
            if (nextProcess.getTimeLeft() <= 1) {
                currentQueue.remove();
            }
            return nextProcess;
        }
        return null;
    }
 

}
