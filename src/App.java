import HPF.HPFNonPrempt;
import HPF.HPFPrempt;

public class App {
    public static void main(String[] args) throws Exception {
        HPFNonPrempt hpfNonPremptProcess = new HPFNonPrempt();
        hpfNonPremptProcess.run(100);
        HPFPrempt hpfPremptProcess = new HPFPrempt();
        hpfPremptProcess.run(100);
    }
}
