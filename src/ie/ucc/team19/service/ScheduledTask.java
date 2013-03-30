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

    /**
     * Contructor for ScheduledTasks. Named task will be executed every given
     * number of milis.
     * @param milis - interval before repeating task.
     * @param taskName - selection of task to perform.
     * @param connector - DBConnectionManager for db access tasks
     */
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

    /**
     * Inner class to encapsulate calling expirePendingEnrolls 
     * @author Cormac
     */
    private class UnEnroll extends TimerTask {
        /**
         * Called by java.util.Timer to execute expirePendingEnrolls
         */
        @Override
        public void run() {
            new UpdateUser(connector).expirePendingEnrolls();
            System.out.println("Removing expired pending enrollments");
        }
    }

    /**
     * Inner class to encapsulate calling expireUnverifiedStudents 
     * @author Cormac
     */
    private class UnRegUnverified extends TimerTask {
        /**
         * Called by java.util.Timer to execute expireUnverifiedStudents
         */
        @Override
        public void run() {
            new UpdateUser(connector).expireUnverifiedStudents();
            System.out.println("Removing unverified registrations");
        }
    }

    /**
     * Called on servlet container shutdown, timer thread stopped.
     */
    public void stop() {
        timer.cancel();
    }
}
