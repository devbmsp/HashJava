public abstract class HashTable {
    protected LinkedList[] buckets;   // array de buckets, cada bucket é uma LinkedList
    protected int capacity;           // número de buckets
    protected int collisionCount;     // contador total de colisões

    // Construtor: inicializa o array de buckets e cria listas vazias
    public HashTable(int capacity) {
        this.capacity = capacity;      // 1) define capacidade inicial
        this.buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList(); // 2) cria lista ligada vazia para cada bucket
        }
        this.collisionCount = 0;       // 3) inicia contador de colisões em zero
    }

    // Método abstrato: cada subclasse implementa sua própria função hash
    protected abstract int hash(String key);

    // Insere uma chave na tabela, tratando colisões por chaining
    public void insert(String key) {
        int idx = hash(key);          // 1) calcula índice a partir da função hash
        if (buckets[idx].size() > 0) {
            collisionCount++;         // 2) se bucket não vazio, há colisão
        }
        buckets[idx].add(key);        // 3) adiciona a chave no início da lista ligada
    }

    // Verifica se uma chave está presente na tabela
    public boolean contains(String key) {
        int idx = hash(key);          // 1) calcula índice
        return buckets[idx].contains(key); // 2) delega para LinkedList.contains()
    }

    // Retorna o número total de colisões ocorridas
    public int getCollisionCount() {
        return collisionCount;
    }

    // Retorna um array com a distribuição de elementos em cada bucket
    public int[] getDistribution() {
        int[] dist = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            dist[i] = buckets[i].size(); // 1) obtém tamanho de cada linked list
        }
        return dist;                   // 2) retorna array de distribuição
    }
}
