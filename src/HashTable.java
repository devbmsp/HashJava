public abstract class HashTable {
    protected LinkedList[] buckets;
    protected int capacity;
    protected int collisionCount = 0;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList();
        }
    }

    protected abstract int hash(String key);

    public void insert(String key) {
        int idx = hash(key);
        if (buckets[idx].size() > 0) {
            collisionCount++;
        }
        buckets[idx].add(key);
    }

    public boolean contains(String key) {
        int idx = hash(key);
        return buckets[idx].contains(key);
    }

    public int getCollisionCount() {
        return collisionCount;
    }

    public int[] getDistribution() {
        int[] dist = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            dist[i] = buckets[i].size();
        }
        return dist;
    }
}
