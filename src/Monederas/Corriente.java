package Monederas;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import Firma.GeneradorDeClaves;
import Moneda.ZinCoin;
import Operaciones.Transaccion;
import javafx.util.Pair;

public class Corriente extends Cuenta{

	private String privateKey;
	private String nombre;
	private ZinCoin sistema;
	private GeneradorDeClaves generador;
	private List<Pair<Transaccion,Boolean>> transacciones;
	
	public Corriente() {	
	}
	
	public Corriente(String claveP,String nombre) {
		this.privateKey = claveP;
		this.nombre = nombre;
		generarClaves();
		this.sistema.addUser(this);
		this.transacciones = new ArrayList<>();
	}
	
	public String getNombre(){
		String user = this.nombre;
		return user;
	}
	
	public String getClave() {
		String clave = this.privateKey;
		return clave;
	}
	
	public PublicKey getClavePublica() {
		KeyPair keys = this.generador.getClaves(this.privateKey);
		PublicKey k = keys.getPublic();
		return k;
	}
	
	public void generarClaves() {
		try {
			this.generador = new GeneradorDeClaves();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		this.generador.generarClave(this.privateKey);		
	}
	
	public void transferir(String usuario,double cantidad,double propina) {
		Transaccion nueva = new Transaccion();
		nueva.setPropina(propina);
		
		String operacion = this.nombre+"-"+usuario+"-"+Double.toString(cantidad);
		KeyPair keys = this.generador.getClaves(this.privateKey);
		nueva.firmar(keys.getPrivate(),operacion);
		
		this.sistema.entregarTransaccion(nueva);
	}
	
	public void setTransaccion(boolean correcto, Transaccion t) {
		Pair<Transaccion,Boolean> p = new Pair<>(t, correcto);
		this.transacciones.add(p);
	}
	
}
