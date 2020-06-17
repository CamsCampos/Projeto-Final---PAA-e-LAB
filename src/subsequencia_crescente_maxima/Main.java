package subsequencia_crescente_maxima;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Camila Silva Campos
 *
 */
public class Main {

	static Scanner sc = new Scanner(System.in);
	static Random rd = new Random();

	public static void main(String[] args) {

		menu();

	}

	/**
	 * 
	 * @brief Menu para escolha dos algoritmos implementados
	 *
	 */
	public static void menu() {

		int opcaoMenu = 0;

		do {
			System.out.println("\n\n\n\n               =========== MENU ============\n");
			System.out.println("     | OBS: O número entre parênteses indica a quantidade \n       "
					+ "máxima de elementos em um conjunto para executar em até 5 segundos: ");
			System.out.println("\n     | Subsequência Crescente Máxima: ");
			System.out.println("     |    1 - Força bruta (75, sujeito à grandes variações) ");
			System.out.println("     |    2 - Programação dinâmica (22.000) ");

			System.out.print("\n Digite sua opção: ");
			opcaoMenu = sc.nextInt();
			System.out.print("\n");
			switch (opcaoMenu) {
			case 1:
				iniciaSubseqCresMaxima("Força Bruta");
				break;
			case 2:
				iniciaSubseqCresMaxima("Programação Dinâmica");
				break;
			default:
				System.out.println("\n  >>> Opção Inválida! <<<\n");
				break;
			}
		} while (opcaoMenu != 0);
		sc.close();
		System.out.println("\n\n FIM DO PROGRAMA! ");
	}

	/**
	 * 
	 * @brief Inicializa o algoritmo selecionado pelo usuário
	 * 
	 * @param tipoAlgoritmo: nome do algoritmo selecionado
	 *
	 */
	public static void iniciaSubseqCresMaxima(String tipoAlgoritmo) {
		int opcaoEntrada;
		long tempoGasto;
		ArrayList<Integer> conjunto = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> resultado = new ArrayList<ArrayList<Integer>>();
		boolean repetir;

		do {
			System.out.println("\n ==> Subsequência Crescente Máxima (" + tipoAlgoritmo + "): ");
			System.out.println("\n   -> Informe sua opção de entrada dos dados:");
			System.out.println("\n	1- Gerar conjunto aleatório: ");
			System.out.println("\n	2- Inserir conjunto: ");
			opcaoEntrada = sc.nextInt();

			repetir = false;
			switch (opcaoEntrada) {
			case 1:
				System.out.println("\n   -> Qual o tamanho do seu conjunto? ");
				conjunto = gerarConjunto(sc.nextInt());
				System.out.println("\n   -> Conjunto gerado: " + conjunto);

				break;
			case 2:
				conjunto = lerConjunto();
				System.out.println("\n   -> Conjunto inserido: " + conjunto);

				break;
			default:
				System.out.println("\n  >>> Opção Inválida! <<<\n");
				repetir = true;
				break;
			}
		} while (repetir);

		switch (tipoAlgoritmo) {
		case "Força Bruta":
			tempoGasto = System.currentTimeMillis();
			resultado = SubsequenciaCrescMaxima.forcaBruta(conjunto);
			System.out.println("\n   -> Tempo gasto: " + (System.currentTimeMillis() - tempoGasto) + " milissegundos");
			System.out.println("\n   ->  Resultado: " + resultado);
			break;

		case "Programação Dinâmica":
			tempoGasto = System.currentTimeMillis();
			resultado = SubsequenciaCrescMaxima.programacaoDinamica(conjunto);
			System.out.println("\n   -> Tempo gasto: " + (System.currentTimeMillis() - tempoGasto) + " milissegundos");
			System.out.println("\n   ->  Resultado: " + resultado);
			break;
		default:
			System.out.println("\n  >>> Opção Inválida! <<<\n");
			repetir = true;
		}
	}

	/**
	 * @brief Gera conjunto aleatório de inteiros
	 * 
	 * @param tamanho do conjunto
	 * 
	 * @return conjunto de inteiros gerados
	 * 
	 */
	public static ArrayList<Integer> gerarConjunto(int tamanho) {
		ArrayList<Integer> conjunto = new ArrayList<Integer>();
		for (int i = 0; i < tamanho; i++) {
			conjunto.add(rd.nextInt(100));
		}
		return conjunto;
	}

	/**
	 * @brief Lê conjunto de inteiros pelo teclado
	 * 
	 * @return conjunto de inteiros inseridos
	 * 
	 */
	public static ArrayList<Integer> lerConjunto() {
		int num = 0;
		ArrayList<Integer> sequencia = new ArrayList<Integer>();

		System.out.println(
				"\n     -> Informe uma sequência de inteiros separados por enter. Para finalizar, digite -1: ");

		while (num != -1) {
			try {
				num = sc.nextInt();
				while (num != -1) {
					sequencia.add(num);
					num = sc.nextInt();
				}

			} catch (InputMismatchException e) {
				System.out.println("Por favor, insira um inteiro...");
				num = 0;
				sc.nextLine();
			}
		}

		return sequencia;

	}
}
