public class HashTableFunc2 extends HashTable {
    public HashTableFunc2(int capacity) {
        super(capacity);
    }

    @Override
    protected int hash(String key) {
        int hash = 7;
        for (char c : key.toCharArray()) {
            hash = hash * 31 + c;
        }
        return Math.abs(hash) % capacity;
    }
}
