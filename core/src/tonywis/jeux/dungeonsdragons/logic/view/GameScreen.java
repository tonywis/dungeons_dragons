package tonywis.jeux.dungeonsdragons.logic.view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;

import tonywis.jeux.dungeonsdragons.logic.map.GlobalMap;

/**
 * Created by Tony on 21/05/2016.
 */
public class GameScreen implements Screen {

    public GameView gameView;
    private int screenWidth, screenHeight;

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture map1;
    private Texture regdar;
    public TiledMap globalMap;
    private TiledMapRenderer globalMapRenderer;
    public GlobalMap globalMapModel;

    private GestureDetector gesture;
    private static final float CELL_SIZE = 186;

    public GameScreen(GameView _gameView) {
        gameView = _gameView;
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
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

    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
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
