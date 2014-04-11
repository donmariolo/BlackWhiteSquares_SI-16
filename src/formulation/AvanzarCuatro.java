package formulation;

import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.State;

public class AvanzarCuatro extends Operator {

	public AvanzarCuatro() {
		super();
		this.setName("AvanzarCuatro");

	}

	@Override
	protected State effect(State state) {
		BlWhEntorno nuevoEntorno = (BlWhEntorno) ((BlWhEntorno) state).clone();
		int pos = nuevoEntorno.getPosicion();
		nuevoEntorno.setPosicion(pos + 4);
		// this.setName("AvanzarCuatro");

		return nuevoEntorno;
	}

	@Override
	protected boolean isApplicable(State state) {
		// solo se puede avanzar 4 si estamos en un cuadrado NEGRO
		BlWhEntorno nuevoEntorno = (BlWhEntorno) ((BlWhEntorno) state);
		int pos = nuevoEntorno.posicion;

		if (nuevoEntorno.listaCuadrados.get(pos).getColor().equals("N"))

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
		System.out.println(entorno.toString());
		AvanzarCuatro avance = new AvanzarCuatro();
		System.out.println('\n' + "--------Prueba de avanzar CUATRO--------");
		if (avance.isApplicable(entorno)) {
			System.out.println("Posicion anterior: " + entorno.posicion);
			BlWhEntorno entorno1 = (BlWhEntorno) avance.effect(entorno);
			System.out.println("La posicion despues de avanzar CUATRO es: "
					+ entorno1.posicion);
			System.out.println("\nEntorno: " + entorno1.toString());

		} else {
			System.out
					.println("No se puede avanzar CUATRO porque en la posicion "
							+ entorno.posicion
							+ ", el cuadrado es de color "
							+ entorno.getListaCuadrados().get(entorno.posicion)
									.getColor());

		}

	}

}
