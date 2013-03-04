package ie.ucc.team19.controllers;

public class ControllerFactory {

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

    public static final Controller getControllerByFullClassName(String className) {
        try {
            String name = "ie.ucc.team19.controllers." + className + "Controller";
            Class<?> actionClass = Class.forName(name);
            return getControllerByClass(actionClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
