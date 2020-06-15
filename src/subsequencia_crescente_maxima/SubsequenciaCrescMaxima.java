package subsequencia_crescente_maxima;

import java.util.ArrayList;

/**
 * @brief Resolve o problema de Subsequência Crescente Máxima
 *
 * @author Camila Silva Campos
 * 
 */
public class SubsequenciaCrescMaxima {

	/**
	 * @brief Subsequência crescrente máxima com força bruta
	 *
	 * @param seq Sequência de inteiros.
	 *
	 * @return Lista com a(s) subsequência(s) crescente(s) máxima(s)
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<ArrayList<Integer>> forcaBruta(ArrayList<Integer> seq) {

		ArrayList<ArrayList<Integer>> subconjuntos = new ArrayList<ArrayList<Integer>>();
		subconjuntos.add(new ArrayList<Integer>());

		// Gera todos os subconjuntos da sequência de inteiros
		while (!seq.isEmpty()) {
			int tamSubConj = subconjuntos.size();
			for (int i = 0; i < tamSubConj; i++) {

				// Verificação para adicionar somente sequências crescentes à lista
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

		// Busca a(s) subsequência(s) máxima(s) e adiciona à lista "subMax"
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
	 * @brief Subsequência crescrente máxima com programação dinâmica
	 *
	 * @param seq Sequência de inteiros.
	 *
	 * @return Lista com a subsequência crescente máxima
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<ArrayList<Integer>> programacaoDinamica(ArrayList<Integer> seq) {

		ArrayList<ArrayList<Integer>> tabela = new ArrayList<ArrayList<Integer>>();
		int posMelhorCandidato = -1;
		tabela.add(null);

		// Percorre os elementos da sequência informada
		for (int i = 0; i < seq.size(); i++) {

			// Percorre a tabela dinâmica do fim para o ínicio
			for (int j = tabela.size() - 1; j >= 0; j--) {

				// Verifica se é a sequência da posição atual da tabela é válida para adicionar
				// o elemento atual
				if (tabela.get(j) != null && tabela.get(j).get(tabela.get(j).size() - 1) < seq.get(i)) {

					// Atualiza a variável, se necessário, da posição do melhor candidato até o
					// momento para o elemento atual
					if ((posMelhorCandidato == -1) || (tabela.get(j).size() > tabela.get(posMelhorCandidato).size())) {
						posMelhorCandidato = j;
					}
				}
			}

			// Adiciona o elemento atual na melhor sequência encontrada
			if (posMelhorCandidato != -1) {
				tabela.add((ArrayList<Integer>) tabela.get(posMelhorCandidato).clone());
				tabela.get(tabela.size() - 1).add(seq.get(i));
			} else {
				// Não há sequência para o elemento atual. Ele é adicionado sozinho à tabela
				tabela.add(new ArrayList<Integer>());
				tabela.get(tabela.size() - 1).add(seq.get(i));
			}

			posMelhorCandidato = -1; // Zera a posição do melhor candidato para reiniciar a tabela com o próximo
										// elemento

		}

		int tamMelhorSequencia = 0;
		ArrayList<ArrayList<Integer>> subMaxima = new ArrayList<ArrayList<Integer>>();

		// Procura na tabela dinâmica finalizada a(s) maior(es) subsequência(s) e guarda
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
