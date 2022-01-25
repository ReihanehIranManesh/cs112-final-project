public abstract class Transition<T, U> {


    protected Location<T> target;

    public Transition(Location<T> target) {
        this.target = target;
    }

    public Location<T> getTarget() {
        return target;
    }

    public abstract boolean canTransit(State<T, U> curr);

    public abstract State<T, U> transit (State<T, U> curr);

    @Override
    public String toString() {
        return "Transition{" +
                "target=" + target.toString() +
                '}';
    }
}
