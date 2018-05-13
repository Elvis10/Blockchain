package Moneda;

import java.util.ArrayList;
import java.util.List;

import Operaciones.*;
import javafx.util.Pair;

public class CasaCambio {

	private List<Pair<String, Pair<String,Cambio>>> intercambios;
	
	public CasaCambio() {
		this.intercambios = new ArrayList<>();
	}
	
	public void cambiar(String tokenOrigen,String token,Cambio c){
		Pair<String,Cambio> par = new Pair(token,c);
		Pair<String,Pair<String,Cambio>> par2 = new Pair(tokenOrigen,par);
		this.intercambios.add(par2);
	}
	
	public Cambio buscarIntercambio(String token,double minimo) {
		Cambio c = null;
		
		for(Pair<String,Pair<String,Cambio>> par : this.intercambios){
			if(par.getKey().equals(token)) {
				Pair<String,Cambio> var = par.getValue(); 
				if(var.getValue().obtenerCantidad() >= minimo) {
					return var.getValue();
				}
			}
		}
		return c;
	}	
	
	public void aceptarIntercambio() {
		Transaccion t
	}
	
}
