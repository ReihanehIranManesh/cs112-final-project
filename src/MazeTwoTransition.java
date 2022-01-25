public class MazeTwoTransition extends Transition<MazeTwoContent> {


    public MazeTwoTransition(Location<MazeTwoContent> target) {
        super(target);
    }

    public MazeTwoState transit (MazeTwoState curr)
    {
        return new MazeTwoState(target, curr.getVelocity());
    }

}
