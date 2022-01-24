import java.util.*;

public class Solver<T> {

    Location<T> goalLocation;


    public Solver(Location<T> goalLocation) {
        this.goalLocation = goalLocation;
    }
    public Stack<State<T>> solve(State<T> startState, Maze<T> maze) {

        Stack<State<T>> solution = new Stack<>();

        List<State<T>> states = new ArrayList<>();
        Set<State<T>> visitedStates = new HashSet<>();
        states.add(startState);
        int index = 0;
        while (states.size() > 0) {

            List<State<T>> newStates = new ArrayList<>();
            for (State<T> curState : states) {
                if (visitedStates.contains(curState)) {
                    continue;
                }
                List<Transition<T>> transitions = curState.getTransitions(maze.getMazeRow(), maze.getMazeCol(), maze.getLocationGrid());
                for (Transition<T> transition : transitions) {
                    if (transition.canTransit(curState)) {
                        State<T> newState = transition.transit(curState);
                        newState.setPreviousState(curState);
                        if (newState.getUserLocation().isGoal()) {
                            System.out.println("Solution found");
                            State<T> cur = newState;
                            solution.add(cur);
                            while (cur.getPreviousState() != null) {
                                cur = cur.getPreviousState();
                                solution.add(cur);
                            }
                            return solution;
                        }
                        newStates.add(newState);
                    }
                }
                visitedStates.add(curState);
            }
            states = newStates;
        }

        return solution;
    }
}
