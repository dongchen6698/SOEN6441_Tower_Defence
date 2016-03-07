package Testcase;

import static org.junit.Assert.*;

import org.junit.Test;

import BaseGameUI.MainScreen;
import BaseGameUI.StartScreen;

public class StartScreenTest {
	MainScreen main = new MainScreen();
	StartScreen start = new StartScreen(main);

	@Test
	public void testFileNotnull() {	
		assertNotNull(start.chooseFile());
	}
	
}
