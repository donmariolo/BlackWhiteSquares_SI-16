package formulation;

import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.heuristic.EvaluationFunction;

public class BlWhFuncionEvaluacion extends EvaluationFunction {

	@Override
	// TODO CORRECCÓN: El valor de G es igual al coste real de las acciones
	// ejecutadas. La fórmula general sería G(N) = G(Padre) +
	// CosteAcción(Padre->N)
	public double calculateG(Node nodo) {
		BlWhEntorno state = (BlWhEntorno) nodo.getState();
		int resultado = 0;

		resultado = (state.posicion + 1) / 4;

		return resultado;

	}

	@Override
	// TODO CORRECCIÓN: A la hora de devolver el resultado, habría que realizar
	// un redondeo de la división ya que según la técnica del problema relajado,
	// el número de acciones siempre tiene que ser un número entero.
	public double calculateH(Node nodo) {
		BlWhEntorno state = (BlWhEntorno) nodo.getState();

		int queda = state.listaCuadrados.size() - (state.posicion + 1);
		int resultado = queda / 4;

		return (resultado + 1);

	}

}
