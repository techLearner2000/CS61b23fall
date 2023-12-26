import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int size;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public BSTMap() {

    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        // base case
        if (node == null) {
            return new Node(key, value, 1);
        }

        // recursive case
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            node.right = put(node.right, key, value);
        }
        else if (cmp < 0) {
            node.left = put(node.left, key, value);
        }
        else {
            node.value = value;
        }
        node.size = 1 + size(node.right) + size(node.left);

        // return the root
        return node;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        V res;
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            res = get(node.right, key);
        }
        else if (cmp < 0) {
            res = get(node.left, key);
        }
        else {
            res = node.value;
        }
        return res;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        boolean res = containsKey(root, key);
        return res;
    }

    private boolean containsKey(Node node, K key) {
        boolean res;
        if (node == null) {
            return false;
        }

        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            res = containsKey(node.right, key);
        } else if (cmp < 0) {
            res = containsKey(node.left, key);
        } else {
            res = true;
        }
        return res;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        int size = size(root);
        return size;
    }

    private int size(Node node) {
        // base case
        if (node == null) {
            return 0;
        }

        // recursive case
        return 1 + size(node.left) + size(node.right);
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        root = null;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
