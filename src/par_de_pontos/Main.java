package par_de_pontos;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.awt.Point;

// TODO
// a) o tempo necessário para resolver cada instância do problema proposto
// b) o tamanho do maior conjunto possível com solução fornecida no tempo máximo de 5 segundos
public class Main {
  private static Scanner scanner;
  private static Random random;
  
  /**
   * Algoritimo par de pontos mais proximo
   * @param args
   */
  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    random = new Random();

    System.out.println("--- ALGORITIMO PAR DE PONTOS MAIS PROXIMO ---");
    forcaBruta();   
    DivisaoConquista(); 

    scanner.close();
  }

  /**
   * Inicia o algoritimo de força bruta
   */
  private static void forcaBruta()
  {
    System.out.println("Utilizando força bruta");
    Dados dadosBruta = pegarDados();
    ArrayList<Point> listPontosBruta = gerarDados(dadosBruta);
    ForcaBruta forcaBruta = new ForcaBruta(listPontosBruta);
    ArrayList<ArrayList<Point>> resultadoBruta = forcaBruta.comeca();
    
    System.out.println("Resultado:");
    System.out.println(resultadoBruta);
    System.out.printf("Tempo: %dms (%d segundos)\n", forcaBruta.tempo, forcaBruta.tempo / 1000);
    System.out.printf("Conjuntos possíveis com tempo máximo de 5 segundos: %d\n", forcaBruta.max5);
  }

  /**
   * Inicia o algoritimo de Divisao e Conquista
   */
  private static void DivisaoConquista()
  {
    System.out.println("Utilizando divisão e conquista");
    Dados dados = pegarDados();
    ArrayList<Point> listPontos = gerarDados(dados);
    DivisaoConquista divisao = new DivisaoConquista(listPontos);
    Point resultado = divisao.comeca();

    System.out.println("Resultado:");
    System.out.println(resultado);
    System.out.printf("Tempo: %dms (%d segundos)\n", divisao.tempo, divisao.tempo / 1000);
    System.out.printf("Conjuntos possíveis com tempo máximo de 5 segundos: %d\n", divisao.max5);
  }
  /**
   * Pegar os dados necessarios para o algoritimo x
   * Utiliza a classe Scanner para ler a entrada do usuario
   * @return A classe Dados com o máximo de pontos no plano, o máximo para a coordenada X e Y
   */
  private static Dados pegarDados()
  {
    int maxPontos, maxX, maxY;
    
    System.out.printf("Digite o número de pontos no plano:");
    maxPontos = scanner.nextInt();
    System.out.println("Digite o valor máximo para a coordenada:");
    System.out.printf("X:");
    maxX = scanner.nextInt();
    System.out.printf("Y:");
    maxY = scanner.nextInt();

    return new Dados(maxPontos, maxX, maxY);
  }

  /**
   * Gerar os pontos necessarios para o algoritimo x
   * Utiliza a classe Random para gerar pontos aleatorios
   * @return Uma lista de pontos randomicos
   */
  private static ArrayList<Point> gerarDados(Dados dados)
  {
    ArrayList<Point> pontos = new ArrayList<>();
    
    for (int i = 0;i < dados.maxPontos;i++)
    {
      int x = (random.nextInt(dados.maxX + 1)) - (random.nextInt(dados.maxX + 1));
      int y = (random.nextInt(dados.maxY + 1)) - (random.nextInt(dados.maxY + 1));
      pontos.add(new Point(x, y));
    }
    return pontos;
  }
}