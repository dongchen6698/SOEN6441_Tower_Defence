package Testcase;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import MapVerification.MapVerification;

public class MapVerificationTest {
	
	private static int[][] map;
	private static int[][] map2;
	private static int[][] map3;
	private static int[][] map4;
	static MapVerification mapVerification;
	static MapVerification mapVerification2;
	static MapVerification mapVerification3;
	static MapVerification mapVerification4;
	
	@BeforeClass
	public static void beforeClasstest(){
		int[][] array = { 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1 },
				{ 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 3 },
				{ 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0 },
				{ 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 2, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 }};
		
		int[][] array2 = { 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1 },
				{ 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 3 },
				{ 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0 },
				{ 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 2, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 }};
		
		int[][] array3 = { 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 3 },
				{ 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1 },
				{ 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0 },
				{ 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 2, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 }};
		
		int[][] array4 = { 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 3 },
				{ 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0 },
				{ 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 2, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0 }};
		
		map = array;
		map2 = array2;
		map3 = array3;
		map4 = array4;
		
		mapVerification = new MapVerification(map);
		mapVerification2 = new MapVerification(map2);
		mapVerification3 = new MapVerification(map3);
		mapVerification4 = new MapVerification(map4);
	}
	@Before
	public void beforetest(){
		
	
	}
	
	@Test
	public void testAvailableMap() {
		 
		 assertTrue(mapVerification.isState());
	}
	
	@Test
	public void testWrongMap1(){
		
		assertFalse(mapVerification2.isState());
	}
	
	@Test
	public void testWrongMap2(){
		
		assertFalse(mapVerification3.isState());
	}
	
	@Test
	public void testWrongMap3(){
		
		assertFalse(mapVerification4.isState());
	}
	
	@Test
	public void testEntrypoint(){
		
		assertEquals(1, mapVerification.getNumEntryPoint());
	}
	
	@Test
	public void testExitPoint(){
		
		assertEquals(1, mapVerification.getNumExitPoint());
	}
	
	@Test
	public void testnumPath(){
		
		assertSame(41, mapVerification.getNumPath());
	}
}
