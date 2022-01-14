package Aplicacio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Dades.LaberintMetodes;

public class AplicacioBack {

	 static LaberintMetodes l;
	 static int  casellaF, casellaC, valorAct=9;
	 static boolean fi=false;
	 static String print;
	
	public static void main(String[] args) throws FileNotFoundException {
		 l=carregaLaberint("Laberint.txt");
	     System.out.println(l);
	    // int casellesPos = 24;		//24 caselles amb operacions
	     //String[] pasos = new String[casellesPos+1];
	     l.resuelveBackT(casellaF, casellaC);
	    // print=l.printaPasos(pasos);
	     //System.out.println(print);
	}
	
	
	private static LaberintMetodes carregaLaberint(String nomFitxer) throws FileNotFoundException {
        Scanner entrada = new Scanner(new File(nomFitxer));
        
        
        int files=0, columnes=0, casellaSF=0, casellaSC=0,casellaAF=0, casellaAC=0;
        String linia;
        String[][] valors=null;
        
        Scanner trossejar;
        
        try {
            linia = entrada.nextLine();
            trossejar = new Scanner(linia);
            trossejar.useDelimiter(",");
            
            files=trossejar.nextInt();
            columnes=trossejar.nextInt();
            casellaAF=trossejar.nextInt();
            casellaF=casellaAF;
            casellaAC=trossejar.nextInt();
            casellaC=casellaAC;
            casellaSF=trossejar.nextInt();
           // casellaFiF=casellaSF;
            casellaSC=trossejar.nextInt();
           // casellaFiC=casellaSC;
            
            int f=0;
            valors = new String [files][columnes];
            while (f<files) {
                linia = entrada.nextLine();
                trossejar = new Scanner(linia);
                trossejar.useDelimiter(",");
                for(int c=0; c<columnes;c++) {
                    valors[f][c]=trossejar.next();
                }
                
                f++;
            }

        } catch (NoSuchElementException e) {
            entrada.close();
            System.out.println(e);
        }
        LaberintMetodes lab = new LaberintMetodes(files, columnes, casellaAF, casellaAC, casellaSF, casellaSC);
        lab.afegirLab(valors);        
        return lab;
    }
	}


