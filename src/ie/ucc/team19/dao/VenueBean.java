package ie.ucc.team19.dao;

public class VenueBean {
    private int venueId;
    private String venueRoom;
    private String venueBuilding;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private int capacity;
    private boolean onCampus;

    public VenueBean() {

    }

    public int getVenueId() {
        return venueId;
    }
    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }
    public String getVenueRoom() {
        return venueRoom;
    }
    public void setVenueRoom(String venueRoom) {
        this.venueRoom = venueRoom;
    }
    public String getVenueBuilding() {
        return venueBuilding;
    }
    public void setVenueBuilding(String venueBuilding) {
        this.venueBuilding = venueBuilding;
    }
    public String getAddressLine1() {
        return addressLine1;
    }
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    public String getAddressLine2() {
        return addressLine2;
    }
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    public String getAddressLine3() {
        return addressLine3;
    }
    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public boolean isOnCampus() {
        return onCampus;
    }
    public void setOnCampus(boolean onCampus) {
        this.onCampus = onCampus;
    }
}
