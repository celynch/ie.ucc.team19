package ie.ucc.team19.dao;

public class VenueBean {
    private int venue_id;
    private String venue_room;
    private String venue_building;
    private String address_line1;
    private String address_line2;
    private String address_line3;
    private int capacity;
    private boolean on_campus;

    public int getVenue_id() {
        return venue_id;
    }
    public void setVenue_id(int venue_id) {
        this.venue_id = venue_id;
    }
    public String getVenue_room() {
        return venue_room;
    }
    public void setVenue_room(String venue_room) {
        this.venue_room = venue_room;
    }
    public String getVenue_building() {
        return venue_building;
    }
    public void setVenue_building(String venue_building) {
        this.venue_building = venue_building;
    }
    public String getAddress_line1() {
        return address_line1;
    }
    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }
    public String getAddress_line2() {
        return address_line2;
    }
    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }
    public String getAddress_line3() {
        return address_line3;
    }
    public void setAddress_line3(String address_line3) {
        this.address_line3 = address_line3;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public boolean isOn_campus() {
        return on_campus;
    }
    public void setOn_campus(boolean on_campus) {
        this.on_campus = on_campus;
    }
}
