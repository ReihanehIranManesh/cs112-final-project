public class StartLocation<T> extends Location<T> {
    public StartLocation(int r, int c, T con)
    {
        super(r,c, con);

    }

    @Override
    public boolean isStart() {
        return true;
    }
}
