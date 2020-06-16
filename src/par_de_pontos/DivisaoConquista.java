package par_de_pontos;

import java.util.ArrayList;
import java.awt.Point;

/**
 * Algoritimo utilizando divisão e conquista para calcular
 * o par de pontos mais proximos
 */
public class DivisaoConquista {
  private ArrayList<Point> pontos; // Todos os pontos
  private Point pontoProximo; // Ponto mais proximo

  public long tempo; // Tempo total gasto
  public int max5; // Maximo de pontos processados em 5 segundos

  /**
   * Inicia todos as listas e parametros
   */
  public DivisaoConquista(ArrayList<Point> pontos)
  {
    this.pontos = pontos;
    this.pontoProximo = new Point();
    max5 = 0;
  }

  /**
   * Inicia o algoritimo
   */
  public Point comeca()
  {
    // Armazena o tempo inicial
    long tempoInicial = System.currentTimeMillis();

    // Oderna os pontos
    ordenarPontos(pontos, 0, pontos.size() - 1);

    // Divide os lados
    ArrayList<Point> planoEsquerdo = new ArrayList<>();
    ArrayList<Point> planoDireito = new ArrayList<>();
    int metade = pontos.size() / 2;
    planoEsquerdo.addAll(pontos.subList(0, metade));
    planoDireito.addAll(pontos.subList(metade, pontos.size()));

    // Processa os dois lados e calcula a menor distancia 
    double d = menor(processaLado(planoEsquerdo), processaLado(planoDireito));

    // Divide os lados com o limite da menor distancia
    ArrayList<Point> pontosDEsquerdo = new ArrayList<>();
    ArrayList<Point> pontosDDireito = new ArrayList<>();
    pontosDEsquerdo.addAll(pontos.subList(metade, metade + (int) d));
    pontosDDireito.addAll(pontos.subList(metade - (int) d, metade));

    // Processa os dois lados
    processaLado(pontosDEsquerdo);
    processaLado(pontosDDireito);

    // Registra o tempo total do algoritimo
    tempo = System.currentTimeMillis() - tempoInicial;
    return pontoProximo;
  }

  /**
   * Ordena a lista de pontos
   * @param pontos A lista de pontos para ordenar
   * @param inicio Inicio da lista
   * @param fim Fim da lista
   */
  private void ordenarPontos(ArrayList<Point> pontos, int inicio, int fim) {
		if (inicio < fim) {
			int posicaoPivo = separar(pontos, inicio, fim);
			ordenarPontos(pontos, inicio, posicaoPivo - 1);
			ordenarPontos(pontos, posicaoPivo + 1, fim);
		}
	}

  /**
   * Separa a lista de pontos
   * @param pontos A lista de pontos para separar
   * @param inicio Inicio da lista
   * @param fim Fim da lista
   * @return a posição da lista
   */
	private int separar(ArrayList<Point> pontos, int inicio, int fim) {
		Point pivo = pontos.get(inicio);
		int i = inicio + 1, f = fim;
		while (i <= f) {
			if (pontos.get(i).getX() <= pivo.getX())
				i++;
			else if (pivo.getX() < pontos.get(f).getX())
				f--;
			else {
				Point troca = pontos.get(i);
				pontos.set(i, pontos.get(f));

				pontos.set(f, troca);
				i++;
				f--;
			}
		}

		pontos.set(inicio, pontos.get(f));
		pontos.set(f, pivo);
		return f;
	}

  /**
   * Calcula o ponto com menor distancia
   * @param ponto1 Ponto 1 a ser calculado
   * @param ponto2 Ponto 2 a ser calculado
   * @return o ponto com menor distancia
   */
  private double menor(double ponto1, double ponto2)
  {
    if (ponto1 > ponto2)
      return ponto2;
    else
      return ponto1;
  }

  /**
   * Processa os pontos de um lado do plano
   * @param pontos Todos os pontos de um lado do plano
   * @return A menor distancia encontrada
   */
  private double processaLado(ArrayList<Point> pontos)
  {
    long tempoInicial = System.currentTimeMillis();
    double menorDistancia = 0;
    if (pontos.size() > 1)
    {
      Point ponto1 = pontos.get(0);
      Point ponto2 = pontos.get(1);

      // Inicializa a menor distancia entre os dois pontos
      menorDistancia = distanciaEntrePontos(
        ponto1.getX(), ponto1.getY(), // X e Y do ponto 1
        ponto2.getX(), ponto2.getY() // X e Y do ponto 2
      );

      // Armazena a menor distancia durante o calculo
      double menorDistanciaAux;
      while (!pontos.isEmpty())
      {
        // Registra o máximo de pontos que podem ser calculados em 5 segundos
        if ((System.currentTimeMillis() - tempoInicial) / 1000 < 5)
          max5++;
        ponto1 = pontos.get(0);

        for (int i = 1;i < pontos.size();i++)
        {
          // Ponto a ser calculado
          ponto2 = pontos.get(i);

          // Registra como menor distancia
          menorDistanciaAux = distanciaEntrePontos(
            ponto1.getX(), ponto1.getY(), // X e Y do ponto 1
            ponto2.getX(), ponto2.getY() // X e Y do ponto 2
          );
          // Verifica se o ponto que esta sendo calculado tem a distancia
          // menor do que a última registrada
          if (menorDistanciaAux < menorDistancia)
          {
            menorDistancia = menorDistanciaAux;
            this.pontoProximo = ponto1;
          }
        }

        pontos.remove(0);
      }
    }

    return menorDistancia;
  }

  /**
   * Calcula a distancia entre os pontos
   * @param x1 X do ponto número 1
   * @param y1 Y do ponto número 1
   * @param x2 X do ponto número 2
   * @param y2 Y do ponto número 2
   * @return A distancia entre os pontos
   */
  private double distanciaEntrePontos(double x1, double y1, double x2, double y2) {
		return (Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2))));
  }
}