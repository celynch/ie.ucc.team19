package ie.ucc.team19.service;

import java.util.ArrayList;

import ie.ucc.team19.dao.DBConnectionManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupListener implements ServletContextListener {
    private ArrayList<ScheduledTask> tasks = new ArrayList<ScheduledTask>();

    /**
     * Called on application start. Setsup properties for access via the
     * servletcontext. Initiates scheduled task timers.
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PropertiesReader properties = new PropertiesReader();
        sce.getServletContext().setAttribute("properties", properties);
        DBConnectionManager connector = new DBConnectionManager(properties);
        int milisPerDay = 1000*60*60*24;
        int milisPerHour = 1000*60*60;
        tasks.add(new ScheduledTask(milisPerDay, "expireUnverifiedStudents", connector));
        tasks.add(new ScheduledTask(milisPerHour * 2, "expirePendingEnrolls", connector));
    }

    /**
     * Called on application shutdown. Performs clean up tasks, releases
     * resources, cancels scheduled tasks.
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        for(ScheduledTask task : tasks) {
            task.stop();
        }
    }
}
