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
        processList.add(new JProcess("J", globalClock));
        processList.add(new JProcess("K", globalClock));
        processList.add(new JProcess("L", globalClock));
        processList.add(new JProcess("M", globalClock));
        processList.add(new JProcess("N", globalClock));
        processList.add(new JProcess("O", globalClock));
        processList.add(new JProcess("P", globalClock));
        processList.add(new JProcess("Q", globalClock));
        processList.add(new JProcess("R", globalClock));
        processList.add(new JProcess("S", globalClock));
        processList.add(new JProcess("T", globalClock));
        processList.add(new JProcess("U", globalClock));
        processList.add(new JProcess("V", globalClock));
        processList.add(new JProcess("W", globalClock));
        processList.add(new JProcess("X", globalClock));
        processList.add(new JProcess("Y", globalClock));
        processList.add(new JProcess("Z", globalClock));
        processList.add(new JProcess("1", globalClock));
        processList.add(new JProcess("2", globalClock));
        processList.add(new JProcess("3", globalClock));
        processList.add(new JProcess("4", globalClock));
        processList.add(new JProcess("5", globalClock));
        processList.add(new JProcess("6", globalClock));
        processList.add(new JProcess("7", globalClock));
        processList.add(new JProcess("8", globalClock));
        processList.add(new JProcess("9", globalClock));



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
