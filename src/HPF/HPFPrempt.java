package HPF;

import main.Process;

import java.util.Queue;


public class HPFPrempt extends HPF {
    
    public HPFPrempt(int seed, boolean aging) {
        super(seed, aging);
    }
    
    public Process getNextProcess() {
        Queue<Process> currentQueue = getNextQueue();
        if (currentQueue != null) {
            Process nextProcess = currentQueue.peek();
            if (nextProcess.getTimeLeft() == 1) {
                currentQueue.remove();
            }
            return nextProcess;
        }
        return null;
    }
 

}
