package formulation;

import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.heuristic.EvaluationFunction;

public class BlWhFuncionEvaluacion extends EvaluationFunction {

	@Override
	// TODO CORRECC�N: El valor de G es igual al coste real de las acciones
	// ejecutadas. La f�rmula general ser�a G(N) = G(Padre) +
	// CosteAcci�n(Padre->N)
	public double calculateG(Node nodo) {
		BlWhEntorno state = (BlWhEntorno) nodo.getState();
		int resultado = 0;

		resultado = (state.posicion + 1) / 4;

		return resultado;

	}

	@Override
	// TODO CORRECCI�N: A la hora de devolver el resultado, habr�a que realizar
	// un redondeo de la divisi�n ya que seg�n la t�cnica del problema relajado,
	// el n�mero de acciones siempre tiene que ser un n�mero entero.
	public double calculateH(Node nodo) {
		BlWhEntorno state = (BlWhEntorno) nodo.getState();

		int queda = state.listaCuadrados.size() - (state.posicion + 1);
		int resultado = queda / 4;

		return (resultado + 1);

	}

}
