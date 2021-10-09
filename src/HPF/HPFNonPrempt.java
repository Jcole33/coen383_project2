package HPF;

import java.util.Queue;


public class HPFNonPrempt extends HPF {
    
    public HPFNonPrempt(int processCount) {
        super(processCount);
    }

    public void run(float endTime) {
        while (getTime() < endTime) {
            addArrivals();
            JProcess nextProcess = getNextProcess();
            nextProcess.run(nextProcess.getExpectedRuntime());
            for (int i = 0; i < nextProcess.getExpectedRuntime(); ++i) {
                runString += nextProcess.getName();
            }
        }
        System.out.println(runString);
    }
    public JProcess getNextProcess() {
        Queue<JProcess> currentQueue = getNextQueue();
        if (currentQueue != null) {
            return currentQueue.remove();
        }
        return null;
    }
 

}
