package main;
public class Clock {
    float time = 0;
    public float getTime() {
        return time;
    }
    public void incrementTime(float increment) {
        time += increment;
    }
}
