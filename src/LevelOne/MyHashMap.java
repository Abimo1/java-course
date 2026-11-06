package LevelOne;

public class MyHashMap<K, V> {

    private Node<K, V>[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    private int hash(K key){
        if(key == null) return 0;
        int h = key.hashCode();
        h = h ^ (h >>> 16);
        return h & (table.length - 1);
    }

    private boolean keyEquals(K a, K b){
        if (a == null) return b == null;
        return a.equals(b);
    }

    public V get(K key){

        int idx = hash(key);
        Node<K, V> cur = table[idx];
        while(cur !=null){
            if(keyEquals(cur.key, key)){
                return cur.value;
            }
            cur= cur.next;
        }
        return  null;
    }

    public V put(K key, V value){
        if (size + 1 > table.length * LOAD_FACTOR){
            resize();
        }

        int idx = hash(key);
        Node<K, V> cur = table[idx];
        while(cur !=null){
            if(keyEquals(cur.key, key)){
                V old = cur.value;
                cur.value = value;
                return old;
            }
            cur = cur.next;
        }
        table[idx] = new Node<>(key, value, table[idx]);
        size++;
        return null;
    }

    public V remove(K key){

        int idx = hash(key);
        Node<K, V> cur = table[idx];
        Node<K, V> prev = null;
        while (cur != null){
            if (keyEquals(cur.key, key)){
                if (prev == null){
                    table[idx] = cur.next;
                }else{
                    prev.next = cur.next;
                }
                size--;
                return cur.value;
            }
            prev = cur;
            cur = cur.next;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void resize(){
        Node<K, V>[] oldTable = table;

        int newCapacity = oldTable.length * 2;

        table = (Node<K, V>[]) new Node[newCapacity];

        for (Node<K, V> head : oldTable){
            Node<K, V> cur = head;
            while (cur != null){
                Node<K, V> next = cur.next;
                int newIdx = hash(cur.key);
                cur.next = table[newIdx];
                table[newIdx] = cur;
                cur = next;
            }
        }
    }

    private static class Node<K, V>{
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
