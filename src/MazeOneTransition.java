public class MazeOneTransition extends Transition<Integer> {


    public MazeOneTransition(Location<Integer> target) {
        super(target);
    }

    public State<Integer> transit (State<Integer> curr)
    {
        return new MazeOneState(target);
    }

}
