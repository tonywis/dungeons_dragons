package tonywis.jeux.dungeonsdragons.logic.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;

import tonywis.jeux.dungeonsdragons.logic.view.screen.*;

public class GameView extends Game {

	private final static java.lang.String TAG = "GameView";
	public MainMenuScreen mainMenuScreen;
	public LoadingScreen loadingScreen;
	public GameScreen gameScreen;
	public AssetManager assetManager;

	@Override
	public void create () {
		mainMenuScreen = new MainMenuScreen(this);
		setScreen(mainMenuScreen);
	}

	public Screen loadingAssetScreen() {
		loadingScreen = new LoadingScreen(this);
		return loadingScreen;
	}

	public Screen startGameScreen() {
		gameScreen = new GameScreen(this);
		return gameScreen;
	}
}
