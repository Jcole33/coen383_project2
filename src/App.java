import HPF.HPFNonPrempt;
import HPF.HPFPrempt;
import HPF.HPF;
import SJF.SJF;
import SRT.SRT;
public class App {
    public static void main(String[] args) throws Exception {
        for (int seed = 0; seed < 5; ++seed) {
            System.out.println("With Seed " + seed + ":");
            HPF process = new HPFNonPrempt(seed, false);
            process.printProcesses();
            System.out.println("HPFNonPrempt:");
            process.run(100);
            process = new HPFNonPrempt(seed, true);
            System.out.println("HPFNonPrempt with aging:");
            process.run(100);
            process = new HPFPrempt(seed, false);
            System.out.println("HPFPrempt:");
            process.run(100);
            process = new HPFPrempt(seed, true);
            System.out.println("HPFPrempt with aging:");
            process.run(100);
            System.out.println("Shortest Job First:");
            SJF.Schedule(seed);
            System.out.println("Shortest Remaining Time:");
            SRT.run(seed);
        }

    }
    
}
