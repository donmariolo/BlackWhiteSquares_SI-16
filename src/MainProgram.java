import algorithms.ASearch;
import algorithms.OnlineHillClimbing;
import es.deusto.ingenieria.is.search.algorithms.blind.BreadthFS;
import es.deusto.ingenieria.is.search.algorithms.blind.DepthFS;
import es.deusto.ingenieria.is.search.algorithms.heuristic.BestFS;
import formulation.BlWhFuncionEvaluacion;
import formulation.BlWhProblem;

public class MainProgram {

	public static void main(String[] args) {
		try {
			BlWhProblem problem = new BlWhProblem();
			problem.addInitialState(problem.gatherInitialPercepts());

			problem.createOperators();

//			problem.solve(BreadthFS.getInstance());
//			System.out.println("");
//			problem.solve(DepthFS.getInstance());
//			System.out.println("");
//			problem.solve(new BestFS(new BlWhFuncionEvaluacion()));
//			System.out.println("");
//			problem.solve(new ASearch(new BlWhFuncionEvaluacion()));
			
			System.out.println("");
			problem.solve(new OnlineHillClimbing(new BlWhFuncionEvaluacion()));


		} catch (Exception ex) {
			System.err.println("% [Main Program] Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

}
