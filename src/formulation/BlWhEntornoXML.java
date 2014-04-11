package formulation;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import es.deusto.ingenieria.is.search.formulation.State;
import es.deusto.ingenieria.is.search.xml.StateXMLReader;

public class BlWhEntornoXML extends StateXMLReader {

	private int size;
	public List<Cuadrado> listaCuadrados;

	public BlWhEntornoXML(String xmlFile) {
		super(xmlFile);
	}

	@Override
	public State getState() {
		BlWhEntorno e = new BlWhEntorno(size);
		e.setListaCuadrados(listaCuadrados);
		return e;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		try {
			if (qName.equals("is:lineofsquares")) {
				listaCuadrados = new ArrayList<Cuadrado>();
			} else if (qName.equals("is:white")) {
				Cuadrado blanco = new Cuadrado("B");
				this.listaCuadrados.add(blanco);
			} else if (qName.equals("is:black")) {
				Cuadrado negro = new Cuadrado("N");
				this.listaCuadrados.add(negro);
			}
		} catch (Exception ex) {
			System.out.println(this.getClass().getName() + ".startElement(): "
					+ ex);
		}

	}

	// CORRECCIÓN: La invocación al método solve() tiene que estar en una clase
	// independiente que se llame MainProgram.
	public static void main(String[] args) {

	}

}
