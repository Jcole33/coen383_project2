package HPF;

import main.Process;

import java.util.Queue;


public class HPFPrempt extends HPF {
    
    public HPFPrempt(int seed, boolean aging) {
        super(seed, aging);
    }
    

    public Process getNextProcess() {
        //get queue with next process to run as head
        Queue<Process> currentQueue = getNextQueue();
        if (currentQueue != null) {
            Process nextProcess = currentQueue.peek();
            //if process will be finished on the next run then remove it from the queue entirely, otherwise leave it for later runs
            if (nextProcess.getTimeLeft() == 1) {
                currentQueue.remove();
            }
            return nextProcess;
        }
        //if no processes exist then return null
        return null;
    }
 

}
