package Dades;

public class LaberintAvid {
    int files, columnes, casellaSF, casellaSC, casellaAF, casellaAC, posF, posC, posAuxF, posAuxC, posActF, posActC;
    String[][] valors = null, valorsAux;
    
    public LaberintAvid (int files, int columnes, int casellaSF, int casellaSC, int casellaAF, int casellaAC) {
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
    
    public int operacio(int valorActual) {
        int operand;
        String num;
        if(valors[posF][posC].contains("+")) {
            num=valors[posF][posC].replace("+", "");
            operand=Integer.parseInt(num);
            valorActual=valorActual+operand;
        }else
        if(valors[posF][posC].contains("-")) {
            num=valors[posF][posC].replace("-", "");
            operand=Integer.parseInt(num);
            valorActual=valorActual-operand;
        }else
            if(valors[posF][posC].contains("*")) {
                num=valors[posF][posC].replace("*", "");
                operand=Integer.parseInt(num);
                valorActual=valorActual*operand;
            }else
                if(valors[posF][posC].contains("/")) {
                    num=valors[posF][posC].replace("/", "");
                    operand=Integer.parseInt(num);
                    valorActual=valorActual/operand;
                }
        return valorActual;
    }
    
    public int millorCas (int valorActual) {
        int mov=0, Faux=0, Caux=0, valorAux=0, valorAux2=0;
        String oper;
        boolean movValid=true;
        while(mov<4) {
            oper=moviment(mov);
            movValid=movValid();
            if(movValid&&!valorsAux[posF][posC].equalsIgnoreCase("N")) {
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
}