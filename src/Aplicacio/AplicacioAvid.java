package Aplicacio;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Dades.LaberintMetodes;
public class AplicacioAvid {
    
    static LaberintMetodes l;
    static int  casellaF, casellaC, mov, valorAct=9;
    static String casella=new String();
    static boolean fi=false, movV=true;
    static Scanner teclat = new Scanner(System.in);
    
    public static void main(String[] args) throws FileNotFoundException {
        l=carregaLaberint("Laberint.txt");
        System.out.println(l);
        while(!fi) {
                valorAct=l.millorCasAvid(valorAct);
                casellaF=l.actualitzacioF();
                casellaC=l.actualitzacioC();
                System.out.println("fila: "+casellaF+"columna:"+casellaC);
                System.out.println("punts que queden: "+valorAct);
            fi=l.finalJoc(valorAct);
        }
        if(casellaF==1&&casellaC==6)
            System.out.println("has guanyat");
        else
            System.out.println("has perdut");
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