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
    String runString = "";

    public HPF() {
        //initialize priority level queues
        for (int i = 0; i < 4; ++i) {
            queueList.add(new LinkedList<JProcess>());
        }
        List<JProcess> processList = new ArrayList<JProcess>();
        //create new proceses
        processList.add(new JProcess("A", globalClock));
        processList.add(new JProcess("B", globalClock));
        processList.add(new JProcess("C", globalClock));
        processList.add(new JProcess("D", globalClock));
        processList.add(new JProcess("E", globalClock));
        processList.add(new JProcess("F", globalClock));
        processList.add(new JProcess("G", globalClock));
        processList.add(new JProcess("H", globalClock));
        processList.add(new JProcess("I", globalClock));

        //sorts processes by arrival time
        Collections.sort(processList);
        arrivalList = new LinkedList<JProcess>(processList);
        System.out.println("Processes:");
        for (int i = 0; i < arrivalList.size(); ++i) {
            System.out.println(processList.get(i));
        }

    }

    public float getTime() {
        return globalClock.getTime();
    }
    public Queue<JProcess> getNextQueue() {
        for (int i = 0; i < 4; ++i) {
            Queue<JProcess> currentQueue = queueList.get(i);
            if (!currentQueue.isEmpty()) {
                return currentQueue;
            }
        }
        return null;
    }
    
    public void addArrivals() {
        while ( !arrivalList.isEmpty() && arrivalList.peek().getArrivalTime() <= getTime() ) {
            JProcess arrivingProcess = arrivalList.remove();
            queueList.get(arrivingProcess.getPriority() - 1).add(arrivingProcess);
        }
    }
}
