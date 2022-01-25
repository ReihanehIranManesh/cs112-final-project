import java.util.List;

public class MazeThreeContent {
    private char color;
    private String direction;

    public char getColor() {
        return color;
    }

    public String getDirection() {
        return direction;
    }

    public MazeThreeContent(char color, String direction) {
        this.color = color;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return direction + "_" + color;
    }

}
