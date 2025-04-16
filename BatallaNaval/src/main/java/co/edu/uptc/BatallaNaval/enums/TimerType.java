package co.edu.uptc.BatallaNaval.enums;

public enum TimerType {
    PLACEMENT(90),
    TURN(15);

    private final int duration;

    TimerType(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}