package Dades;

public class Paso {
	
	private int fila, columna;
	private String valor;
	public Paso(int fila,int columna,String valor){
		this.fila=fila;
		this.columna=columna;
		this.valor=valor;
		
	}
	public int getFila(){
		return fila;
	}
	public int getCol(){
		return columna;
	}
	public String getValor() {
		return valor;
	}
	public void setFila(int fila){
		this.fila=fila;
	}
	public void setCol(int col){
		this.columna=col;
	}
	
	public void setValue(String value){
		valor=value;
	}
	
}
