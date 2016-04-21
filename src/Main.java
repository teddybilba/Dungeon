import controller.KeyBoard;
import model.Game;
import view.Window;

public class Main {
	public static void main(String[] args) {
		Window window = new Window();
		
		Game game = new Game(window);
		KeyBoard keyboard = new KeyBoard(game);
		window.setKeyListener(keyboard);
	}
}
