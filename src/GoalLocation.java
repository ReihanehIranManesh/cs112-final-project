public class GoalLocation extends Location{
    public GoalLocation(int r, int c, int con) {
        super(r,c, con);

    }

    @Override
    public boolean isGoal() {
        return true;
    }
}
