public class HashTableFunc1 extends HashTable {
    public HashTableFunc1(int capacity) {
        super(capacity);             // 1) chama construtor da superclasse
    }

    @Override
    protected int hash(String key) {
        int sum = 0;
        for (char c : key.toCharArray()) { // 1) percorre cada caractere da string
            sum += c;                      // 2) acumula valor do caractere
        }
        return sum % capacity;            // 3) usa módulo para garantir índice válido
    }
}
