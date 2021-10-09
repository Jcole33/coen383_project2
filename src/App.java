import HPF.HPFBatchRun;
import HPF.HPFNonPremptBatchRun;
import HPF.HPFPremptBatchRun;


public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("HPFNonPrempt:");
        HPFBatchRun batch = new HPFNonPremptBatchRun(5);
        batch.run(100);
        batch.printStats();
        System.out.println("HPFPrempt:");
        batch = new HPFPremptBatchRun(5);
        batch.run(100);
        batch.printStats();
    }
    
}
