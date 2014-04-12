package algorithms;

import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.heuristic.EvaluationFunction;
import es.deusto.ingenieria.is.search.algorithms.heuristic.HeuristicSearchMethod;
import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.Problem;
import es.deusto.ingenieria.is.search.formulation.State;

public class OnlineHillClimbing extends HeuristicSearchMethod {

	public OnlineHillClimbing(EvaluationFunction function) {
		super(function);
	}

	@Override
	public Node search(Problem problem, State initialState) {
	
		Node currentNode = new Node(initialState);
		currentNode.setH(getEvaluationFunction().calculateH(currentNode));

		boolean local_best = false;
		while (!local_best) {		
			Node bestSuccessor = expand(currentNode, problem);
			if (currentNode.getH()>=bestSuccessor.getH()) {
				local_best = true;
			} else {
				currentNode = bestSuccessor;
			}
		}
		return currentNode;
	}

	protected Node expand(Node node, Problem problem) {
		Node bestSuccessor = null;

		for (Operator operator : problem.getOperators()) {
			
			Node successorState = new Node(operator.apply(node.getState()));
			bestSuccessor=new Node(operator.apply(successorState.getState()));
			if (successorState.getState() != null) {
				
				successorState.setH(getEvaluationFunction().calculateH(successorState));
				
				if (successorState.getH() > bestSuccessor.getH()) {
					bestSuccessor = successorState;
					bestSuccessor.setOperator(operator.toString());
				}
			}else{
			}
		}

		return bestSuccessor;
	}

	public static void main(String[] args) {

	}

}
