public class MazeTwoTransition extends Transition<MazeTwoContent, Integer> {


    public MazeTwoTransition(Location<MazeTwoContent> target) {
        super(target);
    }

    @Override
    public boolean canTransit(State<MazeTwoContent, Integer> curr) {
        if (curr.getUserLocation().getContent().getVelocity() + curr.info <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public State<MazeTwoContent, Integer> transit(State<MazeTwoContent, Integer> curr) {
        System.out.println("Target location" + target);
        System.out.println("Current location" + curr.getUserLocation());
        System.out.println("State velocity" + curr.getInfo());
        System.out.println("-----");

        return new MazeTwoState(target, curr.getInfo() + curr.getUserLocation().getContent().getVelocity());
    }



}
