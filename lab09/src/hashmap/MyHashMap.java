package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size;
    private int capacity;
    private double loadFactor;

    /** Constructors */
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        buckets = new Collection[capacity];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
        this.size = 0;
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    private int getBucketIndex(K key) {
        if (key == null) {
            return 0;
        }
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    private void resize() {
        capacity *= 2;
        Collection<Node>[] newBuckets = new Collection[capacity];
        for (int i = 0; i < newBuckets.length; i++) {
            newBuckets[i] = createBucket();
        }
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                int newIndex = getBucketIndex(node.key);
                newBuckets[newIndex].add(node);
            }
        }
        buckets = newBuckets;
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
        if ((size + 1) / (double) capacity > loadFactor) {
            resize();
        }
        Node node = new Node(key, value);
        int index = getBucketIndex(key);
        for (Node n : buckets[index]) {
            if (n.key.equals(key)) {
                n.value = value;
                return;
            }
        }
        boolean add = buckets[index].add(node);
//        System.out.println(value);
        size++;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        int index = getBucketIndex(key);
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        int index = getBucketIndex(key);
        if (index >= buckets.length) {
            return false;
        }
        boolean isContained = false;
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                isContained = true;
            }
        }
        return isContained;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        for (Collection<Node> bucket : buckets) {
            if (bucket != null) {
                bucket.clear();
            }
        }
        this.size = 0;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 9.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 9. If you don't implement this, throw an
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
        throw new UnsupportedOperationException();
    }

}
