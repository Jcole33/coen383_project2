package HPF;

import main.Process;

import java.util.Queue;

public class HPFNonPrempt extends HPF {
    Process lastProcess = null;
    public HPFNonPrempt(int seed, boolean aging) {
        super(seed, aging);
    }

    public Process getNextProcess() {
        if (lastProcess != null && lastProcess.getTimeLeft() > 0) {
            return lastProcess;
        }
        Queue<Process> currentQueue = getNextQueue();
        if (currentQueue != null) {
            lastProcess = currentQueue.remove();
            return lastProcess;
        }
        return null;
    }
 

}
