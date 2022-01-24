import java.util.*;

public class Solver {

    Location goalLocation;

    public Solver(Location goalLocation) {
        this.goalLocation = goalLocation;
    }
    public Stack<State> solve(State startState) {

        Stack<State> solution = new Stack<>();

        List<State> states = new ArrayList<>();
        Set<State> visitedStates = new HashSet<>();
        states.add(startState);
        int index = 0;
        while (states.size() > 0) {

            List<State> newStates = new ArrayList<>();
            for (State curState : states) {
                if (visitedStates.contains(curState)) {
                    continue;
                }
                List<Transition> transitions = curState.getUserLocation().getTransitions();
                for (Transition transition : transitions) {
                    if (transition.canTransit(curState)) {
                        State newState = transition.transit(curState);
                        newState.setPreviousState(curState);
                        if (newState.getUserLocation().isGoal()) {
                            System.out.println("Solution found");
                            State cur = newState;
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
