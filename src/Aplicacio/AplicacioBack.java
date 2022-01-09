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
	
	public static void main(String[] args) throws FileNotFoundException {
		 l=carregaLaberint("Laberint.txt");
	     System.out.println(l);
	     int casellesPos = 24;//24 caselles amb operacions
	}
	
	public static int funcioBackT (int profArbre) {
		if(fi) { return 1;}
		
		for (int i=0; i<35;i++) {//35 num caselles lab
			
			funcioBackT(--profArbre);
			//Retorn d'un cas BASE
			System.out.println("Fem Backtraking");	
			profArbre++;
			fi=l.finalJoc(valorAct);
		}
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
	            casellaAC=trossejar.nextInt();
	            casellaSF=trossejar.nextInt();
	            casellaSC=trossejar.nextInt();
	            
	            
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


