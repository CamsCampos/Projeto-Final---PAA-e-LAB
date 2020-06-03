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
			System.out.println("     | Subsequ�ncia Crescente M�xima: ");
			System.out.println("     |    1 - For�a bruta ");
			System.out.println("     |    2 - Programa��o din�mica ");
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
			opcao = sc.nextInt();
			System.out.print("\n");
			switch (opcao) {
			case 1:
				System.out.println("\n ==> Subsequ�ncia Crescente M�xima (For�a Bruta): ");
				System.out.println("\n     -> Subsequ�ncia(s) Crescente(s) M�xima(s) (For�a Bruta) = "
						+ SubsequenciaCrescMaxima.forcaBruta(leSequencia()));
				break;
			case 2:
				System.out.println("\n ==> Subsequ�ncia Crescente M�xima (Programa��o Din�mica): ");
				System.out.println("\n     -> Subsequ�ncia(s) Crescente(s) M�xima(s) (Programa��o Din�mica) = "
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
				System.out.println("Op��o Inv�lida!");
				break;
			}
		} while (opcao != 0);
		sc.close();
		System.out.println("\n\n FIM DO PROGRAMA! ");
	}

	/**
	 * @brief L� subsequ�ncia de inteiros do teclado
	 *
	 * @return Lista com a subsequ�ncia de inteiros
	 */
	public static ArrayList<Integer> leSequencia() {
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
