package Dades;

public class LaberintMetodes {
    int files, columnes, casellaSF, casellaSC, casellaAF, casellaAC, posF, posC, posAuxF, posAuxC, posActF, posActC;
    String[][] valors = null, valorsAux;
    
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
    public boolean usable(int i,int j){
        if(!valorsAux[i][j].equalsIgnoreCase("NA"))
        	return true;
        
        return false;
    }
		
	/*public boolean sresoldreBack(int valors[][], int i, int j, int sol[][])
    {
        // si x, y es el final == true
        if (x == N - 1 && y == N - 1) {
            sol[x][y] = 1;
            return true;
        }
 
        // comprovem si es valid
        if (usable(i, j) == true) {
            sol[i][j] = 1;
 
            //ens movem endavant en direcció de les X
            if (sresoldreBack(valors, i + 1, j, sol))
                return true;
 
            //si al moure en la direcció de les x no funciona ens movem en direccio de les y
            if (sresoldreBack(valors, i, j + 1, sol))
                return true;
 
            //en el cas de que cap de les solucions funcioni apliquem el backtracking i desmarquem la posició
            sol[i][j] = 0;
            return false;
        }
 
        return false;
    }*/
    public String getValue(int i,int j){
        return valors[i][j];
    }
	public String printaPasos (String[] pasos ) {	
		String resultat = "";	
		for (int i = pasos.length - 1; i > 0; i--) resultat += pasos[i] + " ";	
		return resultat;	
	}
}