public class State {

    private Location userLocation;
    private Information info;
    private Location previousUserLocation;

    public State(Location userLocation) {
        this.userLocation = userLocation;
        this.previousUserLocation = this.userLocation;
    }

    public Location getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(Location userLocation) {
        this.previousUserLocation = this.userLocation;
        this.userLocation = userLocation;
    }

}
