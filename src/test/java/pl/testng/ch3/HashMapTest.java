package pl.testng.ch3;

import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HashMapTest {

	Map<String,String> map = null;
	
	@BeforeMethod
	public void prepareHasMap() {
		map = new HashMap<String,String>();
	}
	
	@Test
	public void shouldAddElement() {
		map.put("key", "element");
		
		assertEquals(map.get("key"),"element");
	}
	@Test
	public void shouldClean() {
		map.put("key", "element");
		map.put("key2", "element2");
		map.clear();
		assertEquals(map.keySet().size(),0);
	}
	@Test
	public void shouldReplace() {
		map.put("key", "element");
		map.put("key", "newValue");
		map.clear();
		assertNotEquals(map.get("key"),"element");
	}
	@Test
	public void shouldAddWithNullKey() {
		map.put(null, "element");

		assertEquals(map.get(null),"element");
	}
}
