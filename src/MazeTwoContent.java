import java.util.List;

public class MazeTwoContent {
    private int velocity;
    private List<String> directions;

    public int getVelocity() {
        return velocity;
    }

    public List<String> getDirections() {
        return directions;
    }

    public MazeTwoContent(int velocity, List<String> directions) {
        this.velocity = velocity;
        this.directions = directions;
    }

//    @Override
//    public String toString() {
//        return "MazeTwoContent{" +
//                "velocity=" + velocity +
//                ", directions=" + directions +
//                '}';
//    }

    @Override
    public String toString() {
        String joined = String.join("_", directions);
        return joined+ "_" + velocity;
    }

}
