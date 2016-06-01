package tonywis.jeux.dungeonsdragons.logic.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;

import tonywis.jeux.dungeonsdragons.logic.view.GameView;

/**
 * Created by Tony on 21/05/2016.
 */
public class LoadingScreen implements Screen {

    private final BitmapFont font;
    private final SpriteBatch batch;
    private final Texture emptyT;
    private final Texture fullT;
    private final NinePatch empty;
    private final NinePatch full;
    public GameView gameView;

    public LoadingScreen(GameView _gameView) {
        gameView = _gameView;
        gameView.assetManager = new AssetManager();
        gameView.assetManager.load("tex/maps/maps.txt", TextureAtlas.class);

        font=new BitmapFont();
        batch=new SpriteBatch();
        emptyT=new Texture(Gdx.files.internal("tex/loading/empty.png"));
        fullT=new Texture(Gdx.files.internal("tex/loading/full.png"));
        empty=new NinePatch(new TextureRegion(emptyT,24,24),8,8,8,8);
        full=new NinePatch(new TextureRegion(fullT,24,24),8,8,8,8);
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
        batch.begin();
        empty.draw(batch, 40, 225, 720, 30);
        full.draw(batch, 40, 225, gameView.assetManager.getProgress()*720, 30);
        font.draw(batch, (int)(gameView.assetManager.getProgress()*100)+"% loaded", 400, 247, 0, Align.center, false);
        batch.end();
        if (gameView.assetManager.update()) {
            gameView.setScreen(gameView.startGameScreen());
        }
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
