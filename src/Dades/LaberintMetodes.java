package Dades;

public class LaberintMetodes {
    int files, columnes, casellaSF, casellaSC, casellaAF, casellaAC, posF, posC, posAuxF, posAuxC, posActF, posActC;
    String[][] valors = null, valorsAux;
    Pila p=new Pila(35);
    
    public LaberintMetodes (int files, int columnes, int casellaSF, int casellaSC, int casellaAF, int casellaAC) {
        valors=new String[files][columnes];
        this.files=files;
        this.columnes=columnes;
        this.casellaSF=casellaSF;
        this.casellaSC=casellaSC;
        this.casellaAF=casellaAF;
        this.casellaAC=casellaAC;
        posF=casellaSF;
        posC=casellaSC;
        valorsAux=new String[files][columnes];

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
            return devolver;
    }
    public String printaAux(){
        String devolver=new String();
        int i=0,j=0;
        for(i=0;i<files;i++){
            for(j=0;j<columnes;j++){
                devolver=devolver + " "+valorsAux[i][j];
            }
            devolver=devolver+"\n";
                
        }        
            return devolver;
    }
    public void afegirLab (String[][] valors) {
        for(int i=0; i<files;i++)
            for(int j=0;j<columnes;j++) {
                this.valors[i][j]=valors[i][j];
                valorsAux[i][j]=valors[i][j];
            }
    }

    public int actualitzacioF () {
        return posF;
    }
    public int actualitzacioC () {
        return posC;
    }
    
    public int operacio(int valorActual, int i, int j) {
        int operand=0;
        String num="";
        if(valors[i][j].contains("+")) {
            num=valors[i][j].replace("+", "");
            operand=Integer.parseInt(num);
            valorActual=valorActual+operand;
        }else
        if(valors[i][j].contains("-")) {
            num=valors[i][j].replace("-", "");
            operand=Integer.parseInt(num);
            valorActual=valorActual-operand;
        }else
            if(valors[i][j].contains("*")) {
                num=valors[i][j].replace("*", "");
                operand=Integer.parseInt(num);
                valorActual=valorActual*operand;
            }else
                if(valors[i][j].contains("/")) {
                    num=valors[i][j].replace("/", "");
                    operand=Integer.parseInt(num);
                    valorActual=valorActual/operand;
                }
        return valorActual;
    }

    
    public boolean finalJoc(int valorActual, int i, int j) {
        if(valorActual<=0 || (i==casellaAF && j==casellaAC) )
            return true;
        else
            return false;
    }
    public int comprobar(int valorRestant, int casellaF, int casellaC){
        //cori: 0=norte, 1=sur, 2=oeste, 3=este
       	int moves=4,i=casellaF,j=casellaC, max=0;
       	int cori=0;
  		int mov=0;
  		int valorRestantAux;
  		
  		while(mov<moves){
              switch(mov){
                  case 0:
                  if(i>0)
                  if(usable(i-1,j)){
                	valorRestantAux=valorRestant;
                	max=operacio(valorRestantAux, i-1, j);
                	
                  	//max=valors[i][j]+valors[i-1][j];
                  	cori=0;
                  }
                  	
                  break;
                  case 1:
                	  valorRestantAux=valorRestant;
                	  if(i<files-1)
                  if(usable(i+1,j)&&operacio(valorRestantAux, i+1, j)>=max){
                  	  max=operacio(valorRestantAux, i+1, j);
                      //max=valors[i][j]+valors[i-1][j];
                      cori=1;
                  }
                  break;
                  case 2:
                	  valorRestantAux=valorRestant;
                	  if(j>0)
                  if(usable(i,j-1)&&operacio(valorRestantAux, i, j-1)>=max){
                	
                  	  max=operacio(valorRestantAux, i, j-1);
                      //max=Integer.parseInt(valors[i][j])+Integer.parseInt(valors[i][j-1]);
                      cori=2;
                  }
                  break;
                  case 3:
                	  valorRestantAux=valorRestant;
                if(j<columnes-1)
                  if(usable(i,j+1)&&operacio(valorRestantAux, i, j+1)>=max){
                	  
                  	  max=operacio(valorRestantAux, i, j+1);
                      //max=Integer.parseInt(valors[i][j])+Integer.parseInt(valors[i][j+1]);
                      cori=3;
                  }
                  break;
              }
              
              mov++;
          }
          valorsAux[i][j]="NA";
          switch(cori){
             case 0:
             posF=i-1;
             break;
             case 1:
             posF=i+1;
             break;
             case 2:
             
             posC=j-1;
             break;
             case 3:
             
             posC=j+1;
             break;
          }
       	
            return max;
        
    }
    
