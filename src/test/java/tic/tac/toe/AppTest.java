package tic.tac.toe;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
  @Test
  public void testAppHasAGreeting() {
    App app = new App();
    assertNotNull("app should have a greeting", app.getGreeting());
  }

  @Test
  public void testAppHasAGame() {
    App app = new App();
    assertNotNull("app should have a game", app.displayGame());
  }
}
