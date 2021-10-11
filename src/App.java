import HPF.HPFNonPrempt;
import HPF.HPFPrempt;
import HPF.HPF;

public class App {
    public static void main(String[] args) throws Exception {
        HPF process = new HPFNonPrempt(4, false);
        process.printProcesses();
        System.out.println("HPFNonPrempt:");
        process.run(100);
        process = new HPFNonPrempt(4, true);
        System.out.println("HPFNonPrempt with aging:");
        process.run(100);
        process = new HPFPrempt(4, false);
        System.out.println("HPFPrempt:");
        process.run(100);
        process = new HPFPrempt(4, true);
        System.out.println("HPFPrempt with aging:");
        process.run(100);
    }
    
}
