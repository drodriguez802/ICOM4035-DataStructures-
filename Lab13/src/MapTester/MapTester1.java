package MapTester;

import directBSTMap.DirectBSTMap;
import mapInterfaces.Map;
import mapInterfaces.Entry; 

public class MapTester1 {

	public static void main(String[] args) {
		Map<Integer, String> map = 
			new DirectBSTMap<Integer, String>(new IntegerKeyValidator(), new NumberComparator1()); 
		map.put(1, "one"); 
		map.put(30, "thirty"); 
		map.put(13, "thirteen"); 
		map.put(40, "forty"); 
		map.put(-10, "minus ten"); 
		map.put(19, "nineteen"); 
		map.put(11, "eleven"); 
		map.put(4, "four"); 
		map.put(-5, "minus five"); 
		map.put(35, "thirty five"); 
		map.put(50, "fifty"); 
		map.put(25, "twenty five"); 
		map.put(17, "seventeen"); 
		map.put(8, "eight"); 
		map.put(9, "nine"); 
	 	map.put(10, "ten"); 
		map.put(7, "seven"); 
		map.put(23, "twenty three"); 
		map.put(16, "sixteen"); 
		map.put(14, "fourteen"); 
		map.put(47, "forty seven"); 
		map.put(29, "twenty nine"); 
		map.put(33, "thirty three");
		map.put(15, "fifteen"); 
		
		showMap("Test1", map); 
		
		map.put(19, "nineteen 2");
		showMap("Test2", map); 
		
		map.remove(13); 
		showMap("Test3", map); 

		map.remove(1); 
		showMap("Test3", map); 
		
	}
	
	private static <K,V> void showMap(final String msg, final Map<K,V> m) { 
		System.out.println("\n" + msg); 
		DirectBSTMap<K,V> bstm = (DirectBSTMap<K,V>) m; 
		bstm.showNodes(); 
	}
	private static <K,V> void showMapValues(final String msg, final Map<K,V> m) { 
		System.out.println("\n" + msg); 
		for (V value : m.values()) 
			System.out.println(value); 
	}

	private static <K,V> void showMapKeys(final String msg, final Map<K,V> m) { 
		System.out.println("\n" + msg); 
		for (K key : m.keys()) 
			System.out.println(key); 
	}

	private static <K,V> void showMapEntries(final String msg, final Map<K,V> m) { 
		System.out.println("\n" + msg); 
		for (Entry<K,V> e : m.entries()) 
			System.out.println(e); 
	}

}
