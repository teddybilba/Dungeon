import controller.KeyBoard;
import model.Game;
import view.Window;
import view.Settings;

public class Main {
	public static void main(String[] args) {
		Window window = new Window();
		Game game = new Game(window);
		Settings settings= new Settings();
		KeyBoard keyboard = new KeyBoard(game);
		window.setKeyListener(keyboard);
	}
}
