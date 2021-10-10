package HPF;

import java.util.List;
import java.util.ArrayList;

public abstract class HPFBatchRun {
    int totalThroughPut = 0;
    float totalAvgWait = 0;
    float totalAvgResponse = 0;
    float totalAvgTurnAround = 0;
    List<HPF> runList = new ArrayList<HPF>();

    public HPFBatchRun(int times, boolean aging) {
        for (int i = 0; i < times; ++i) {
            HPF hpfProcess = getHPFProcess(i, aging);
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

    public abstract HPF getHPFProcess(int seed, boolean aging);

    public float getAvgResponse() {
        return totalAvgResponse / runList.size();
    }
    public float getAvgWait() {
        return totalAvgWait / runList.size();
    }
    public float getAvgTurnAround() {
        return totalAvgTurnAround / runList.size();
    }
    public float getThroughPut() {
        return (float) totalThroughPut / runList.size();
    }
    public void printStats() {
        System.out.println("Average Response Time: " + getAvgResponse() + " Average Wait Time: " + getAvgWait() + " Average Turn Around Time: " + getAvgTurnAround() + " Throughput: " + getThroughPut());
    }
    public void printProcesses() {
        for (int i = 0; i < runList.size(); ++i) {
            System.out.println("Processes for run " + i);
            runList.get(i).printProcesses();
        }
    }
    public List<HPF> getRunList() {
        return runList;
    }
}
