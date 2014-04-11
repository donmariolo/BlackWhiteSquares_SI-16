package formulation;

import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.State;

public class AvanzarDos extends Operator {


	public AvanzarDos() {
		super();
		this.setName("AvanzarDos");
	}

	@Override
	protected State effect(State state) {
		BlWhEntorno nuevoEntorno = (BlWhEntorno) ((BlWhEntorno) state).clone();
		int pos = nuevoEntorno.getPosicion();
		nuevoEntorno.setPosicion(pos + 2);
		// this.setName("AvanzarDos");

		return nuevoEntorno;
	}

	@Override
	protected boolean isApplicable(State state) {
		// solo se puede avanzar 2 si estamos en un cuadrado BLANCO
		BlWhEntorno nuevoEntorno = (BlWhEntorno) ((BlWhEntorno) state);
		int pos = nuevoEntorno.posicion;

		// Comprobacion, si el cuadrado de esa posicion es blanco se puede
		// hacer, si no, FALSE
		if (nuevoEntorno.listaCuadrados.get(pos).getColor().equals("B"))
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		BlWhEntorno entorno = new BlWhEntorno(10);
		entorno.posicion = 3;

		for (int i = 0; i < entorno.listaCuadrados.size(); i++) {
			String aChar[] = { "B", "N" };
			Cuadrado c = new Cuadrado(
					aChar[(int) Math.floor((Math.random() * 2))]);
			entorno.listaCuadrados.set(i, c);

		}
		System.out.println("Entorno: " + entorno.toString());

		// NOTA: las posiciones van de la 0 a la N-1, siendo N el tamaño del
		// entorno
		AvanzarDos avance = new AvanzarDos();
		System.out.println('\n' + "--------Prueba de avanzar DOS--------");
		if (avance.isApplicable(entorno)) {
			System.out.println("Posicion anterior: " + entorno.posicion);
			BlWhEntorno entorno1 = (BlWhEntorno) avance.effect(entorno);
			System.out.println("La posicion despues de avanzar DOS es: "
					+ entorno1.posicion);
			System.out.println("\nEntorno: " + entorno1.toString());
		} else {
			System.out.println("No se puede avanzar DOS porque en la posicion "
					+ entorno.posicion
					+ ", el cuadrado es de color "
					+ entorno.getListaCuadrados().get(entorno.posicion)
							.getColor());
		}

	}

}
