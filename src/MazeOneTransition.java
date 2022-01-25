public class MazeOneTransition extends Transition<Integer, Void> {


    public MazeOneTransition(Location<Integer> target) {
        super(target);
    }

    @Override
    public boolean canTransit(State<Integer, Void> curr) {
        return true;
    }

    public State<Integer, Void> transit (State<Integer, Void> curr)
    {
        return new MazeOneState(target);
    }

}
