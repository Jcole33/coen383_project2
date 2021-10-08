package HPF;
import java.util.Queue;

import Clock;
import Process;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class HPFNonPrempt {
    Clock globalClock = new Clock();
    List<Queue<Process>> queueList = new ArrayList<Queue<Process>>();
    Queue<Process> arrivalList = new LinkedList<Process>();

    HPFNonPrempt(int processCount) {
        //initialize priority level queues
        for (int i = 0; i < 4; ++i) {
            queueList.add(new LinkedList<Process>());
        }
        List<Process> processList = new ArrayList<Process>();
        //create new proceses
        for (int i = 0; i < processCount; ++i) {
            processList.add(new Process(globalClock));
        }
        Collections.sort(processList, (a, b) -> {
            return a.arrivalTime > b.arrivalTime ? -1 : 1;
        });
        
        for (int i = 0; i < 4; ++i) {
            queueList.add(new LinkedList<Process>());
        }
    }
}
