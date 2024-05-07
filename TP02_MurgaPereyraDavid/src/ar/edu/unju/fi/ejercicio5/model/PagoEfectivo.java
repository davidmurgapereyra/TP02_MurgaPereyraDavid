package ar.edu.unju.fi.ejercicio5.model;
import java.time.*;
import ar.edu.unju.fi.ejercicio5.interfaces.*;

public class PagoEfectivo implements Pago {
	private double monto_pagado;
	private LocalDate fecha_de_pago;
	
	
	public PagoEfectivo(double monto_pagado, LocalDate fecha_de_pago) {
		this.monto_pagado = monto_pagado;
		this.fecha_de_pago = fecha_de_pago;
	}
	public double getMonto_pagado() {
		return monto_pagado;
	}
	public void setMonto_pagado(double monto_pagado) {
		this.monto_pagado = monto_pagado;
	}
	public LocalDate getFecha_de_pago() {
		return fecha_de_pago;
	}
	public void setFecha_de_pago(LocalDate fecha_de_pago) {
		this.fecha_de_pago = fecha_de_pago;
	}
	
	@Override
	public void realizarPago(double monto) {
		monto_pagado=monto*0.90;	
	}
	
	public void  imprimirRecibo() {
		System.out.println("\n ******* FACTURA DE PAGO ******* \n fecha_de_pago=" + fecha_de_pago + ", monto_pagado="
				+ monto_pagado );}
	
	
	public String toString() {
		return "PagoEfectivo [monto_pagado=" + monto_pagado + ", fecha_de_pago=" + fecha_de_pago + "]";
	}
	
	
}