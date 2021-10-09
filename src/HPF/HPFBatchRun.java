package HPF;

import java.util.List;


public abstract class HPFBatchRun {
    int totalThroughPut = 0;
    float totalAvgWait = 0;
    float totalAvgResponse = 0;
    float totalAvgTurnAround = 0;
    List<HPF> runList;

    public HPFBatchRun(int times) {
        for (int i = 0; i < times; ++i) {
            HPF hpfProcess = getHPFProcess(i);
            runList.add(hpfProcess);
        }
    }

    public void run(int length) {
        for (int i = 0; i < runList.size(); ++i) {
            HPF currentRun = runList.get(i);
            currentRun.run(length);
            //currentRun.printStats();
            totalThroughPut += currentRun.getThroughPut();
            totalAvgWait += currentRun.getAvgWait();
            totalAvgResponse += currentRun.getAvgResponse();
            totalAvgTurnAround += currentRun.getAvgTurnAround();
        }
    }

    public abstract HPF getHPFProcess(int seed);

    public float getAvgResponse() {
        return totalAvgResponse / runList.size();
    }
    public float getAvgWait() {
        return totalAvgWait / runList.size();
    }
    public float getAvgTurnAround() {
        return totalAvgTurnAround / runList.size();
    }
    public int getThroughPut() {
        return totalThroughPut;
    }
    public void printStats() {
        System.out.println("Average Response Time: " + getAvgResponse() + " Average Wait Time: " + getAvgWait() + " Average Turn Around Time: " + getAvgTurnAround() + " Throughput: " + getThroughPut());
    }
}
