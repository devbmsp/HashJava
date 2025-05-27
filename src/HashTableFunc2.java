public class HashTableFunc2 extends HashTable {
    public HashTableFunc2(int capacity) {
        super(capacity);
    }

    @Override
    protected int hash(String key) {
        int h = 7;                         // 1) valor inicial não-zero para evitar zeros
        for (char c : key.toCharArray()) { // 2) percorre caracteres
            h = h * 31 + c;               // 3) aplica fórmula polinomial
        }
        return Math.abs(h) % capacity;    // 4) evita negativos e aplica módulo
    }
}
