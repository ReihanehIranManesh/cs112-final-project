public class GoalLocation<T> extends Location<T> {
    public GoalLocation(int r, int c, T con) {
        super(r,c, con);

    }
    @Override
    public boolean isGoal() {
        return true;
    }
}
