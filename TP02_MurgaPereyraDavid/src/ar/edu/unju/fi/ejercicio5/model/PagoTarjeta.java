package ar.edu.unju.fi.ejercicio5.model;
import ar.edu.unju.fi.ejercicio5.interfaces.*;
import java.time.*; 


public class PagoTarjeta implements Pago{
	private String  nro_tarjeta;
	private LocalDate fecha_de_pago ;
	private double monto_pagado ;
	
	
	public PagoTarjeta(String nro_tarjeta, LocalDate fecha_de_pago, double monto_pagado) {
		this.nro_tarjeta = nro_tarjeta;
		this.fecha_de_pago = fecha_de_pago;
		this.monto_pagado = monto_pagado;
	}
	@Override
	public void realizarPago(double monto) {
		monto_pagado=monto*1.15;	
	}
	
	public void  imprimirRecibo() {
		System.out.println( "\n ******* FACTURA DE PAGO ******* \n nro_tarjeta=" + nro_tarjeta +  ",fecha_de_pago=" + fecha_de_pago + ", monto_pagado="
				+ monto_pagado );}
	
	// GETERS AND SETTERS 
	public String toString() {
		return "PagoTarjeta [nro_tarjeta=" + nro_tarjeta + ", fecha_de_pago=" + fecha_de_pago + ", monto_pagado="
				+ monto_pagado + "]";
	}
	public String getNro_tarjeta() {
		return nro_tarjeta;
	}
	public void setNro_tarjeta(String nro_tarjeta) {
		this.nro_tarjeta = nro_tarjeta;
	}
	public LocalDate getFecha_de_pago() {
		return fecha_de_pago;
	}
	public void setFecha_de_pago(LocalDate fecha_de_pago) {
		this.fecha_de_pago = fecha_de_pago;
	}
	public double getMonto_pagado() {
		return monto_pagado;
	}
	public void setMonto_pagado(double monto_pagado) {
		this.monto_pagado = monto_pagado;
	}

}