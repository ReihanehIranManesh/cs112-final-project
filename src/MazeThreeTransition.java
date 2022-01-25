public class MazeThreeTransition extends Transition<MazeThreeContent, Character> {


    public MazeThreeTransition(Location<MazeThreeContent> target) {
        super(target);
    }

    @Override
    public boolean canTransit(State<MazeThreeContent, Character> curr) {
        return true;
    }


    @Override
    public State<MazeThreeContent, Character> transit(State<MazeThreeContent, Character> curr) {

        return new MazeThreeState(target, curr.getUserLocation().getContent().getColor() == 'R' ? 'B' : 'R');
    }



}
