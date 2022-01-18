package Dades;

public class LaberintMetodes {
    int files, columnes, casellaSF, casellaSC, casellaAF, casellaAC, posF, posC, posAuxF, posAuxC, posActF, posActC;
    String[][] valors = null, valorsAux;
    Pila p=new Pila(35);
    Paso pas=new Paso(0,0,"");
    boolean[][]wasHere;
    int puntos=0;
    
    public LaberintMetodes (int files, int columnes, int casellaSF, int casellaSC, int casellaAF, int casellaAC) {
        valors=new String[files][columnes];
        wasHere=new boolean[files][columnes];
        fillWasHere();
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
            valorActual=valorActual+operand;		//sumem
        }else
        if(valors[i][j].contains("-")) {
            num=valors[i][j].replace("-", "");
            operand=Integer.parseInt(num);
            valorActual=valorActual-operand;			//restem
        }else
            if(valors[i][j].contains("*")) {
                num=valors[i][j].replace("*", "");
                operand=Integer.parseInt(num);
                valorActual=valorActual*operand;		//multipliquem
            }else
                if(valors[i][j].contains("/")) {
                    num=valors[i][j].replace("/", "");
                    operand=Integer.parseInt(num);
                    valorActual=valorActual/operand;		//dividim
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
          valorsAux[i][j]="NA";		//trobem paret solida
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
    public void solveMaze() {
    	for (int row=0;row<files;row++)
    		for(int col=0;col<columnes;col++) {
    			wasHere[row][col]=false;
    			//correctPath[row][col]=false;
    		}
    	boolean b= recursiveSolve(casellaSF, casellaSC,0);
    }
    public void fillWasHere(){
        for(int i=0;i<files;i++){
            for(int j=0;j<columnes;j++){
                if(!usable(i,j))
                	wasHere[i][j]=true;
            }
        }
    }
    
    public boolean recursiveSolve(int x, int y,int valorAct) {
    	if (x==casellaAF && y==casellaAC) return true;
    	
    	if (x>=0&&y>=0&&x<files&&y<columnes)
    	if(valorAct<0 || wasHere[x][y]) return false;
    	
    	if (x>=0&&y>=0&&x<files&&y<columnes)
    	wasHere[x][y]=true;
    	
    	if(x!=0&&usable(x-1,y))
                System.out.println("Valor: "+valors[x][y]+", fila= "+(x)+", col= "+y);
    			valorAct=operacio(valorAct, x,y);
                System.out.println("\tAct: "+valorAct);
    		if(recursiveSolve(x-1, y, valorAct)) {
    			//correctPath[x][y] = true;
    			
    			return true;
    		}
    	if(x!= files-1&&usable(x+1,y))
                System.out.println("Valor: "+valors[x][y]+", fila= "+(x)+", col= "+y);
    			valorAct=operacio(valorAct, x,y);
    			System.out.println("\tAct: "+valorAct);
    		if(recursiveSolve(x+1, y, valorAct)) {
    			//correctPath[x][y]=true;
    			
    			return true;
    		}
    	if(y!=0&&usable(x,y-1))
                System.out.println("Valor: "+valors[x][y]+", fila= "+x+", col= "+y);
    			valorAct=operacio(valorAct, x,y);
    			System.out.println("\tAct: "+valorAct);
    		if(recursiveSolve(x, y-1,valorAct)) {
    			//correctPath[x][y]=true;
    			return true;
    		}
    	if(y!= columnes-1&&usable(x,y+1))
                System.out.println("Valor: "+valors[x][y]+", fila= "+x+", col= "+y);
    			valorAct=operacio(valorAct, x,y);
    			System.out.println("\tAct: "+valorAct);
    		if(recursiveSolve(x,y+1, valorAct)) {
    			//correctPath[x][y]=true;
    			return true;
    		}
    	return false;
    }
    public boolean utilizable(int i,int j){
        if(usable(i,j)&&(i>0&&i<5)&&(j>0&&j<7))
        	return true;
        return false;
    }
    public boolean pasoBackT ( int valorActual, int i,int j) {
    	//contador++;
    	if(valorsAux[i][j]== valors[casellaAF][casellaAC]) { //si la posicion en que estamos es la salida acabamos el bucle
    		System.out.println("Solucio trobada:");
    		p.printaPila();
    		return true;
    	}
    	if(valorsAux[i][j].equalsIgnoreCase("NA")||valorActual<=0) {
    		return false;
    	}
    	
    	//valorsAux[i][j]="P";//marcamos la posición por la que ya hemos pasado para no volver a mirarla
    	boolean resultado;
    	
    	if(j<columnes-1)
    	if(!valorsAux[i][j+1].equalsIgnoreCase("P")&&pas.getFila()!=i&&pas.getCol()!=j+1) {	//ignorem posicions visitades
    	System.out.println("Dreta"+valors[i][j]);
    	valorActual=operacio(valorActual, i, j+1);
    	p.apilaValor(new Paso(i,j,valors[i][j]));
    	valorsAux[i][j]="P";						//marquem la casella com a visitada
    	System.out.println(printaAux());
    	resultado=pasoBackT( valorActual, i, j+1);
    	 
    		
    	}
    	if(i>0)
    	if(!valorsAux[i-1][j].equalsIgnoreCase("P")&&pas.getFila()!=i-1&&pas.getCol()!=j) {
    	System.out.println("Amunt"+valors[i][j]);
    	valorActual=operacio(valorActual, i-1, j);
    	p.apilaValor(new Paso(i,j,valors[i][j]));
    	valorsAux[i][j]="P";
    	System.out.println(printaAux());
    	resultado=pasoBackT( valorActual, i-1, j);		//nos movemos hacia las 4 direcciones
    	
    	}
    	
    	if(j>0)
    	if(!valorsAux[i][j-1].equalsIgnoreCase("P")&&pas.getFila()!=i&&pas.getCol()!=j-1) {
    	System.out.println("Esquerra"+valors[i][j]);
    	valorActual=operacio(valorActual, i, j-1);
    	p.apilaValor(new Paso(i,j,valors[i][j]));
    	valorsAux[i][j]="P";
    	System.out.println(printaAux());
    	resultado=pasoBackT( valorActual, i, j-1);		// si la casilla en que estamos es la salia devolvemos true
    	
    	}
    	
    	if(i<files-1)
    	if(!valorsAux[i+1][j].equalsIgnoreCase("P")&&pas.getFila()!=i+1&&pas.getCol()!=j) {
    	System.out.println("Avall"+valors[i][j]);
    	valorActual=operacio(valorActual, i+1, j);
    	p.apilaValor(new Paso(i,j,valors[i][j]));
    	valorsAux[i][j]="P";
    	System.out.println(printaAux());
    	resultado=pasoBackT(valorActual, i+1, j);
    
    	}

    	pas=p.desapilaValor();
    	System.out.println("Fem backtraking");
    	String valorLab=p.getV();
    	i=p.getF();
    	j=p.getC();
    	valorsAux[i][j]=valorLab;
    	return false;
    }

    private boolean usable(int i,int j){
        if (i>=0&&j>=0&&i<files&&j<columnes)
        if(!valorsAux[i][j].equalsIgnoreCase("NA"))		//comprovem que la casella no és una paret
        	return true;
   
        return false;
    }
		
    public String getValue(int i,int j){		//comprovem el valor de la casella
        return valors[i][j];
    }
	public String printaPasos (String[] pasos ) {				//imprimim les consultes que hem fet
		String resultat = "";	
		for (int i = pasos.length - 1; i > 0; i--) resultat += pasos[i] + " ";	
		return resultat;	
	}
}