package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.heuristic.EvaluationFunction;
import es.deusto.ingenieria.is.search.algorithms.heuristic.HeuristicSearchMethod;
import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.Problem;
import es.deusto.ingenieria.is.search.formulation.State;

public class ASearch extends HeuristicSearchMethod {

	public ASearch(EvaluationFunction function) {
		super(function);
	}

	public Node search(Problem problem, State initialState) {

		List<Node> frontier = new ArrayList<Node>();
		Node firstNode = null;
		List<Node> successorNodes = null;
		boolean solutionFound = false;

		frontier.add(new Node(initialState));

		while (!solutionFound && !frontier.isEmpty()) {

			firstNode = frontier.remove(0);

			if (problem.isFinalState(firstNode.getState())) {
				solutionFound = true;
			} else {
				successorNodes = this.expand(firstNode, problem);
				if (successorNodes != null && !successorNodes.isEmpty()) {
					frontier.addAll(successorNodes);
					Collections.sort(frontier);
				}
			}
		}

		if (solutionFound) {
			return firstNode;
		} else {
			return null;
		}
	}

	protected List<Node> expand(Node node, Problem problem) {
		List<Node> successors = new ArrayList<Node>();
		Node successorNode = null;
		State currentState = null;
		State successorState = null;
		if (node != null && problem != null) {

			currentState = node.getState();
			if (currentState != null) {

				for (Operator operator : problem.getOperators()) {

					successorState = operator.apply(currentState);

					if (successorState != null) {
						successorNode = new Node(successorState);

						successorNode.setOperator(operator.getName());
						successorNode.setParent(node);
						successorNode.setDepth(node.getDepth() + 1);

						successorNode.setH(this.getEvaluationFunction()
								.calculateH(successorNode));
						successorNode.setG(this.getEvaluationFunction()
								.calculateG(successorNode));

						successors.add(successorNode);
					}
				}
			}
		}

		return successors;
	}

}
