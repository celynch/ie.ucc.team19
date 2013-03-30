package ie.ucc.team19.dao;

/**
 * Bean to represent a list of course Category names. 
 * @author Cormac
 */
public class CategoryBean {
    String[] categoryTitles;

    public String[] getCategoryTitles() {
        return categoryTitles;
    }

    public void setCategoryTitles(String[] catagoryTitles) {
        this.categoryTitles = catagoryTitles;
    }
}
