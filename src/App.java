import HPF.HPFBatchRun;
import HPF.HPFNonPrempt;
import HPF.HPFPrempt;
import HPF.HPFNonPremptBatchRun;
import HPF.HPFPremptBatchRun;
import HPF.HPF;

public class App {
    public static void main(String[] args) throws Exception {
        HPFBatchRun process = new HPFNonPremptBatchRun(5, false);
        process.printProcesses();
        System.out.println("HPFNonPrempt:");
        process.run(100);
        process.printStats();
        System.out.println("HPFNonPrempt with aging:");
        process = new HPFNonPremptBatchRun(5, true);
        process.run(100);
        process.printStats();
        System.out.println("HPFPrempt:");
        process = new HPFPremptBatchRun(5, false);
        process.run(100);
        process.printStats();
        System.out.println("HPFPrempt with aging:");
        process = new HPFPremptBatchRun(5, true);
        process.run(100);
        process.printStats();
    }
    
}
