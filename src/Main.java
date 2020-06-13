import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Camila Campos, ... (coloque seu nome)
 *
 */
public class Main {

	static Scanner sc = new Scanner(System.in);
	static Random rd = new Random();

	public static void main(String[] args) {

		menu();

	}

	/**
	 * @brief Menu
	 */
	public static void menu() {

		int opcaoMenu = 0;

		do {
			System.out.println("\n\n\n\n               =========== MENU ============\n");
			System.out.println("     | OBS: O n�mero entre par�nteses indica a quantidade \n       "
					+ "m�xima de elementos em um conjunto para executar em at� 5 segundos: ");
			System.out.println("\n     | Subsequ�ncia Crescente M�xima: ");
			System.out.println("     |    1 - For�a bruta (75, sujeito � grandes varia��es) ");
			System.out.println("     |    2 - Programa��o din�mica (25.000) ");
			System.out.println("\n     | (Nome do seu algoritmo): ");
			System.out.println("     |    3 - XXX ");
			System.out.println("     |    4 - YYY ");
			System.out.println("\n     | (Nome do seu algoritmo): ");
			System.out.println("     |    5 - XXX ");
			System.out.println("     |    6 - YYY ");
			System.out.println("\n     | (Nome do seu algoritmo): ");
			System.out.println("     |    7 - XXX ");
			System.out.println("     |    8 - YYY ");
			System.out.println("\n     | 0 - Sair ");

			System.out.print("\n Digite sua op��o: ");
			opcaoMenu = sc.nextInt();
			System.out.print("\n");
			switch (opcaoMenu) {
			case 1:
				iniciaSubseqCresM�xima("For�a Bruta");
				break;
			case 2:
				iniciaSubseqCresM�xima("Programa��o Din�mica");

				break;
			case 3:
				System.out.println("\n ==> (Nome do seu algoritmo): ");
				break;
			case 4:
				System.out.println("\n ==> (Nome do seu algoritmo): ");
				break;
			case 5:
				System.out.println("\n ==> (Nome do seu algoritmo): ");
				break;
			case 6:
				System.out.println("\n ==> (Nome do seu algoritmo): ");
				break;
			case 7:
				System.out.println("\n ==> (Nome do seu algoritmo): ");
				break;
			case 8:
				System.out.println("\n ==> (Nome do seu algoritmo): ");
				break;
			case 0:
				break;
			default:
				System.out.println("\n  >>> Op��o Inv�lida! <<<\n");
				break;
			}
		} while (opcaoMenu != 0);
		sc.close();
		System.out.println("\n\n FIM DO PROGRAMA! ");
	}

	public static void iniciaSubseqCresM�xima(String tipoAlgoritmo) {
		int opcaoEntrada;
		long tempoGasto;
		ArrayList<Integer> conjunto = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> resultado = new ArrayList<ArrayList<Integer>>();
		boolean repetir;

		do {
			System.out.println("\n ==> Subsequ�ncia Crescente M�xima (" + tipoAlgoritmo + "): ");
			System.out.println("\n   -> Informe sua op��o de entrada dos dados:");
			System.out.println("\n	1- Gerar conjunto aleat�rio: ");
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
				System.out.println("\n  >>> Op��o Inv�lida! <<<\n");
				repetir = true;
				break;
			}
		} while (repetir);

		switch (tipoAlgoritmo) {
		case "For�a Bruta":
			tempoGasto = System.currentTimeMillis();
			resultado = SubsequenciaCrescMaxima.forcaBruta(conjunto);
			System.out.println("\n   -> Tempo gasto = " + (System.currentTimeMillis() - tempoGasto) + " milissegundos");
			System.out.println("   ->  Resultado: " + resultado);
			break;

		case "Programa��o Din�mica":
			tempoGasto = System.currentTimeMillis();
			resultado = SubsequenciaCrescMaxima.programacaoDinamica(conjunto);
			System.out.println("\n   -> Tempo gasto = " + (System.currentTimeMillis() - tempoGasto) + " milissegundos");
			System.out.println("   ->  Resultado: " + resultado);
			break;
		default:
			System.out.println("\n  >>> Op��o Inv�lida! <<<\n");
			repetir = true;
		}
	}

	public static ArrayList<Integer> gerarConjunto(int tamanho) {
		ArrayList<Integer> conjunto = new ArrayList<Integer>();
		for (int i = 0; i < tamanho; i++) {
			conjunto.add(rd.nextInt(100));
		}
		return conjunto;
	}

	public static ArrayList<Integer> lerConjunto() {
		int num = 0;
		ArrayList<Integer> sequencia = new ArrayList<Integer>();

		System.out.println(
				"\n     -> Informe uma sequ�ncia de inteiros separados por enter. Para finalizar, digite -1: ");

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
