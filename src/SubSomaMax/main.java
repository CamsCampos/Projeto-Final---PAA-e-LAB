package SubSomaMax;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        System.out.println("\n ===== ALGORITMO SUBSEQUÊNCIA DE SOMA MÁXIMA =====");


        //Gerador do array de positivos e negativos
        List<Integer> listaMaxSum = new ArrayList<>();
        ArrayList<Integer> sequencia = new ArrayList<Integer>();

//      limiteArray é o valor maximo de elementos do array analizado
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual será o limite de elementos do array?");
        int limiteArray = scanner.nextInt();

        for (int i = 0; i<limiteArray; i++){
            Random random=new Random();
            int randomNumber=(random.nextInt(20)-10);
            listaMaxSum.add(randomNumber);
        }
        Integer[] array = listaMaxSum.toArray(new Integer[0]);
        System.out.println(" Sequencia analizada: " + listaMaxSum);

        long tempoInicioFB = System.currentTimeMillis();
        //Chamada
        main.SubsequenciaSomaMaxFB(listaMaxSum,limiteArray);
        long tempoDecorridoFB = System.currentTimeMillis() - tempoInicioFB;
        //Tempo de execução FB
        System.out.println(" Tempo decorrido: " + tempoDecorridoFB +"ms");
        System.out.println(" T = O(N)^3");
        System.out.println(" O algoritmo faz O(n)^3 comparações, sendo três laços por iteração");

        long tempoInicioDC = System.currentTimeMillis();
        //Chamada
        int RespostaDC = main.subsequenciaSomaMaxDC(listaMaxSum, 0,(listaMaxSum.size())-1);
        //Respostas do Algoritmo de Divisão e Conquista
        System.out.println("\n ===== ALGORITMO DIVISÃO E CONQUISTA (Subsequência de Soma Máxima) =====");
        System.out.println(" Soma máxima da sequência: " + RespostaDC);

        long tempoDecorridoDC = System.currentTimeMillis() - tempoInicioFB;
        //Tempo de execução DC
        System.out.println(" Tempo decorrido: " + tempoDecorridoDC +"ms");

        System.out.println(" T = O(n logn)");
        System.out.println(" O algoritmo faz O(n) comparações a cada nivel de chamada recursiva e o número de níveis corresponde a O(log n)");

    }

    public static void SubsequenciaSomaMaxFB(List<Integer> listaMaxSum,int limiteArray){

        // ===== ALGORITMO DE FORÇA BRUTA
        int respostaFB = Integer.MIN_VALUE;
//        indiceArray é o indice do array
//        indiceSubArray é o indice do sub array, de quem será calculado a sua soma maxima
        for(int indiceArray = 1; indiceArray <= limiteArray; ++indiceArray ){
            for (int indiceSubArray = 0; indiceSubArray < listaMaxSum.size(); ++indiceSubArray) {
                //if para caso se a soma dos indices que esta sendo percorrido ultrapassar o limite do array, definir a parada de procura.
                if(indiceSubArray + indiceArray > limiteArray){
                    break;
                }
                int soma = 0;
                for(int i = indiceSubArray; i < (indiceSubArray + indiceArray); i++){
                    soma = soma + listaMaxSum.get(i);
//                    System.out.println(listaMaxSum.get(i));
                }
                respostaFB = Math.max(respostaFB, soma);
            }
        }

        //Respostas do Algoritmo de Soma Maxima força bruta
        System.out.println("\n ===== ALGORITMO DE FORÇA BRUTA (Subsequência de Soma Máxima) =====");
        System.out.println(" Soma máxima da sequência: " + respostaFB);

    }
    static int subsequenciaSomaMaxDC(List<Integer> listaMaxSum,int primeiro,int ultimo){

        // ===== ALGORITMO DE DIVISÃO E CONQUISTA

        //caso base, array de 1 elemento
        //Primeiro é o valor do primeiro elemento
        //ultimo é o valor do ultimo elemento

        if (primeiro == ultimo)
            return listaMaxSum.get(primeiro);
        //meio é o ponto médio do array
        int meio = (primeiro + ultimo)/2;
        return Math.max(Math.max(subsequenciaSomaMaxDC(listaMaxSum,primeiro,meio),subsequenciaSomaMaxDC(listaMaxSum,meio+1,ultimo)),somaMaxIntercalada(listaMaxSum,primeiro,meio,ultimo));
    }

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

