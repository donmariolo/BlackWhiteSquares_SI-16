package formulation;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.is.search.formulation.State;

public class BlWhEntorno extends State {

	public List<Cuadrado> listaCuadrados;
	public int posicion;

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public int getPosicion() {
		return posicion;
	}

	public BlWhEntorno(int size) {
		this.listaCuadrados = new ArrayList<Cuadrado>();
		for (int i = 0; i < size; i++) {
			Cuadrado c = new Cuadrado();
			listaCuadrados.add(c);
		}
	}

	public List<Cuadrado> getListaCuadrados() {
		return this.listaCuadrados;
	}

	public void setListaCuadrados(List<Cuadrado> listaCuadrados) {
		this.listaCuadrados = listaCuadrados;
	}

	public BlWhEntorno clone() {
		BlWhEntorno nuevoEntorno = new BlWhEntorno(this.getListaCuadrados()
				.size());

		nuevoEntorno.listaCuadrados = this.listaCuadrados;

		nuevoEntorno.posicion = this.posicion;
		// Cuadrado c = new Cuadrado();
		// int size = nuevoEntorno.getListaCuadrados().size();
		// for (int i = 0; i < size; i++) {
		// c = this.listaCuadrados.get(i);
		// nuevoEntorno.listaCuadrados.set(i, c);
		// }

		return nuevoEntorno;
	}

	@Override
	public boolean equals(Object object) {
		if (object != null && object instanceof BlWhEntorno) {
			BlWhEntorno nuevoEntorno = (BlWhEntorno) object;

			if (this.posicion == nuevoEntorno.posicion)
				return true;
			else
				return false;

		} else
			return false;
	}

	@Override
	public String toString() {
		String cadena = "";
		for (int i = 0; i < listaCuadrados.size(); i++) {
			if (this.posicion == i) {
				cadena = cadena + "[ " + listaCuadrados.get(i).getColor()
						+ " ]^  ";
			} else {
				cadena = cadena + "[ " + listaCuadrados.get(i).getColor()
						+ " ]  ";
			}
		}
		return cadena;
	}

	public static void main(String[] args) {
		BlWhEntorno entorno = new BlWhEntorno(10);
		BlWhEntorno entornoAux = new BlWhEntorno(10);

		for (int i = 0; i < entorno.listaCuadrados.size(); i++) {
			String aChar[] = { "B", "N" };
			Cuadrado c = new Cuadrado(
					aChar[(int) Math.floor((Math.random() * 2))]);
			entorno.listaCuadrados.set(i, c);
			entornoAux.listaCuadrados.set(i, c);
		}

		System.out.println('\n' + "------------EQUALS-------------");
		entorno.posicion = 5;
		System.out.println(entorno.toString());
		entornoAux.posicion = 3;
		System.out.println("¿Coincide la posicion?");
		if (entorno.equals(entornoAux))
			System.out.println("Sí, la posición del entorno 1 es "
					+ entorno.posicion + " y la del entorno 2 tambien es "
					+ entornoAux.posicion);
		else
			System.out.println("No, la posición del entorno 1 es "
					+ entorno.posicion + " y la del entorno 2 es "
					+ entornoAux.posicion);

		System.out.println('\n' + "------------EQUALS-------------");
		entorno.posicion = 7;
		entornoAux.posicion = 4;
		System.out.println("¿Coincide la posicion?");
		if (entorno.equals(entornoAux))
			System.out.println("Sí, la posición del entorno 1 es "
					+ entorno.posicion + " y la del entorno 2 tambien es "
					+ entornoAux.posicion);
		else
			System.out.println("No, la posición del entorno 1 es "
					+ entorno.posicion + " y la del entorno 2 es "
					+ entornoAux.posicion);

		System.out.println('\n' + "------------CLONE-------------");
		BlWhEntorno entornoClonado = entorno.clone();

		System.out.println("Entorno clonado: " + entornoClonado.toString());

	}

}
