import HPF.HPFNonPrempt;
import HPF.HPF;

public class App {
    public static void main(String[] args) throws Exception {
        HPF process = new HPFNonPrempt(4, false);
        process.printProcesses();
        process.run(100);
        process.printStats();
        process = new HPFNonPrempt(4, true);
        process.run(100);
        process.printStats();
    }
    
}
