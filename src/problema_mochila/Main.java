package problema_mochila;

import java.util.Scanner;
import java.util.Set;

import problema_mochila.partes.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
        
		System.out.println("\nBem-Vindo ao Problema da Mochila");
		
		System.out.println("\nVamos definir as caracteristicas da sua mochila:\n1-\t\tPeso\n2-\t\tValor\n3-\t\tCapacidade Maxima\n");
		
		System.out.println("Quantas mochilas gostaria de adicionar?\n->");
		int numeroMochilas = entrada.nextInt(); entrada.nextLine();
		
		System.out.println("Qual a capacidade da sua mochila ?\n-> ");
		int capacidade = entrada.nextInt(); entrada.nextLine();
		
		Mochila z=new Mochila(capacidade);
		
		for(int i=0; i>numeroMochilas; i++) {
			
			System.out.println("Qual o nome da sua mochila?\n->");
			String nomeMochila = entrada.nextLine(); entrada.nextLine();
			
			System.out.println("Qual o valor da sua mochila ?\n-> ");
			int valor = entrada.nextInt(); entrada.nextLine();
			
			System.out.println("Qual o peso da sua mochila ?\n-> ");
			int peso = entrada.nextInt(); entrada.nextLine();
			
			z.add(new Parte(peso, valor, nomeMochila));
			}
		
		

		
		System.out.println("Com qual metodo gostaria de resolver o Problema da Mochila ?\n1-Força Bruta\n2-Algoritmo Guloso\n3-Programação Dinamica\n0-Sair\nEscolha-> ");
		
		int menuSelector = entrada.nextInt(); entrada.nextLine();
		
		switch(menuSelector) {
			case 1:
				if ( args.length != 1 ) {
		            System.err.println("Especificar o input");
		            System.exit(1);
		        }
				int[] p = {2, 7, 6, 4};
		        double[] v = {12.7, 6.4, 1.7, 0.3};
		        ForcaBruta f = new ForcaBruta(p, v, 10);
		        long tempoInicial1 = System.currentTimeMillis();
		        f.resolve();
		        f.imprimaSolucao();
		        long tempo1 = System.currentTimeMillis();
		        System.out.println("\nRealizada no seguinte tempo:"+tempo1);
				break;
			case 2:
				
				break;
			case 3:
				long tempoInicial3 = System.currentTimeMillis();
				Set<Parte> solucao=z.resolveProgramacaoDinamica();
				long tempo3 = System.currentTimeMillis();
				
				System.out.println(solucao+"\nRealizada no seguinte tempo:"+tempo3);
				break;
				
		}
		}
	}
