import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String filename = "female_names.txt";
        String[] names = new String[5000];
        int count = loadNames(filename, names);
        if (count == -1) {
            System.err.println("Erro ao ler arquivo de nomes.");
            return;
        }

        int capacity = 32;
        HashTable func1 = new HashTableFunc1(capacity);
        HashTable func2 = new HashTableFunc2(capacity);

        // Inserção e medição de tempo
        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            func1.insert(names[i]);
        }
        long insertTime1 = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            func2.insert(names[i]);
        }
        long insertTime2 = System.nanoTime() - start;

        // Busca e medição de tempo
        start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            func1.contains(names[i]);
        }
        long searchTime1 = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            func2.contains(names[i]);
        }
        long searchTime2 = System.nanoTime() - start;

        // Impressão do relatório
        System.out.println("== Relatório Função Hash 1 ==");
        System.out.println("Colisões: " + func1.getCollisionCount());
        System.out.println("Tempo inserção (ns): " + insertTime1);
        System.out.println("Tempo busca (ns): " + searchTime1);
        System.out.println("Distribuição de chaves:");
        printDistribution(func1.getDistribution());

        System.out.println("\n== Relatório Função Hash 2 ==");
        System.out.println("Colisões: " + func2.getCollisionCount());
        System.out.println("Tempo inserção (ns): " + insertTime2);
        System.out.println("Tempo busca (ns): " + searchTime2);
        System.out.println("Distribuição de chaves:");
        printDistribution(func2.getDistribution());
    }

    private static int loadNames(String filename, String[] names) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int idx = 0;
            while ((line = br.readLine()) != null && idx < names.length) {
                if (!line.trim().isEmpty()) {
                    names[idx++] = line.trim();
                }
            }
            return idx;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static void printDistribution(int[] dist) {
        for (int i = 0; i < dist.length; i++) {
            System.out.println("Índice " + i + ": " + dist[i]);
        }
    }
}