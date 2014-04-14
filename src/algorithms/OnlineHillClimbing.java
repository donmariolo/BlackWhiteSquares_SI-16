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
		boolean local_best = false;
		while(!problem.isFinalState(currentNode.getState())|| !local_best){
			
				currentNode.setH(getEvaluationFunction().calculateH(currentNode));
				currentNode.setG(getEvaluationFunction().calculateG(currentNode));
				Node bestSuccessor = expand(currentNode, problem);
				bestSuccessor.setH(this.getEvaluationFunction().calculateH(bestSuccessor));
				bestSuccessor.setG(this.getEvaluationFunction().calculateG(bestSuccessor));
				
				
				if (currentNode.compareTo(bestSuccessor) == -1 || currentNode.compareTo(bestSuccessor) == 0) {
					System.out.println("ENTRAAAAA");
					
					local_best = true;
					

					
				} else {
					currentNode = bestSuccessor;
					
					System.out.println(currentNode.getH());
				}
			}
		
		
		
		Node finalState = new Node(currentNode.getState());
		
		return finalState;
		


	}

	protected Node expand(Node node, Problem problem) {
		// TODO: BestSuccessor = CurrentNode's first successor
		Operator first=problem.getOperators().get(0);
		Node bestSuccessor = new Node(first.apply(node.getState()));
		
		for (int i = 1; i < problem.getOperators().size(); i++) {
			
			
			Operator o=problem.getOperators().get(i);
			Node successor = new Node(o.apply(node.getState()));
			
			if (successor.getState() != null){
				
				// TODO: Compute f(Successor) + if f(Successor)>f(BestSuccessor)
				successor.setH(this.getEvaluationFunction().calculateH(successor));
				successor.setG(this.getEvaluationFunction().calculateG(successor));
				bestSuccessor.setH(this.getEvaluationFunction().calculateH(bestSuccessor));
				bestSuccessor.setG(this.getEvaluationFunction().calculateG(successor));
				
				if (successor.compareTo(bestSuccessor) == -1) {
					bestSuccessor = successor;
					//bestSuccessor.setOperator(o.toString());
					
				}
			}
		
		}
		System.out.println("expand:" + bestSuccessor.toString());
		return bestSuccessor;
	}

	public static void main(String[] args) {

	}

}
