package BaseGameUI;

import static org.junit.Assert.*;

import org.junit.Test;

public class StartScreenTest {
	MainScreen main = new MainScreen();
	StartScreen start = new StartScreen(main);

	@Test
	public void testFileNotnull() {	
		assertNotNull(start.chooseFile());
	}
	
}
