package HPF;

import main.Clock;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class HPF{
    Clock globalClock = new Clock();
    List<Queue<JProcess>> queueList = new ArrayList<Queue<JProcess>>();
    Queue<JProcess> arrivalList;

    public HPF(int processCount) {
        //initialize priority level queues
        for (int i = 0; i < 4; ++i) {
            queueList.add(new LinkedList<JProcess>());
        }
        List<JProcess> processList = new ArrayList<JProcess>();
        //create new proceses
        for (int i = 0; i < processCount; ++i) {
            processList.add(new JProcess(globalClock));
        }
        //sorts processes by arrival time
        Collections.sort(processList);
        arrivalList = new LinkedList<JProcess>(processList);
    }
    public float getTime() {
        return globalClock.time;
    }
}
