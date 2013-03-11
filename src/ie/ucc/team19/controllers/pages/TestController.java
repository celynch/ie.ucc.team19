package ie.ucc.team19.controllers.pages;

import ie.ucc.team19.controllers.AbstractController;

/*
import ie.ucc.team19.daos.FooDao;
import ie.ucc.team19.daos.DaoFactory;
import ie.ucc.team19.daos.BarDao;
import ie.ucc.team19.daos.FooBarDao;
import ie.ucc.team19.daos.hibernate.HibernateUtil;
import java.util.List;
*/

/**
 *
 * @author master
 */
public class TestController extends AbstractController{

    public void execute() {
        /*
        FooBarDao fooBarsDao = DaoFactory.getFooBarDao();
        BarDao barsDao = DaoFactory.getBarDao();
        FooDao foosDao = DaoFactory.getFooDao();
        HibernateUtil.beginViewTransaction();
        List fooBars = fooBarsDao.getAll();
        List foos=foosDao.getAll();
        List bars=barsDao.getAll();

        HibernateUtil.commitTransaction();
       
        this.setReturnPage("/index.jsp");
        this.getRequest().setAttribute("foos", foos);
        this.getRequest().setAttribute("bars",bars);
        this.getRequest().setAttribute("fooBars", fooBars);
        */
        
        this.setReturnPage("/Index.jsp");
        this.getRequest().setAttribute("title", "test");
    }
}
