package Aplicacio;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Dades.LaberintAvid;
public class AplicacioAvid {
	
	static LaberintAvid l;
	static int  casellaF, casellaC, mov, valorAct=9;
	static String casella=new String();
	static boolean fi=false;
	
	
	public static void main(String[] args) throws FileNotFoundException {
		l=carregaLaberint("Laberint.txt");
		System.out.println(l);
		while(!fi) {
			mov = (int) (Math.random()*4);
			casella=l.moviment(mov, casellaF, casellaC);
			System.out.println(casella);
			if(!casella.contains("N")) {
				valorAct=l.operacio(valorAct, casellaF, casellaC);
				System.out.println(valorAct);
			}
			fi=l.finalJoc(valorAct, casellaF, casellaC);
		}
	}
	
	private static LaberintAvid carregaLaberint(String nomFitxer) throws FileNotFoundException {
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
			casellaF = casellaSF;
			casellaSC=trossejar.nextInt();
			casellaC=casellaSF;
			
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
		LaberintAvid lab = new LaberintAvid(files, columnes, casellaAF, casellaAC, casellaSF, casellaSC);
		lab.afegirLab(valors);		
		return lab;
	}
}
