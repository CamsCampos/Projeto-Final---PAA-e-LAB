package par_de_pontos;

/**
 * Classe utilizada para armazenar os Dados necessarios para
 * gerar pontos
 */
public class Dados {
  public int maxPontos; // Máximo de pontos em um plano
  public int maxY; // Número máximo eixo Y
  public int maxX; // Número máximo eixo X

  public Dados(int maxPontos, int maxX, int maxY)
  {
    this.maxPontos = maxPontos;
    this.maxX = maxX;
    this.maxY = maxY;
  }
}