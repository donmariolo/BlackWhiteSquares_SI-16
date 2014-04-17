package formulation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.SearchMethod;
import es.deusto.ingenieria.is.search.formulation.Problem;
import es.deusto.ingenieria.is.search.formulation.State;
import es.deusto.ingenieria.is.search.xml.StateXMLReader;

public class BlWhProblem extends Problem {

	public BlWhProblem() {
		super();

		this.createOperators();
	}

	public void createOperators() {
		this.addOperator(new AvanzarUno());
		this.addOperator(new AvanzarDos());
		this.addOperator(new AvanzarCuatro());
	}

	public boolean isFinalState(State state) {
		BlWhEntorno nuevoEntorno = (BlWhEntorno) state;
		if (state != null && state instanceof BlWhEntorno) {
			if (nuevoEntorno.listaCuadrados.size() > nuevoEntorno.posicion)
				return false;
			else
				return true;
		} else
			return false;
	}

	public boolean isFullyObserved(State state) {

		BlWhEntorno entorno = (BlWhEntorno) state;
		int pos = entorno.posicion;

		// entorno.listaCuadrados.add(pos+1, new Cuadrado());

		if (entorno.getListaCuadrados().get(pos).getColor() == null) {

			return false;
		}

		else {

			return true;
		}

	}

	public State gatherPercepts(State state) {

		BlWhEntorno entorno = (BlWhEntorno) state;

		int pos = entorno.getPosicion();

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String color;
		System.out
				.print("Introduce el color del cuadrado (B/N) que se encuentra en la posicion "
						+ pos + ": ");
		color = in.next();

		Cuadrado c = new Cuadrado(color);
		entorno.listaCuadrados.set(pos, c);

		return entorno;
	}

	// Pasamos en archivo .xml con la descripcion del entorno
	public State gatherInitialPercepts() {
		StateXMLReader stateXMLReader = new BlWhEntornoXML(
				"data/blackwhitesquaresPartialpercepts1.xml");
		return stateXMLReader.getState();
	}

	public void solve(SearchMethod searchMethod) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.S");
		Date beginDate = GregorianCalendar.getInstance().getTime();
		System.out.println("\n* Start '"
				+ searchMethod.getClass().getSimpleName() + "' ("
				+ formatter.format(beginDate) + ")");
		Node finalNode = searchMethod.search(this,
				this.getInitialStates().get(0));
		Date endDate = GregorianCalendar.getInstance().getTime();
		System.out.println("* End   '"
				+ searchMethod.getClass().getSimpleName() + "' ("
				+ formatter.format(endDate) + ")");

		long miliseconds = (int) Math.abs(beginDate.getTime()
				- endDate.getTime());
		long seconds = miliseconds / 1000;
		miliseconds %= 1000;
		long minutes = seconds / 60;
		seconds %= 60;
		long hours = minutes / 60;
		minutes %= 60;

		String time = "\n* Serach lasts: ";
		time += (hours > 0) ? hours + " h " : " ";
		time += (minutes > 0) ? minutes + " m " : " ";
		time += (seconds > 0) ? seconds + "s " : " ";
		time += (miliseconds > 0) ? miliseconds + "ms " : " ";

		System.out.println(time);

		if (finalNode != null) {
			System.out.println("\n- Solution found!     :)");
			List<String> operators = new ArrayList<String>();
			searchMethod.solutionPath(finalNode, operators);
			searchMethod.createSolutionLog(operators);
			System.out.println("- Final state:\n" + finalNode.getState());
		} else {
			System.out.println("\n- Unable to find the solution!     :(");
		}

	}

	public static void main(String[] args) {

		BlWhProblem p = new BlWhProblem();

		System.out.println("Numero de operadores en la lista: "
				+ p.getOperators().size());

		BlWhEntorno entorno1 = (BlWhEntorno) p.gatherInitialPercepts();
		BlWhEntorno entorno2 = (BlWhEntorno) p.gatherInitialPercepts();

		// Le asignamos a cada uno una posicion para la prueba
		entorno1.posicion = 9;
		entorno2.posicion = 17;

		System.out.println('\n' + "---------Prueba POSICION--------");
		System.out.println("Prueba del entorno 1, de tama�o = "
				+ entorno1.listaCuadrados.size() + ". Asignamos la posicion = "
				+ entorno1.posicion);
		if (p.isFinalState(entorno1))
			System.out.println(("SI es estado final"));
		else
			System.out.println("NO es estado final");

		System.out.println('\n' + "Prueba del entorno 2, de tama�o = "
				+ entorno2.listaCuadrados.size() + ". Asignamos la posicion = "
				+ entorno2.posicion);
		if (p.isFinalState(entorno2))
			System.out.println(("SI es estado final"));
		else
			System.out.println("NO es estado final");

	}

}
