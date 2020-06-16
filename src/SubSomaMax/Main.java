package SubSomaMax;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @brief Resolve o problema de Subsequência de Soma Máxima
 *
 * @author Belle Nerissa Aguiar Elizeu
 *
 */

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Random rd = new Random();
    public static void main(String[] args) {

       menu();

    }

    /**
     *
     * @brief Menu para escolha dos algoritmos implementados,
     * @brief identificar o limite de elementos da sequência
     * @brief impressão das respostas dos algoritmos
     *
     *
     */
    public static void menu(){
        int opcaoMenu = 0;

        do {
            System.out.println("\n\n              =========== MENU ============\n");
            System.out.println("     | OBS: O número entre parênteses indica a quantidade \n       "
                    + "máxima de elementos em um conjunto para executar em até 5 segundos: ");
            System.out.println("\n     | Subsequência de Soma Máxima: ");
            System.out.println("     |    1 - Força bruta (média de 3.700, sujeito à grandes variações) ");
            System.out.println("     |    2 - Divisão e Conquista (135.000.000)");

            System.out.print("\n Digite sua opção: ");
            opcaoMenu = sc.nextInt();
            System.out.print("\n");
            switch (opcaoMenu) {
                case 1:
                    int respostaFB = Integer.MIN_VALUE;
                    List<Integer> listaMaxSum = new ArrayList<Integer>();
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Qual será o limite de elementos do array?");
                    //limiteArray é o valor maximo de elementos do array analizado
                    int limiteArray = scanner.nextInt();
                    //-chamada do gerador
                    Main.gerarListaMaxSum(listaMaxSum,limiteArray);

                    //captura de tempo inicio
                    System.out.println("\n Inicio do algoritmo de Força Bruta ...." );
                    long tempoInicioFB = System.currentTimeMillis();
                    respostaFB = Main.SubsequenciaSomaMaxFB(listaMaxSum,limiteArray,respostaFB);
                    //captura de tempo fim
                    long tempoDecorridoFB = (System.currentTimeMillis() - tempoInicioFB)/1000;

                    //Respostas do Algoritmo de força bruta
                    System.out.println("\n ===== ALGORITMO DE FORÇA BRUTA (Subsequência de Soma Máxima) ====");
                    System.out.println(" Soma máxima da sequência: " + respostaFB);
                    System.out.println(" T = O(N)^3");
                    System.out.println(" O algoritmo faz O(n)^3 comparações, sendo três laços por iteração");
                    System.out.println(" Tempo decorrido: " + tempoDecorridoFB +"s");
                    break;

                case 2:
                    listaMaxSum = new ArrayList<Integer>();
                    scanner = new Scanner(System.in);
                    System.out.println("Qual será o limite de elementos do array?");
                    //limiteArray é o valor maximo de elementos do array analizado
                    limiteArray = scanner.nextInt();
                    Main.gerarListaMaxSum(listaMaxSum,limiteArray);

                    //captura de tempo inicio
                    System.out.println("\n Inicio do algoritmo de Divisão e Conquista ...." );
                    long tempoInicioDC = System.currentTimeMillis();
                    int RespostaDC = Main.subsequenciaSomaMaxDC(listaMaxSum, 0,(listaMaxSum.size())-1);
                    //captura de tempo fim
                    long tempoDecorridoDC = (System.currentTimeMillis() - tempoInicioDC)/1000;

                    //Respostas do Algoritmo de Divisão e Conquista
                    System.out.println("\n ===== ALGORITMO DIVISÃO E CONQUISTA (Subsequência de Soma Máxima) ====");
                    System.out.println(" Soma máxima da sequência: " + RespostaDC);
                    System.out.println(" T = O(n logn)");
                    System.out.println(" O algoritmo faz O(n) comparações a cada nivel de chamada recursiva e o número de níveis corresponde a O(log n)");
                    System.out.println(" Tempo decorrido: " + tempoDecorridoDC +"s");
                    break;

                default:
                    System.out.println("\n  >>> Opção Inválida! <<<\n");
                    break;
            }
        } while (opcaoMenu != 0);
        sc.close();
        System.out.println("\n\n FIM DO PROGRAMA! ");
    }

    /**
     * @brief Gera conjunto aleatório de inteiros
     *
     * @param limiteArray do conjunto
     * @param listaMaxSum: variável do conjunto de inteiros
     *
     * @return conjunto de inteiros gerados
     *
     */
    public static List<Integer> gerarListaMaxSum(List<Integer> listaMaxSum, int limiteArray){
        System.out.println("\n Gerando sequencia de inteiros ...." );
        //Gerador do array de positivos e negativos
        ArrayList<Integer> sequencia = new ArrayList<Integer>();
        for (int i = 0; i<limiteArray; i++){
            Random random=new Random();
            int randomNumber=(random.nextInt(20)-10);
            listaMaxSum.add(randomNumber);
        }
        Integer[] array = listaMaxSum.toArray(new Integer[0]);

//        System.out.println("Sequencia analizada: " + listaMaxSum);

        return listaMaxSum;
    }

    /**
     * @brief Subsequência de soma máxima com força bruta
     *
     * @param listaMaxSum Sequência de inteiros
     * @param limiteArray numero limite de posições da sequência
     * @param respostaFB resposta da soma do algoritmo
     *
     * @return Soma da subsequência de soma máxima
     */

    public static int SubsequenciaSomaMaxFB(List<Integer> listaMaxSum,int limiteArray,int respostaFB){

        // ===== ALGORITMO DE FORÇA BRUTA
//        indiceArray é o indice do array
//        indiceSubArray é o indice do sub array, de quem será calculado a sua soma maxima

            for(int indiceArray = 1; indiceArray <= limiteArray; ++indiceArray ){ // O(n)
            for (int indiceSubArray = 0; indiceSubArray < listaMaxSum.size(); ++indiceSubArray) { // O(n)
                //if para caso se a soma dos indices que esta sendo percorrido ultrapassar o limite do array, definir a parada de procura.
                if(indiceSubArray + indiceArray > limiteArray){
                    break;
                }
                int soma = 0;
                for(int i = indiceSubArray; i < (indiceSubArray + indiceArray); i++){ // O(n)
                    soma = soma + listaMaxSum.get(i);
                }
                respostaFB = Math.max(respostaFB, soma);
            }
        }

            return respostaFB;

    }

    /**
     * @brief Subsequência de soma máxima com programação dinâmica (Divisão e conquista)
     *
     * @param listaMaxSum Sequência de inteiros.
     * @param ultimo elemento na ultima posição da subsequência
     *
     * @return Soma da subsequência de soma máxima
     */
    static int subsequenciaSomaMaxDC(List<Integer> listaMaxSum,int primeiro,int ultimo){

        // ===== ALGORITMO DE DIVISÃO E CONQUISTA
        //Primeiro é o valor do primeiro elemento
        //ultimo é o valor do ultimo elemento
        //caso base, array de 1 elemento
        if (primeiro == ultimo)
            return listaMaxSum.get(primeiro);
        //meio é o ponto médio do array
        int meio = (primeiro + ultimo)/2;
        return Math.max(Math.max(subsequenciaSomaMaxDC(listaMaxSum,primeiro,meio),subsequenciaSomaMaxDC(listaMaxSum,meio+1,ultimo)),somaMaxIntercalada(listaMaxSum,primeiro,meio,ultimo));
    }

    /**
     * @brief Procura da subsequência com soma maxima, nas duas partes da sequencia ( inicio-meio e fim-meio)
     *
     * @param listaMaxSum Sequência de inteiros.
     * @param meio elemento na posição da metade da subsequência
     * @param ultimo elemento na ultima posição da subsequência
     *
     * @return Subsequência com a soma maxima
     */

    static int somaMaxIntercalada(List<Integer> listaMaxSum,int primeiro,int meio, int ultimo){

        //verificar lado direito para o meio do array
        int soma = 0;
        int soma_dir = Integer.MIN_VALUE;
        for (int i = meio + 1; i <= ultimo; i++)
        {
            soma = soma + listaMaxSum.get(i);
            if (soma > soma_dir)
                soma_dir = soma;
        }
        //verificar lado esquerdo para o o meio do array
        soma = 0;
        int soma_esq = Integer.MIN_VALUE;
        for (int i = meio; i >= primeiro; i--)
        {
            soma = soma + listaMaxSum.get(i);
            if (soma > soma_esq)
                soma_esq = soma;
        }

        //retornar a soma dos elementos da direita e da esquerda
        return Math.max(soma_esq + soma_dir, Math.max(soma_esq, soma_dir));
    }

}

