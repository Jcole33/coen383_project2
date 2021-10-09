import HPF.HPFNonPrempt;
import HPF.HPFPrempt;
import main.Process;

public class App {
    public static void main(String[] args) throws Exception {
        HPFNonPrempt hpfNonPremptProcess = new HPFNonPrempt();
        hpfNonPremptProcess.run(100);
        Process.resetRNG();
        HPFPrempt hpfPremptProcess = new HPFPrempt();
        hpfPremptProcess.run(100);
    }
}
