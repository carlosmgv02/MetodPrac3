package Dades;

public class Pila {
	private Paso[] p;
	private int midaPila;
	private int nElems;
	
	public Pila (int midaPila) {
		p = new Paso [midaPila];
		this.midaPila = midaPila;
		this.nElems = 0;
	}
	
	public String getV() {
		return p[nElems].getValor();
	}
	public int getF() {
		return p[nElems].getFila();
	}
	public int getC() {
		return p[nElems].getCol();
	}

	public boolean apilaValor(Paso valor) {
		if (!pilaPlena()) {
			p[nElems] = valor;
			nElems++;
			return true;
		} else {
			return false;
		}
	}

	public Paso desapilaValor() {
		if (!pilaBuida()) {
			nElems--;
			return p[nElems];
		} else return null;	
	}
	 
	
	public String printaPila() {
		if (!pilaBuida()) {		
			String retorn  = "";
			for (int i=0; i< nElems; i++ )
				retorn += "|" + p[i]; 
			return retorn;
		} else return "Pila buida";
	}
	
	public boolean pilaPlena() {
		return this.midaPila == this.nElems;
	}

	public boolean pilaBuida() {
		return this.nElems == 0;
	}

	public int elementsPila() {
		return this.nElems;
	}
	
}
