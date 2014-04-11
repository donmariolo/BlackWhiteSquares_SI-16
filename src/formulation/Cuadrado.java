package formulation;

public class Cuadrado {

	private String color;

	public Cuadrado() {
		super();
	}

	public Cuadrado(String color) {
		super();
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean equals(Object object) {
		if (object != null && object instanceof Cuadrado) {
			Cuadrado cuadradoAux = (Cuadrado) object;
			if (this.getColor().equals(cuadradoAux.getColor()))
				return true;
			else
				return false;
		} else {
			return false;
		}
	}
	
	public Cuadrado clone() {
		Cuadrado cuadrado = new Cuadrado(this.color);

		return cuadrado;
	}


	public static void main(String[] args) {
		// Creamos dos cuadrados blancos
		Cuadrado a = new Cuadrado("B");
		Cuadrado b = new Cuadrado("B");

		System.out
				.println("Prueba del metodo equals con dos cuadrados diferentes. Uno de ellos de color: "
						+ a.color + " y el otro: " + b.color);
		if (a.equals(b)) {
			System.out.println("Los dos cuadrados son iguales");
		} else
			System.out.println("Son de diferente color");

		// Creamos otro cuadrado, pero esta vez, negro
		Cuadrado c = new Cuadrado("N");
		System.out
				.println('\n'
						+ "Prueba del metodo equals con dos cuadrados diferentes. Uno de ellos de color: "
						+ b.color + " y el otro: " + c.color);
		if (c.equals(b)) {
			System.out.println("Los dos cuadrados son iguales");
		} else
			System.out.println("Son de diferente color");

	}

}
