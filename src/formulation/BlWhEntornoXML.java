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
		for(int i=listaCuadrados.size();i<size;i++){
			Cuadrado c= new Cuadrado();
			this.listaCuadrados.add(i, c);
		}
		return e;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		try {
			if (qName.equals("is:lineofsquares")) {
				listaCuadrados = new ArrayList<Cuadrado>();
				this.size= Integer.parseInt(attributes.getValue("length"));
			} else if (qName.equals("is:white")) {
				Cuadrado blanco = new Cuadrado("B");
				this.listaCuadrados.add(blanco);
			} else if (qName.equals("is:black")) {
				Cuadrado negro = new Cuadrado("N");
				this.listaCuadrados.add(negro);
			}else{
				
			}
		} catch (Exception ex) {
			System.out.println(this.getClass().getName() + ".startElement(): "
					+ ex);
		}

	}

	// CORRECCI�N: La invocaci�n al m�todo solve() tiene que estar en una clase
	// independiente que se llame MainProgram.
	public static void main(String[] args) {

	}

}
