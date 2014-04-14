package formulation;

import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.heuristic.EvaluationFunction;

public class BlWhFuncionEvaluacion extends EvaluationFunction {

	@Override
	public double calculateG(Node nodo) {
		BlWhEntorno state = (BlWhEntorno) nodo.getState();
		int resultado = 0;

		resultado = (state.posicion + 1) / 4;

		return (resultado);

	}

	@Override
	public double calculateH(Node nodo) {
		BlWhEntorno state = (BlWhEntorno) nodo.getState();

		int queda = state.listaCuadrados.size() - (state.posicion + 1);
		int resultado = queda / 4;

		return (resultado + 1);

	}

}
