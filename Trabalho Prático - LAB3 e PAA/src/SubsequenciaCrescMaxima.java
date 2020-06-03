import java.util.ArrayList;

/**
 * @brief Resolve o problema de Subsequ�ncia Crescente M�xima
 *
 * @author Camila Silva Campos
 */
public class SubsequenciaCrescMaxima {

	/**
	 * @brief Subsequ�ncia crescrente m�xima com programa��o din�mica
	 *
	 * @param seq Sequ�ncia de inteiros.
	 *
	 * @return Lista com a subsequ�ncia crescente m�xima
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<ArrayList<Integer>> programacaoDinamica(ArrayList<Integer> seq) {

		ArrayList<ArrayList<Integer>> tabela = new ArrayList<ArrayList<Integer>>();
		int posMelhorCandidato = -1;
		tabela.add(null);

		// Percorre a sequ�ncia de n�meros
		for (int i = 0; i < seq.size(); i++) {

			// Percorre a tabela din�mica do fim para o �nicio
			for (int j = tabela.size() - 1; j >= 0; j--) {

				// Verifica se � uma sequ�ncia v�lida e compara com a melhor encontrada at� o
				// momento
				if (tabela.get(j) != null && tabela.get(j).get(tabela.get(j).size() - 1) < seq.get(i)) {
					if (posMelhorCandidato == -1)
						posMelhorCandidato = j;
					else if (tabela.get(j).size() > tabela.get(posMelhorCandidato).size())
						posMelhorCandidato = j;
				}
			}

			// Adiciona o n�mero na melhor sequ�ncia encontrada
			if (posMelhorCandidato != -1) {
				tabela.add((ArrayList<Integer>) tabela.get(posMelhorCandidato).clone());
				tabela.get(tabela.size() - 1).add(seq.get(i));
			} else {
				// N�o h� sequ�ncia para o n�mero. Ele � adicionado sozinho � tabela
				tabela.add(new ArrayList<Integer>());
				tabela.get(tabela.size() - 1).add(seq.get(i));
			}

			posMelhorCandidato = -1; // Zera a posi��o do melhor candidato

		}

		int tamMelhorSequencia = 0;
		ArrayList<ArrayList<Integer>> subMaxima = new ArrayList<ArrayList<Integer>>();

		// Procura na tabela din�mica finalizada a(s) maior(es) subsequ�ncia(s) e guarda
		// em uma lista
		for (int i = 1; i < tabela.size(); i++) {
			if (tabela.get(i).size() > tamMelhorSequencia) {
				tamMelhorSequencia = tabela.get(i).size();
				subMaxima.clear();
				subMaxima.add(tabela.get(i));
			} else if (tabela.get(i).size() == tamMelhorSequencia) {
				subMaxima.add(tabela.get(i));
			}
		}
		return subMaxima;
	}

	/**
	 * @brief Subsequ�ncia crescrente m�xima com for�a bruta
	 *
	 * @param seq Sequ�ncia de inteiros.
	 *
	 * @return Lista com a(s) subsequ�ncia(s) crescente(s) m�xima(s)
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<ArrayList<Integer>> forcaBruta(ArrayList<Integer> seq) {

		ArrayList<ArrayList<Integer>> subconjuntos = new ArrayList<ArrayList<Integer>>();
		subconjuntos.add(new ArrayList<Integer>());

		// Gera todos os subconjuntos da sequencia de inteiros
		while (!seq.isEmpty()) {
			int tamSubConj = subconjuntos.size();
			for (int i = 0; i < tamSubConj; i++) {

				// Adiciona os subconjuntos crescentes � lista "subconjuntos"
				if (i == 0) {
					subconjuntos.add((ArrayList<Integer>) subconjuntos.get(i).clone());
					subconjuntos.get(subconjuntos.size() - 1).add(seq.get(0));
				} else if (seq.get(0) > subconjuntos.get(i).get(subconjuntos.get(i).size() - 1)) {
					subconjuntos.add((ArrayList<Integer>) subconjuntos.get(i).clone());
					subconjuntos.get(subconjuntos.size() - 1).add(seq.get(0));
				}
			}
			seq.remove(0);
		}

		int tamMelhorSequencia = 0;
		ArrayList<ArrayList<Integer>> subMaxima = new ArrayList<ArrayList<Integer>>();

		// Busca a(s) subsequ�ncia(s) m�xima(s)
		for (int i = 1; i < subconjuntos.size(); i++) {
			if (subconjuntos.get(i).size() > tamMelhorSequencia) {
				tamMelhorSequencia = subconjuntos.get(i).size();
				subMaxima.clear();
				subMaxima.add(subconjuntos.get(i));
			} else if (subconjuntos.get(i).size() == tamMelhorSequencia) {
				subMaxima.add(subconjuntos.get(i));
			}
		}

		return subMaxima;
	}

}
