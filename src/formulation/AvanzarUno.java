package formulation;

import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.State;

public class AvanzarUno extends Operator {

	public AvanzarUno() {
		super();
		this.setName("AvanzarUno");

	}

	@Override
	protected State effect(State state) {
		BlWhEntorno nuevoEntorno = (BlWhEntorno) ((BlWhEntorno) state).clone();
		int pos = nuevoEntorno.posicion;
		nuevoEntorno.setPosicion(pos + 1);
		this.setName("AvanzarUno");

		return nuevoEntorno;
	}

	@Override
	protected boolean isApplicable(State state) {
		// siempre puedes avanzar uno, est�s en el cuadrado en el que est�s, por
		// eso no es necesario verificar si es blanco o negro
		return true;
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

		AvanzarUno avance = new AvanzarUno();
		System.out.println('\n' + "--------Prueba de avanzar UNO--------");
		System.out.println("La posicion actual en el entorno es: "
				+ entorno.posicion);
		BlWhEntorno entorno1 = (BlWhEntorno) avance.effect(entorno);
		System.out.println("La posici�n despues de avanzar UNO es: "
				+ entorno1.posicion);
		System.out.println("\nEntorno: " + entorno1.toString());

	}

}
