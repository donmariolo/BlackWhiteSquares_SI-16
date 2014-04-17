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
		Node bestSuccessor = null;
		boolean local_best = false;

		while (!problem.isFinalState(currentNode.getState()) && !local_best) {

			bestSuccessor = expand(currentNode, problem);

			if (bestSuccessor != null
					&& currentNode.compareTo(bestSuccessor) == 1) {
				local_best = true;

			} else {
				currentNode = bestSuccessor;
				BlWhEntorno nuevoEntorno = (BlWhEntorno) currentNode.getState();
				int pos = nuevoEntorno.getPosicion();
				System.out.println("");
				System.out.println("Posicion: " + pos);
			}
		}

		if (bestSuccessor != null
				&& problem.isFinalState(currentNode.getState())) {
			currentNode = bestSuccessor;
		}

		return currentNode;

	}

	protected Node expand(Node node, Problem problem) {

		State currentState = node.getState();
		Node bestSuccessor = null;
		Node successor = null;
		Operator first = problem.getOperators().get(0);

		if (node != null && problem != null) {

			if (currentState != null) {

				bestSuccessor = new Node(first.apply(node.getState()));

				for (int i = 1; i < problem.getOperators().size(); i++) {

					first = problem.getOperators().get(i);

					successor = new Node(first.apply(node.getState()));

					if (successor.getState() != null) {

						successor.setH(this.getEvaluationFunction().calculateH(
								successor));
						successor.setG(this.getEvaluationFunction().calculateG(
								successor));

						bestSuccessor.setH(this.getEvaluationFunction()
								.calculateH(bestSuccessor));
						bestSuccessor.setG(this.getEvaluationFunction()
								.calculateG(bestSuccessor));

						if (successor.compareTo(bestSuccessor) == 1) {
							bestSuccessor = successor;
							bestSuccessor.setOperator(first.getName());
							bestSuccessor.setH(this.getEvaluationFunction()
									.calculateH(bestSuccessor));
							bestSuccessor.setG(this.getEvaluationFunction()
									.calculateG(bestSuccessor));

						}
					}
				}

				if (!problem.isFullyObserved(bestSuccessor.getState())) {

					BlWhEntorno e = (BlWhEntorno) problem
							.gatherPercepts(bestSuccessor.getState());

					e.toString();
				}

			}
		}

		return bestSuccessor;
	}

	public static void main(String[] args) {

	}

}
