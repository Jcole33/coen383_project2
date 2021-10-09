package HPF;
import main.Process;
import main.Clock;

public class JProcess extends Process implements Comparable<JProcess> {
    JProcess(Clock globalClock) {
        super(globalClock);
    }

    @Override
    public int compareTo(JProcess otherProcess) {
        if (getArrivalTime() > otherProcess.getArrivalTime()) {
            return 1;
        } else if (getArrivalTime() == otherProcess.getArrivalTime()) {
            return 0;
        } else {
            return -1;
        }
    }
}
