import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		menu();

	}

	/**
	 * @brief Menu
	 */
	public static void menu() {

		int opcao = 0;
		do {
			System.out.println("\n\n\n\n               =========== MENU ============\n");
			System.out.println("     | Subsequência Crescente Máxima: ");
			System.out.println("     |    1 - Força bruta ");
			System.out.println("     |    2 - Programação dinâmica ");
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

			System.out.print("\n Digite sua opção: ");
			opcao = sc.nextInt();
			System.out.print("\n");
			switch (opcao) {
			case 1:
				System.out.println("\n ==> Subsequência Crescente Máxima (Força Bruta): ");
				System.out.println("\n     -> Subsequência(s) Crescente(s) Máxima(s) (Força Bruta) = "
						+ SubsequenciaCrescMaxima.forcaBruta(leSequencia()));
				break;
			case 2:
				System.out.println("\n ==> Subsequência Crescente Máxima (Programação Dinâmica): ");
				System.out.println("\n     -> Subsequência(s) Crescente(s) Máxima(s) (Programação Dinâmica) = "
						+ SubsequenciaCrescMaxima.programacaoDinamica(leSequencia()));
				break;
			case 3:
				System.out.println("\n ==> (Nome do seu algoritmo): ");
				break;
			case 4:
				System.out.println("\n ==> (Nome do seu algoritmo): ");
				break;
			case 0:
				break;
			default:
				System.out.println("Opção Inválida!");
				break;
			}
		} while (opcao != 0);
		sc.close();
		System.out.println("\n\n FIM DO PROGRAMA! ");
	}

	/**
	 * @brief Lê subsequência de inteiros do teclado
	 *
	 * @return Lista com a subsequência de inteiros
	 */
	public static ArrayList<Integer> leSequencia() {
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
