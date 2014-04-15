package algorithms;

import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.heuristic.EvaluationFunction;
import es.deusto.ingenieria.is.search.algorithms.heuristic.HeuristicSearchMethod;
import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.Problem;
import es.deusto.ingenieria.is.search.formulation.State;
import formulation.BlWhEntorno;

public class OnlineHillClimbing extends HeuristicSearchMethod {

	public OnlineHillClimbing(EvaluationFunction function) {
		super(function);
	}

	@Override
	public Node search(Problem problem, State initialState) {
		Node currentNode = new Node(initialState);
		boolean local_best = false;

		while (!problem.isFinalState(currentNode.getState()) && !local_best) {

			currentNode.setH(getEvaluationFunction().calculateH(currentNode));

			Node bestSuccessor = expand(currentNode, problem);
			bestSuccessor.setH(this.getEvaluationFunction().calculateH(
					bestSuccessor));

			if (currentNode.compareTo(bestSuccessor) == -1
					|| currentNode.compareTo(bestSuccessor) == 0) {

				System.out.println("Entra");
				local_best = true;
				// currentNode = bestSuccessor;

			} else {
				currentNode = bestSuccessor;

			}

		}

		Node finalState = new Node(currentNode.getState());

		return finalState;

	}

	protected Node expand(Node node, Problem problem) {
		// TODO: BestSuccessor = CurrentNode's first successor

		Operator first = problem.getOperators().get(0);

		Node bestSuccessor = new Node(first.apply(node.getState()));

		for (int i = 1; i < problem.getOperators().size(); i++) {

			Operator o = problem.getOperators().get(i);

			Node successor = new Node(o.apply(node.getState()));

			if (successor.getState() != null) {

				// TODO: Compute f(Successor) + if f(Successor)>f(BestSuccessor)
				successor.setH(this.getEvaluationFunction().calculateH(
						successor));

				bestSuccessor.setH(this.getEvaluationFunction().calculateH(
						bestSuccessor));

				if (successor.compareTo(bestSuccessor) == -1) {
					bestSuccessor = successor;
					bestSuccessor.setOperator(o.getName());

					// bestSuccessor.setOperator(o.toString());

				}
			}

		}
		if (!problem.isFullyObserved(bestSuccessor.getState())) {

			@SuppressWarnings("unused")
			BlWhEntorno e = (BlWhEntorno) problem.gatherPercepts(bestSuccessor
					.getState());

		}
		System.out.println("expand:" + bestSuccessor.toString());
		return bestSuccessor;
	}

	public static void main(String[] args) {

	}

}
