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
        posActF=casellaSF;
        posActC=casellaSC;
        posAuxF=posF;
        posAuxC=posC;
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

    public String moviment(int direccio) {
        //dreta 1, esquerra 2, adal 3, abaix 4
        switch(direccio) {
        case 0:
            posAuxC=posC;
            posC++;
            break;
        case 1:
            posAuxC=posC;
            posC--;
            break;
        case 2:
            posAuxF=posF;
            posF--;
            break;
        case 3:
            posAuxF=posF;
            posF++;
        
        }
        if(posF>=0 && posC>=0 && posF<files && posC<columnes)
        return valors[posF][posC];
        else             
            return "N";
    }
    
    public void restaurarPos (int direccio) {
        switch(direccio) {
        case 0:
            posAuxC=posC;
            posC--;
            break;
        case 1:
            posAuxC=posC;
            posC++;
            break;
        case 2:
            posAuxF=posF;
            posF++;
            break;
        case 3:
            posAuxF=posF;
            posF--;
        
        }
    }


    public boolean movValid() {
        if (posF < 0) {
            posF++;
            return false;
        } else if (posC < 0) {
            posC++;
            return false;
        } else if (posF >= files) {
            posF--;
            return false;
        } else if (posC >= columnes) {
            posC--;
            return false;
        } else if (valors[posF][posC].contains("N")) {
            posF = posAuxF;
            posC = posAuxC;
            return false;
        }
        return true;

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
    
    public int millorCasAvid (int valorActual) {
        int mov=0, Faux=0, Caux=0, valorAux=0, valorAux2=0;
        String oper;
        boolean movValid=true;
        while(mov<4) {
            oper=moviment(mov);
            movValid=movValid();
            if(valorsAux[posF][posC].contains("N")&&mov!=0) {
            	restaurarPos(mov);
            }
            if(movValid&&!valorsAux[posF][posC].contains("N")) {
            	valorsAux[3][0]="N";
                valorAux=operacio(valorActual);
                if(mov==0) {
                    valorAux2=valorAux;
                    Faux=posF;
                    Caux=posC;
                }else if(valorAux2<valorAux) {
                    valorAux2=valorAux;
                    Faux=posF;
                    Caux=posC;
                }
                restaurarPos(mov);
            }
            mov++;
        }
        posF=Faux;
        posC=Caux;
        posActF=Faux;
        posActC=Caux;
        valorsAux[posActF][posActC]="N";
        return valorAux2;
    }
    
    public boolean finalJoc(int valorActual) {
        if(valorActual<=0 || (posF==casellaAF && posC==casellaAC) )
            return true;
        else
            return false;
    }
    public void comprobar(int valorRestant){
        //cori: 0=norte, 1=sur, 2=oeste, 3=este
       	int moves=4,i=0,j=0, max=0;
       	int cori=0;
  		int mov=0;
  		int valorRestantAux;
  		
  		while(mov<moves){
              switch(mov){
                  case 0:
                  if(usable(i-1,j)){
                	valorRestantAux=valorRestant;
                	max=operacio(valorRestantAux, i-1, j);
                	
                  	//max=valors[i][j]+valors[i-1][j];
                  	cori=0;
                  }
                  	
                 
                  break;
                  case 1:
                	  valorRestantAux=valorRestant;
                  if(usable(i+1,j)&&operacio(valorRestantAux, i+1, j)>=max){
                	 
                  	  max=operacio(valorRestantAux, i+1, j);
                      //max=valors[i][j]+valors[i-1][j];
                      cori=1;
                  }
                  break;
                  case 2:
                	  valorRestantAux=valorRestant;
                  if(usable(i,j-1)&&operacio(valorRestantAux, i, j-1)>=max){
                	
                  	  max=operacio(valorRestantAux, i, j-1);
                      //max=Integer.parseInt(valors[i][j])+Integer.parseInt(valors[i][j-1]);
                      cori=2;
                  }
                  break;
                  case 3:
                	  valorRestantAux=valorRestant;
                  if(usable(i,j+1)&&operacio(valorRestantAux, i, j+1)>=max){
                	  
                  	  max=operacio(valorRestantAux, i, j+1);
                      //max=Integer.parseInt(valors[i][j])+Integer.parseInt(valors[i][j+1]);
                      cori=3;
                  }
                  break;
              }
              
              mov++;
          }
          switch(cori){
             case 0:
             i--;
             posF=i;
             break;
             case 1:
             i++;
             posF=i;
             break;
             case 2:
             j--;
             posC=j;
             break;
             case 3:
             j++;
             posC=j;
             break;
          }
       	
            
        
    }
    public boolean usable(int i,int j){
        if(!valors[i][j].equalsIgnoreCase("NA"))
        	return true;
        
        return false;
    }
		
	public boolean sresoldreBack(int valors[][], int i, int j, int sol[][])
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
    }
	public String printaPasos (String[] pasos ) {	
		String resultat = "";	
		for (int i = pasos.length - 1; i > 0; i--) resultat += pasos[i] + " ";	
		return resultat;	
	}
}