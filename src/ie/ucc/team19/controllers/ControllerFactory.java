package ie.ucc.team19.controllers;

/**
 * ControllerFactory uses the factory pattern to instantiate the correct
 * controller class based on the URL passed from the FrontController
 * servlet.
 * @author Cormac
 * @see FrontController
 */
public class ControllerFactory {
    /**
     * Given a class type for a controller, instatiates and returns it.
     * @param actionClass - the class type to be instantiated.
     * @return - the controller class instantiated.
     */
    public static final Controller getControllerByClass(Class<?> actionClass) {
        try {
            Controller controller = (Controller) actionClass.newInstance();
            return controller;
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Given a String to represent a class, determines the full classpath
     * and calls getControllerByClass to instantiate it.
     * @param className - String representing the class to instantiate.
     * @return - the controller class instantiated.
     */
    public static final Controller getControllerByFullClassName(String className) {
        try {
            String name = "ie.ucc.team19.controllers.pages." + className + "Controller";
            Class<?> actionClass = Class.forName(name);
            return getControllerByClass(actionClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
