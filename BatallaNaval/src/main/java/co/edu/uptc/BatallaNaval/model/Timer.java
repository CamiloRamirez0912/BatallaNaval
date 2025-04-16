package co.edu.uptc.BatallaNaval.model;

import co.edu.uptc.BatallaNaval.enums.TimerType;
import lombok.Getter;

@Getter
public class Timer extends Thread {
    private TimerType timerType;
    private int remainingTime;
    private boolean running;
    private Runnable onTimeUp;

    public Timer(TimerType timerType, Runnable onTimeUp) {
        this.timerType = timerType;
        this.remainingTime = timerType.getDuration();
        this.running = true;
        this.onTimeUp = onTimeUp;
    }

    @Override
    public void run() {
        while (running && remainingTime > 0) {  
            try {
                Thread.sleep(1000);
                remainingTime--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        if (remainingTime <= 0 && onTimeUp != null) {
            onTimeUp.run();
        }
    }

    public void stopTimer() {
        running = false;
    }

    public void resetTimer() {
        remainingTime = timerType.getDuration();
    }
}