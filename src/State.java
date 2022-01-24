import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class State<T> {

    protected Location<T> userLocation;
    protected Information info;


    protected State<T> previousState;

    public State(Location<T> userLocation) {
        this.userLocation = userLocation;
        this.previousState = null;
    }

    public abstract List<Transition<T>> getTransitions(int mazeRow, int mazeCol, Location<T>[][] locationsGrid);


    public Location<T> getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(Location<T> userLocation) {

        this.userLocation = userLocation;
    }

    public void setPreviousState(State<T> previousState) {
        this.previousState = previousState;
    }

    public State<T> getPreviousState() {
        return previousState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State<T> state = (State<T>) o;
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
