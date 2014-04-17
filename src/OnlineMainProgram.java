import algorithms.OnlineHillClimbing;
import formulation.BlWhFuncionEvaluacion;
import formulation.BlWhProblem;

public class OnlineMainProgram {

	public static void main(String[] args) {
		try {
			BlWhProblem problem = new BlWhProblem();
			problem.addInitialState(problem.gatherInitialPercepts());

			problem.createOperators();

			problem.solve(new OnlineHillClimbing(new BlWhFuncionEvaluacion()));

		} catch (Exception ex) {
			System.err.println("% [Main Program] Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

}
