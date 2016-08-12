package directBSTMap;

import java.util.Comparator;

import exceptions.InvalidKeyException;
import exceptions.NotAPlaceHolderNodeException;
import mapInterfaces.BTPosition;
import mapInterfaces.Entry;
import mapInterfaces.KeyValidator;
import mapInterfaces.Map;

public class DirectBSTMap<K, V> implements Map<K, V> {

	private BTPosition<Entry<K,V>> root; 
	private int size; 
	private KeyValidator<K> kv; 
	private Comparator<K> cmp; 
	
	public DirectBSTMap(KeyValidator<K> kv, Comparator<K> cmp) { 
		this.kv = kv; 
		this.cmp = cmp; 
		root = newPlaceHolder(); 
		size = 0; 
	}
	
	/**
	 * Internal method to generate a place-holder node. 
	 * @return Reference to the created node. 
	 */
	private BTNode<Entry<K,V>> newPlaceHolder() { 
		return new BTNode<Entry<K,V>>(); 
	}
	
	/**
	 * Returns the size of the map: number of elements. 
	 */
	public int size() {
		return size; 
	}

	/** 
	 * Determines if the map is empty 
	 * @return true if empty; false, otherwise.
	 */
	public boolean isEmpty() {
		return size == 0; 
	}

	/**
	 * Constructs an iterable collection containing all the values 
	 * store in the map collection. 
	 * @return the iterable collection of values.
	 */
	public Iterable<V> values() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Constructs an iterable collection containing all the keys 
	 * stored in the map collection. 
	 * @return the iterable collection of keys.
	 */
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Constructs an iterable collection containing all the key-
	 * value entries in the map collection. 
	 * @return the iterable collection of entries.
	 */
	public Iterable<Entry<K, V>> entries() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Searches a specified entry in the collection.
	 * @param key uniquely identifies the entry to look for
	 * @return null if entry with given key is not found; 
	 * otherwise, it returns reference to the entry having the
	 * particular element. 
	 * @throws InvalidKeyException whenever the key is not valid. 
	 */
	public V get(K key) throws InvalidKeyException {
		BTPosition<Entry<K, V>> rn = recGet(root, key); 
		if (rn != null) 
			return rn.element().getValue(); 
		return null;
	}

	/**
	 * Internal method to search for a particular entry in a 
	 * binary search tree.
	 * @param r root of the tree to search in 
	 * @param key the search key that guides the search
	 * @return null if not found. If found, it returns reference
	 * to the node containing entry with the given key.
	 */
	private BTPosition<Entry<K, V>> recGet(BTPosition<Entry<K, V>> r, K key) {
		if (isPlaceHolder(r)) 
			return null; 
		else { 
			int rc = cmp.compare(key, r.element().getKey()); 
			if (rc == 0)
				return r; 
			else if (rc < 0)
				return recGet(r.getLeft(), key); 
			else
				return recGet(r.getRight(), key); 
		}
	}

	/**
	 * Determines if a given node is a place-holder node (external)
	 * @param node the node to test
	 * @return true or false...
	 */
	private boolean isPlaceHolder(BTPosition<Entry<K,V>> node) { 
		return node.element() == null; 
	}
	
	/**
	 * If the collection does not have an entry with key “equal” to 
	 * the given key, then it adds a new entry containing the 
	 * given key-value pair and return null. I it already exists, 
	 * it replaces with value the existing value of the entry with 
	 * key equal to key and return the old value
	 * @param key key of the new element. 
	 * @param value value of the new element - the value to map
	 * with the given key
	 * @return null if no entry exists in the collection with the 
	 * given key value; otherwise it returns reference to the value
	 * being replaced.
	 * @throws InvalidKeyException whenever the key is not valid.
	 */
	public V put(K key, V value) throws InvalidKeyException {
		kv.validateKey(key); 
		BTPosition<Entry<K, V>> rn = recGet(root, key); 
		V vtr = null;
		if(rn!=null){
			vtr=rn.element().getValue();
			rn.element().setValue(value);
			return vtr;
			}
		else{
			recPut(root, key, value);
			size++;
			return null;
		}

	}

	/**
	 * Internal method used to recursively look for the place-holder node
	 * where the new entry is to be placed. Than node is then converted
	 * to an internal node. 
	 * @param r the root of the tree where the search, and eventual insertion, 
	 * takes place
	 * @param key the key used for the search : the key of the new entry 
	 * to add. 
	 * @param value the value of the new entry
	 */
	private void recPut(BTPosition<Entry<K,V>> r, K key, V value) {
		if (isPlaceHolder(r))  // r is a placeholder node
		{ 
			convertToInternalNode(r, new MyEntry<K, V>(key, value)); 
		}
		else if(cmp.compare(r.element().getKey(),key)<0){
			recPut(r.getRight(),key,value);
		}
		else{
			recPut(r.getLeft(),key,value);
			
		}
		r.resetHeight();
	}

