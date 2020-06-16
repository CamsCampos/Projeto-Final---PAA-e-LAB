package par_de_pontos;

import java.util.ArrayList;
import java.awt.Point;

/**
 * Algoritimo utilizando força bruta para calcular
 * o par de pontos mais proximos
 */
public class ForcaBruta {
  private ArrayList<Point> pontos; // Todos os pontos
  private ArrayList<ArrayList<Point>> pontosProximos; // Registra os pontos mais proximos
  private ArrayList<Point> parDePontos; // Registra os pontos que formam pares

  public long tempo; // Registra o tempo total
  public int max5; // Registra o máximo de pontos em 5 segundos
  
  /**
   * Inicia todos as listas e parametros
   */
  public ForcaBruta(ArrayList<Point> pontos)
  {
    this.pontos = pontos;
    this.pontosProximos = new ArrayList<ArrayList<Point>>();
    this.parDePontos = new ArrayList<Point>();
    max5 = 0;
  }

  /**
   * Inicia o algoritimo
   */
  public ArrayList<ArrayList<Point>> comeca()
  {
    // Armazena o tempo inicial
    long tempoInicial = System.currentTimeMillis();

    // Pontos auxiliares
    Point ponto1 = pontos.get(0);
    Point ponto2 = pontos.get(1);

    // Armazena a menor distancia durante o calculo
    double menorDistanciaAux;
    // Inicializa a menor distancia entre os dois pontos
    double menorDistancia = distanciaEntrePontos(
      ponto1.getX(), ponto1.getY(), // X e Y do ponto 1
      ponto2.getX(), ponto2.getY() // X e Y do ponto 2
    );

    // Percorre todos os pontos
    while (!pontos.isEmpty())
    {
      // Registra o máximo de pontos que podem ser calculados em 5 segundos
      if ((System.currentTimeMillis() - tempoInicial) / 1000 == 5)
        max5 = pontosProximos.size();

      ponto1 = pontos.get(0);

      // Percore todos os demais pontos
      for (int i = 1;i < pontos.size();i++)
      {
        // Ponto a ser calculado
        ponto2 = pontos.get(i);

        // Registra como menor distancia
        menorDistanciaAux = distanciaEntrePontos(
          ponto1.getX(), ponto1.getY(), // X e Y do ponto 1
          ponto2.getX(), ponto2.getY() // X e Y do ponto 2
        );

        // Verifica se são iguals
        if (menorDistanciaAux == menorDistancia)
          adicionaParPontos(ponto1, ponto2);

        // Verifica se o ponto que esta sendo calculado tem a distancia
        // menor do que a última registrada
        if (menorDistanciaAux < menorDistancia)
        {
          menorDistancia = menorDistanciaAux;
          pontosProximos.clear();
          adicionaParPontos(ponto1, ponto2);
        }
      }
      pontos.remove(0);
    }
    // Registra o tempo total do algoritimo
    tempo = System.currentTimeMillis() - tempoInicial;
    return pontosProximos;
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
  
  /**
   * Adiciona o par de pontos a lista
   * @param p1 Ponto número 1
   * @param p2 Ponto número 2
   */
  private void adicionaParPontos(Point p1, Point p2)
  {
		pontosProximos.add(new ArrayList<Point>());

		if (!pontosProximos.isEmpty())
			parDePontos = pontosProximos.get(pontosProximos.size() - 1);
		else
			parDePontos = pontosProximos.get(0);

		parDePontos.add(p1);
		parDePontos.add(p2);
  }
}