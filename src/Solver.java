import java.util.*;

public class Solver<T, U> {

    Location<T> goalLocation;


    public Solver(Location<T> goalLocation) {
        this.goalLocation = goalLocation;
    }
    public Stack<State<T, U>> solve(State<T, U> startState, Maze<T, U> maze) {

        Stack<State<T, U>> solution = new Stack<>();

        List<State<T, U>> states = new ArrayList<>();
        Set<State<T, U>> visitedStates = new HashSet<>();
        states.add(startState);
        int index = 0;
        while (states.size() > 0) {

            List<State<T, U>> newStates = new ArrayList<>();
            for (State<T, U> curState : states) {
                if (visitedStates.contains(curState)) {
                    continue;
                }
                List<Transition<T, U>> transitions = curState.getTransitions(maze.getMazeRow(), maze.getMazeCol(), maze.getLocationGrid());
                for (Transition<T, U> transition : transitions) {
                    if (transition.canTransit(curState)) {
                        State<T, U> newState = transition.transit(curState);
                        newState.setPreviousState(curState);
                        if (newState.getUserLocation().isGoal()) {
                            System.out.println("Solution found");
                            State<T, U> cur = newState;
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
