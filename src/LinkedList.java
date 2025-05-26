public class LinkedList {
    private Node head;
    private int size;

    private static class Node {
        String key;
        Node next;
        Node(String key) { this.key = key; }
    }

    public void add(String key) {
        Node node = new Node(key);
        node.next = head;
        head = node;
        size++;
    }

    public boolean contains(String key) {
        Node curr = head;
        while (curr != null) {
            if (curr.key.equals(key)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public int size() {
        return size;
    }
}
