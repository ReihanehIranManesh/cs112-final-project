import java.util.Objects;

public class State {

    private Location userLocation;
    private Information info;


    private State previousState;

    public State(Location userLocation) {
        this.userLocation = userLocation;
        this.previousState = null;
    }

    public Location getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(Location userLocation) {

        this.userLocation = userLocation;
    }

    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }

    public State getPreviousState() {
        return previousState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(userLocation, state.userLocation) && Objects.equals(info, state.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userLocation, info);
    }

    @Override
    public String toString() {
        return "State{" +
                "userLocation=" + userLocation +
                ", info=" + info +
                '}';
    }
}
