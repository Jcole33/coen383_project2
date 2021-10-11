import HPF.HPFNonPrempt;
import HPF.HPFPrempt;
import HPF.HPF;
import RR.RR;

public class App {
    public static void main(String[] args) throws Exception {
//        HPF process = new HPFNonPrempt(4, false);
//        process.printProcesses();
//        System.out.println("HPFNonPrempt:");
//        process.run(100);
//        process = new HPFNonPrempt(4, true);
//        System.out.println("HPFNonPrempt with aging:");
//        process.run(100);
//        process = new HPFPrempt(4, false);
//        System.out.println("HPFPrempt:");
//        process.run(100);
//        process = new HPFPrempt(4, true);
//        System.out.println("HPFPrempt with aging:");
//        process.run(100);
        RR process = new RR(4);
        process.printProcesses();
        System.out.println("RR:");
        process.run(100);
    }
    
}
