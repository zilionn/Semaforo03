package controller;

import java.util.concurrent.Semaphore;

public class ControllerBanco extends Thread{
	
	private Semaphore semaforoSaque, semaforoDeposito;
	private int tipo, codigo;
	private double saldo, valor;
	
	public ControllerBanco(Semaphore semaforoSaque, Semaphore semaforoDeposito, int tipo, int codigo, double saldo, double valor) {
		this.semaforoSaque = semaforoSaque;
		this.semaforoDeposito = semaforoDeposito;
		this.tipo = tipo;
		this.codigo = codigo;
		this.saldo = saldo;
		this.valor = valor;
	}
	

	@Override
	public void run() {
		
	
		if (tipo == 1) {
			try {
				semaforoSaque.acquire();
				executarSaque();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoSaque.release();
			}
		}
		else {
			try {
				semaforoDeposito.acquire();
				executarDeposito();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoDeposito.release();
			}
		}
	}

	private void executarSaque() {
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (saldo < valor) {
			System.out.println("Saque #" + getId() + " não pôde ser executado, saldo insuficiente => Conta nº " + codigo + " Saldo: R$" + String.format("%,.2f", saldo) + " Saque: R$" + String.format("%,.2f", valor));
		} else {
			double saldoApos = saldo - valor;
			System.out.println("Saque #" + getId() + " executado => Conta nº " + codigo + " Saldo: R$" + String.format("%,.2f", saldo) + " Saque: R$" + String.format("%,.2f", valor) + " Saldo após saque: R$" + String.format("%,.2f", saldoApos));
		}

	}

	private void executarDeposito() {
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		double saldoApos = saldo + valor;
		System.out.println("Deposito #" + getId() + " executado => Conta nº " + codigo + " Saldo: R$" + String.format("%,.2f", saldo) + " Depósito: R$" + String.format("%,.2f", valor) + " Saldo após depósito: R$" + String.format("%,.2f", saldoApos));
		
	}


	
	
	
	

}