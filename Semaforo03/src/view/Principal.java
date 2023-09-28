package view;

import java.util.concurrent.Semaphore;

import controller.ControllerBanco;

public class Principal {

	public static void main(String[] args) {

		Semaphore semaforoSaque = new Semaphore(1);
		Semaphore semaforoDeposito = new Semaphore(1);
		
		for (int i = 0; i < 20; i++) {
			int tipo = (int)((Math.random() * 2) + 1);
			int codigo = (int)(Math.random() * 101);
			double saldo = (Math.random() * 1001);
			double valor = (Math.random() * 501);
			Thread Transacao = new ControllerBanco(semaforoSaque, semaforoDeposito, tipo, codigo, saldo, valor);
			Transacao.start();
		}
		
	}

}