public class LinkedList {
    // Nó interno representando um elemento da lista
    private static class Node {
        String key;      // valor armazenado
        Node next;       // referência para o próximo nó
        Node(String key) { this.key = key; }
    }

    private Node head;   // início da lista (bucket)
    private int size;    // número de elementos na lista

    // Adiciona uma nova chave ao início da lista (O(1))
    public void add(String key) {
        Node node = new Node(key); // 1) criar novo nó com a chave
        node.next = head;          // 2) apontar para o antigo head
        head = node;               // 3) novo nó torna-se head
        size++;                    // 4) incrementar contador de elementos
    }

    // Verifica se a lista contém determinada chave (O(n) no tamanho do bucket)
    public boolean contains(String key) {
        Node curr = head;          // 1) começa no head
        while (curr != null) {     // 2) percorre até o fim
            if (curr.key.equals(key)) {
                return true;       // 3) retorna true se encontrar
            }
            curr = curr.next;      // 4) avança para o próximo nó
        }
        return false;              // 5) retorna false se não encontrar
    }

    // Retorna a quantidade de elementos no bucket (usado para colisão e distribuição)
    public int size() {
        return size;
    }
}
