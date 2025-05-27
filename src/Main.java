import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // 1) Leitura do arquivo TXT contendo 5000 nomes
        String filename = "female_names.txt";
        String[] names = new String[5000];       // array estático para armazenar nomes
        int count = loadNames(filename, names);  // método auxiliar carrega nomes
        if (count == -1) {
            System.err.println("Erro ao ler arquivo de nomes.");
            return;                             // aborta se falha na leitura
        }

        // 2) Inicialização das tabelas hash com capacidade de 32
        int capacity = 32;
        HashTable func1 = new HashTableFunc1(capacity); // tabela com hash simples
        HashTable func2 = new HashTableFunc2(capacity); // tabela com hash polinomial

        // 3) Inserção de dados e medição de tempo para hash 1
        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            func1.insert(names[i]);              // insere nome e trata colisões
        }
        long insertTime1 = System.nanoTime() - start;  // calcula tempo total

        // 3) Inserção de dados e medição de tempo para hash 2
        start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            func2.insert(names[i]);
        }
        long insertTime2 = System.nanoTime() - start;

        // 4) Testes de busca e medição de tempo para hash 1
        start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            func1.contains(names[i]);           // verifica presença de cada nome
        }
        long searchTime1 = System.nanoTime() - start;

        // 4) Testes de busca e medição de tempo para hash 2
        start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            func2.contains(names[i]);
        }
        long searchTime2 = System.nanoTime() - start;

        // 5) Impressão de relatórios comparativos no console
        System.out.println("== Relatório Função Hash 1 ==");
        System.out.println("Colisões: " + func1.getCollisionCount());
        System.out.println("Tempo inserção (ns): " + insertTime1);
        System.out.println("Tempo busca (ns): " + searchTime1);
        System.out.println("Distribuição de chaves:");
        printDistribution(func1.getDistribution());  // imprime tamanho de cada bucket

        System.out.println("\n== Relatório Função Hash 2 ==");
        System.out.println("Colisões: " + func2.getCollisionCount());
        System.out.println("Tempo inserção (ns): " + insertTime2);
        System.out.println("Tempo busca (ns): " + searchTime2);
        System.out.println("Distribuição de chaves:");
        printDistribution(func2.getDistribution());
    }

    // Método auxiliar: lê nomes de arquivo TXT e retorna quantidade lida
    private static int loadNames(String filename, String[] names) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int idx = 0;
            while ((line = br.readLine()) != null && idx < names.length) {
                if (!line.trim().isEmpty()) {
                    names[idx++] = line.trim();    // armazena nome no array
                }
            }
            return idx;                          // retorna número de nomes carregados
        } catch (IOException e) {
            e.printStackTrace();                 // imprime erro de I/O
            return -1;                            // sinaliza falha na leitura
        }
    }

    // Imprime distribuição de elementos por bucket (índice e quantidade)
    private static void printDistribution(int[] dist) {
        for (int i = 0; i < dist.length; i++) {
            System.out.println("Índice " + i + ": " + dist[i]);
        }
    }
}