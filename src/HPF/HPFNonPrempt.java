package HPF;

import main.Process;

import java.util.Queue;

public class HPFNonPrempt extends HPF {
    Process lastProcess = null;
    public HPFNonPrempt(int seed, boolean aging) {
        super(seed, aging);
    }

    public Process getNextProcess() {
        //if the last process run still isnt finished yet, then continue to run that since nonprempt
        if (lastProcess != null && lastProcess.getTimeLeft() > 0) {
            return lastProcess;
        }
        //otherwise get the queue containing the next process that should run based on HPF algorithm and pop it out
        Queue<Process> currentQueue = getNextQueue();
        if (currentQueue != null) {
            lastProcess = currentQueue.remove();
            return lastProcess;
        }
        //if no process to run then just return null
        return null;
    }
 

}