	/**
	 * Converts a place-holder (external) node into an internal 
	 * node. Adds two place-holder nodes as children and 
	 * changes its element to a given entry. 	
	 * @param r the node to alter 
	 * @param e the entry to include in the node
	 */
	private void convertToInternalNode(BTPosition<Entry<K, V>> r,
			MyEntry<K, V> e) 
	throws NotAPlaceHolderNodeException
	{
		if (!isPlaceHolder(r)) 
			throw new NotAPlaceHolderNodeException(
					"Node to convert is already internal."); 
		BTPosition<Entry<K,V>> rph = new BTNode<Entry<K,V>>(); 
		BTPosition<Entry<K,V>> lph = new BTNode<Entry<K,V>>(); 
		r.setElement(e); 
		r.setLeft(lph); 
		r.setRight(rph); 
		r.resetHeight(); 
	}
	
	/**
	 * Return value in the entry currently in the collection having
	 * the given key value, if one exists; otherwise it returns 
	 * null. 
	 * @param key identifies the entry to look for.
	 * @return null if no entry is  found having the given key as 
	 * its key. Otherwise, it returns reference to the value paired 
	 * to the given key.
	 * @throws InvalidKeyException whenever the key is not valid. 
	 */
	public V remove(K key) { 
		BTPosition<Entry<K,V>> ptr = recGet(root, key); 
		if (ptr == null) 
			return null; 
		else {
			V vtr = ptr.element().getValue(); 
			recRemove(root, key); 
			size--; 
			return vtr; 
		}
	}

	/**
	 * Searches for the element corresponding to target
	 * inside the tree whose root is given. If found, 
	 * it is removed from the tree and the tree is partially
	 * reorganized as needed in order for it to continue
	 * being a bst. If not found, the tree is not 
	 * altered. 
	 * @param r the root of the tree where the operation
	 * is applied. 
	 * @param key the key of the element being searched for deletion. 
	 * See description of the corresponding parameter in
	 * the documentation given in method remove. 
	 */
	private void recRemove(BTPosition<Entry<K, V>> r, K key) 
	{
		// pre: the subtree having root r is bst and contains a
		// node having an entry with the given key
		int cp = cmp.compare(r.element().getKey(), key); 
		if (cp == 0)
			deleteEntryAtNode(r); 
		else if (cp < 0)
			recRemove(r.getRight(), key); 
		else 
			recRemove(r.getLeft(), key); 
		r.resetHeight(); 
	}

	/**
	 * Removes from the collection, the entry currently at 
	 * the the given node. The entry is removed, and 
	 * perhaps substituted by some other entry in the 
	 * subtree whose root is the node given. At the end,
	 * the subtree having the given node as its root has 
	 * one data node less, continues satisfying the bst property, 
	 * and the only missing element is the one originally 
	 * at the given node.
	 * @param r the node containing the entry to remove.
	 */
	private void deleteEntryAtNode(BTPosition<Entry<K, V>> r) {
		BTPosition<Entry<K, V>> nc = null; 
		if (isPlaceHolder(r.getLeft())) { 
			nc = r.getRight(); 
			r.copyFrom(nc); 
			nc.clean(); 
		} else if (isPlaceHolder(r.getRight())) { 
			nc = r.getLeft(); 
			r.copyFrom(nc);  
			nc.clean(); 
		} else { 
			BTPosition<Entry<K, V>> minE = minimumElementNode(r.getRight()); 
			r.setElement(minE.element()); 
			recRemove(r.getRight(), minE.element().getKey());
		}
		r.resetHeight(); 
	}

	/**
	 * Returns reference to the node containing the minimum
	 * value among all the values in the bst tree whose root
	 * is given as parameter. The tree is assumed to be bst. 
	 * @param r the root of the tree where the operation
	 * takes place.
	 * @return the root of the resulting tree. 
	 */
	private BTPosition<Entry<K, V>> minimumElementNode(BTPosition<Entry<K, V>> r) {
		// pre: right != null
		BTPosition<Entry<K, V>> ntret = r; 
		while (!isPlaceHolder(ntret.getLeft()))
			ntret = ntret.getLeft(); 
		return ntret;
	}
	
	/////////////////////////////////////////////////////////////////
	// INTERNAL CLASS ///////////////////////////////////////////////
	// Internal class for entries....
	private static class MyEntry<K1, V1> implements Entry<K1, V1> { 
		private K1 key; 
		private V1 value; 
		public MyEntry(K1 key, V1 value) { 
			this.key = key; 
			this.value = value; 
		}
		public K1 getKey() {
			return key;
		}
		public V1 getValue() {
			return value;
		}
		public void setKey(K1 key) {
            this.key = key; 
		}
		public void setValue(V1 value) {
			this.value = value; 
		}
		public String toString() { 
			return "[" + key + ", " + value + "]"; 
		}
	}
	
	
	/////////////////////////////////////////////////////////////
	// for testing purposes......
	public void showNodes() { 
		System.out.println("Showing Nodes in PREODDER: ");
		preorderShowNodes(root); 
	}

	private void preorderShowNodes(BTPosition<Entry<K, V>> r) {
		if (!isPlaceHolder(r)) { 
			System.out.println(r.element()
					+" => Height = " + r.getHeight());
			preorderShowNodes(r.getLeft()); 
			preorderShowNodes(r.getRight()); 
		}
		
	}
}
