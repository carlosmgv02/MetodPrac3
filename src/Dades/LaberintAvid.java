package Dades;

public class LaberintAvid {
	int files, columnes, casellaSF, casellaSC, casellaAF, casellaAC;
	String[][] valors = null;
	
	public LaberintAvid (int files, int columnes, int casellaSF, int casellaSC, int casellaAF, int casellaAC) {
		valors=new String[files][columnes];
		this.files=files;
		this.columnes=columnes;
		this.casellaSF=casellaSF;
		this.casellaSC=casellaSC;
		this.casellaAF=casellaAF;
		this.casellaAC=casellaAC;
		//this.valors=valors;
	}
	
	public String toString() {
		String devolver=new String();
		int i=0,j=0;
		for(i=0;i<files;i++){
			for(j=0;j<columnes;j++){
				devolver=devolver + " "+valors[i][j];
			}
			devolver=devolver+"\n";
				
		}		
		/*return "Laberint [files=" + files + ", columnes=" + columnes + ", casellaSF=" + casellaSF + ", casellaSC="
				+ casellaSC + ", casellaAF=" + casellaAF + ", casellaAC=" + casellaAC + ", valors="
				+ Arrays.toString(valors) + "]";*/
			return devolver;
	}
	public void afegirLab (String[][] valors) {
		for(int i=0; i<files;i++)
			for(int j=0;j<columnes;j++) {
				this.valors[i][j]=valors[i][j];
				
			}
	}

	public String moviment(int direccio, int posicioF, int posicioC) {
		//dreta 1, esquerra 2, adal 3, abaix 4
		switch(direccio) {
		case 1:
			posicioC++;
			return valors[posicioF][posicioC];
		case 2:
			posicioC--;
			return valors[posicioF][posicioC];
		case 3:
			posicioF++;
			return valors[posicioF][posicioC];
		case 4:
			posicioF--;
			return valors[posicioF][posicioC];
		
		}
		return valors[posicioF][posicioC];
	}
	
	public int operacio(int valorActual, int posicioF, int posicioC) {
		int operand;
		if(valors[posicioF][posicioC].contains("+")) {
			valors[posicioF][posicioC].replace("+", "");
			operand=Integer.parseInt(valors[posicioF][posicioC]);
			valorActual+=operand;
		}else
		if(valors[posicioF][posicioC].contains("-")) {
			valors[posicioF][posicioC].replace("-", "");
			operand=Integer.parseInt(valors[posicioF][posicioC]);
			valorActual-=operand;
		}else
			if(valors[posicioF][posicioC].contains("*")) {
				valors[posicioF][posicioC].replace("*", "");
				operand=Integer.parseInt(valors[posicioF][posicioC]);
				valorActual*=operand;
			}else
				if(valors[posicioF][posicioC].contains("/")) {
					valors[posicioF][posicioC].replace("/", "");
					operand=Integer.parseInt(valors[posicioF][posicioC]);
					valorActual/=operand;
				}
		return valorActual;
	}
	
	public boolean finalJoc(int valorActual, int posicioF, int posicioC) {
		if(valorActual<=0 || (posicioF==casellaAF && posicioC==casellaAC) )
			return true;
		else
			return false;
	}
}
