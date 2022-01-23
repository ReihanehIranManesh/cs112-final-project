public class StartLocation extends Location {
    public StartLocation(int r, int c, int con)
    {
        super(r,c, con);

    }

    @Override
    public boolean isStart() {
        return true;
    }
}
