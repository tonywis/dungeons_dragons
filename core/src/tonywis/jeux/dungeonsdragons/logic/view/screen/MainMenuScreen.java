package tonywis.jeux.dungeonsdragons.logic.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import tonywis.jeux.dungeonsdragons.logic.view.GameView;

/**
 * Created by Tony on 21/05/2016.
 */
public class MainMenuScreen implements Screen {

    private final GameView gameView;

    public MainMenuScreen(GameView _gameView) {
        gameView = _gameView;
    }

    /**
     * Called when this screen becomes the current screen for a {@link *Game}.
     */
    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched())
            gameView.setScreen(gameView.loadingAssetScreen());
    }

    /**
     * @param width
     * @param height
     * @see *ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * @see *ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see *ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link *Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
