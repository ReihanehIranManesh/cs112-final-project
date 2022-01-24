public abstract class Transition<T> {


    protected Location<T> target;

    public Transition(Location<T> target) {
        this.target = target;
    }

    public Location<T> getTarget() {
        return target;
    }

//
//    public String toString() {
//        return "" + this.getRow() + "," + this.getCol() + " " + this.getContent();
//    }

    public boolean canTransit(State<T> curr) {

//        if (getCol() < Maze.getMazeCol() && getRow() < Maze.getMazeRow() && getCol() >= 0 && getRow() >= 0) {
//
//            int LocationContent = curr.getContent();
//            int LocationRow = curr.getRow();
//            int LocationCol = curr.getCol();
//            State l;
//
//            if ((LocationCol + LocationContent == getCol() || LocationCol - LocationContent == getCol()) && LocationRow == getRow()){
//                return new State(getRow(), getCol(), getContent());
//            }
//
//
//            else if ((LocationRow + LocationContent == getRow() || LocationRow - LocationContent == getRow()) && LocationCol == getCol()){
//                return new State(getRow(), getCol(), getContent());
//            }
//        }
//        return null;

        return true;
    }

    public abstract State<T> transit (State<T> curr);

    @Override
    public String toString() {
        return "Transition{" +
                "target=" + target.toString() +
                '}';
    }
}
