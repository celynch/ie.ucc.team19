package ie.ucc.team19.service;

import ie.ucc.team19.dao.DBConnectionManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class uses java.util.Timer to schedule a task to execute once a given
 * number of miliseconds have passed.
 */

public class ScheduledTask {
    private Timer timer;
    protected DBConnectionManager connector;

    public ScheduledTask(long milis, String taskName, DBConnectionManager connector) {
        this.connector = connector;
        timer = new Timer();
        switch (taskName) {
            case "expirePendingEnrolls":
                    timer.scheduleAtFixedRate(new UnEnroll(), milis, milis);
                    System.out.println("Task scheduled: " + taskName);
                break;
            case "expireUnverifiedStudents":
                    timer.scheduleAtFixedRate(new UnRegUnverified(), milis, milis);
                    System.out.println("Task scheduled: " + taskName);
                    break;
            default:
                    System.out.println("Task not recognised");
        }
    }

    private class UnEnroll extends TimerTask {
        @Override
        public void run() {
            new UpdateUser(connector).expirePendingEnrolls();
            System.out.println("Removing expired pending enrollments");
        }
    }

    private class UnRegUnverified extends TimerTask {
        @Override
        public void run() {
            new UpdateUser(connector).expirePendingEnrolls();
            System.out.println("Removing unverified registrations");
        }
    }

    public void stop() {
        timer.cancel();
    }
}