    public boolean pasoBackT (int contador, int valorActual, int i,int j) {
    	//contador++;
    	if(valorsAux[i][j]== valors[casellaAF][casellaAC]) { //si la posicion en que estamos es la salida acabamos el bucle
    		return true;
    	}
    	if(valorsAux[i][j].equalsIgnoreCase("NA")||valorActual<=0) {
    		return false;
    	}
    	
    	//valorsAux[i][j]="P";//marcamos la posición por la que ya hemos pasado para no volver a mirarla
    	boolean resultado;
    	
    	if(i>=0&&j>=0&&i<files-1&&j<columnes-1) {
    		
    	if(!valorsAux[i][j+1].equalsIgnoreCase("P")) {
    	valorActual=operacio(valorActual, i, j+1);
    	System.out.println(valors[i][j]);
    	p.apilaValor(new Paso(i,j,valors[i][j]));
    	valorsAux[i][j]="P";
    	System.out.println(printaAux());
    	resultado=pasoBackT(--contador, valorActual, i, j+1);
    	if (resultado&&valorActual>0) return true;
    	}
    	
    	if(!valorsAux[i-1][j].equalsIgnoreCase("P")) {
    	valorActual=operacio(valorActual, i-1, j);
    	System.out.println("2:"+valors[i][j]);
    	p.apilaValor(new Paso(i,j,valors[i][j]));
    	valorsAux[i][j]="P";
    	System.out.println(printaAux());
    	resultado=pasoBackT(--contador, valorActual, i-1, j);		//nos movemos hacia las 4 direcciones
    	if (resultado&&valorActual>0) return true;			// y comparamos el resultado con la salida,
    	}
    	
    	if(!valorsAux[i][j-1].equalsIgnoreCase("P")) {
    	valorActual=operacio(valorActual, i, j-1);
    	System.out.println("3:"+valors[i][j]);
    	p.apilaValor(new Paso(i,j,valors[i][j]));
    	valorsAux[i][j]="P";
    	System.out.println(printaAux());
    	resultado=pasoBackT(--contador, valorActual, i, j-1);		// si la casilla en que estamos es la salia devolvemos true
    	if (resultado&&valorActual>0) return true;			// sino retornamos falso
    	}
    	
    	if(!valorsAux[i+1][j].equalsIgnoreCase("P")) {
    	valorActual=operacio(valorActual, i+1, j);
    	System.out.println("4:"+valors[i][j]);
    	p.apilaValor(new Paso(i,j,valors[i][j]));
    	valorsAux[i][j]="P";
    	System.out.println(printaAux());
    	resultado=pasoBackT(--contador, valorActual, i+1, j);
    	if (resultado&&valorActual>0) return true;
    	}
    }
    	p.desapilaValor();
    	i=p.getF();
    	j=p.getC();
    	++contador;
    	return false;
    }
   /* public void resuelveBackT (int contador, int i, int j) {
    	if(pasoBackT(contador, 9,i, j)) {
    		valors[i][j] = valors[casellaSF][casellaSC]; aquí faltaria poner las coordenadas de la entrada
    		
    	}
    }*/
    
    private boolean usable(int i,int j){
        if(!valorsAux[i][j].equalsIgnoreCase("NA"))
        	return true;
   
        return false;
    }
		
    public String getValue(int i,int j){
        return valors[i][j];
    }
	public String printaPasos (String[] pasos ) {	
		String resultat = "";	
		for (int i = pasos.length - 1; i > 0; i--) resultat += pasos[i] + " ";	
		return resultat;	
	}
}