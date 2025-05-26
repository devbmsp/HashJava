public class HashTableFunc1 extends HashTable {
    public HashTableFunc1(int capacity) {
        super(capacity);
    }

    @Override
    protected int hash(String key) {
        int sum = 0;
        for (char c : key.toCharArray()) {
            sum += c;
        }
        return sum % capacity;
    }
}
