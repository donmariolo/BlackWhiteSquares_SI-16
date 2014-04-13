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
		//currentNode.setG(getEvaluationFunction().calculateG(currentNode));

		boolean local_best = false;
		while (!local_best) {		
			Node bestSuccessor = expand(currentNode, problem);
			
			//TODO: if f(CurrentNode)>=f(BestSuccessor)
			if (currentNode.getH() < bestSuccessor.getH()) {
				local_best = true;
			} else {
				currentNode = bestSuccessor;
			}
		}
		
		//TODO: return FinalState = CurrentNode's state
		Node finalState = new Node (currentNode.getState());
		return finalState;
	}

	protected Node expand(Node node, Problem problem) {
		//TODO: BestSuccessor = CurrentNode's first successor
		Node bestSuccessor = null;

		for (Operator operator : problem.getOperators()) {
			
			Node successor = new Node(operator.apply(node.getState()));
			bestSuccessor=new Node(operator.apply(successor.getState()));
			
			if (successor.getState() != null) {
					
				//TODO: Compute f(Successor) + if f(Successor)>f(BestSuccessor)
				successor.setH(this.getEvaluationFunction().calculateH(successor));
				successor.setG(this.getEvaluationFunction().calculateG(successor));
				
				bestSuccessor.setH(this.getEvaluationFunction().calculateH(bestSuccessor));
				bestSuccessor.setG(this.getEvaluationFunction().calculateG(successor)+1);
				
				
				if (successor.getH() > bestSuccessor.getH()) {
					bestSuccessor = successor;
					bestSuccessor.setOperator(operator.toString());
				}
			}
		}

		return bestSuccessor;
	}

	public static void main(String[] args) {

	}

}
