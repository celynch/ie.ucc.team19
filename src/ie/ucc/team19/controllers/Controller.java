package ie.ucc.team19.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
    
    public void execute();
    public void init(HttpServletRequest request, HttpServletResponse response);
    public String getReturnPage();
}
