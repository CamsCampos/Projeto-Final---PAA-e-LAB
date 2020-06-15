package subsequencia_crescente_maxima;

import java.util.ArrayList;

/**
 * @brief Resolve o problema de Subsequ�ncia Crescente M�xima
 *
 * @author Camila Silva Campos
 * 
 */
public class SubsequenciaCrescMaxima {

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

		// Gera todos os subconjuntos da sequ�ncia de inteiros
		while (!seq.isEmpty()) {
			int tamSubConj = subconjuntos.size();
			for (int i = 0; i < tamSubConj; i++) {

				// Verifica��o para adicionar somente sequ�ncias crescentes � lista
				// "subconjuntos"
				if ((i == 0) || (seq.get(0) > subconjuntos.get(i).get(subconjuntos.get(i).size() - 1))) {
					subconjuntos.add((ArrayList<Integer>) subconjuntos.get(i).clone());
					subconjuntos.get(subconjuntos.size() - 1).add(seq.get(0));
				}
			}
			seq.remove(0);
		}

		int tamMelhorSequencia = 0;
		ArrayList<ArrayList<Integer>> subMaxima = new ArrayList<ArrayList<Integer>>();

		// Busca a(s) subsequ�ncia(s) m�xima(s) e adiciona � lista "subMax"
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

		// Percorre os elementos da sequ�ncia informada
		for (int i = 0; i < seq.size(); i++) {

			// Percorre a tabela din�mica do fim para o �nicio
			for (int j = tabela.size() - 1; j >= 0; j--) {

				// Verifica se � a sequ�ncia da posi��o atual da tabela � v�lida para adicionar
				// o elemento atual
				if (tabela.get(j) != null && tabela.get(j).get(tabela.get(j).size() - 1) < seq.get(i)) {

					// Atualiza a vari�vel, se necess�rio, da posi��o do melhor candidato at� o
					// momento para o elemento atual
					if ((posMelhorCandidato == -1) || (tabela.get(j).size() > tabela.get(posMelhorCandidato).size())) {
						posMelhorCandidato = j;
					}
				}
			}

			// Adiciona o elemento atual na melhor sequ�ncia encontrada
			if (posMelhorCandidato != -1) {
				tabela.add((ArrayList<Integer>) tabela.get(posMelhorCandidato).clone());
				tabela.get(tabela.size() - 1).add(seq.get(i));
			} else {
				// N�o h� sequ�ncia para o elemento atual. Ele � adicionado sozinho � tabela
				tabela.add(new ArrayList<Integer>());
				tabela.get(tabela.size() - 1).add(seq.get(i));
			}

			posMelhorCandidato = -1; // Zera a posi��o do melhor candidato para reiniciar a tabela com o pr�ximo
										// elemento

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

}
