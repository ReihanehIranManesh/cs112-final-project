public class Transition extends Location {
    public Transition(int r, int c, int con) {
        super(r, c, con);
    }

    public String toString() {
        return "" + this.getRow() + "," + this.getCol() + " " + this.getContent();
    }

    public State checkTransition(State curr) {

        if (getCol() < Maze.getMazeCol() && getRow() < Maze.getMazeRow() && getCol() >= 0 && getRow() >= 0) {

            int LocationContent = curr.getContent();
            int LocationRow = curr.getRow();
            int LocationCol = curr.getCol();
            State l;

            if ((LocationCol + LocationContent == getCol() || LocationCol - LocationContent == getCol()) && LocationRow == getRow()){
                return new State(getRow(), getCol(), getContent());
            }


            else if ((LocationRow + LocationContent == getRow() || LocationRow - LocationContent == getRow()) && LocationCol == getCol()){
                return new State(getRow(), getCol(), getContent());
            }
        }
        return null;
    }
}
