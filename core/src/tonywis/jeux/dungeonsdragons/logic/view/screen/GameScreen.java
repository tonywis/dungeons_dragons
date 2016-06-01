package tonywis.jeux.dungeonsdragons.logic.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import tonywis.jeux.dungeonsdragons.logic.map.GlobalMap;
import tonywis.jeux.dungeonsdragons.logic.view.GameView;
import tonywis.jeux.dungeonsdragons.logic.view.gesture.GestureGame;

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

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        globalMapModel = new GlobalMap();

        camera = new OrthographicCamera(screenWidth, screenHeight);
        camera.setToOrtho(true, screenWidth, screenHeight);
        camera.position.set(screenWidth*0.5f, screenHeight*0.5f, 0); // middle of screen
        camera.update();

        batch = new SpriteBatch();

        GlobalMap g = new GlobalMap();

        map1 = new Texture(Gdx.files.internal("02000.jpg"));
        int tileW = map1.getWidth()/11;
        int tileH = map1.getHeight()/11;
        regdar = new Texture(Gdx.files.internal("regdar.png"));
        TextureRegion[][] splitMap1 = TextureRegion.split(map1, tileW, tileH);
        globalMap = new TiledMap();
        MapLayers layers = globalMap.getLayers();
        TiledMapTileLayer layer = new TiledMapTileLayer(11, 11, tileW, tileH);
        for (int l=0; l<layer.getHeight(); l++) {
            for (int c=0; c<layer.getWidth(); c++) {
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                splitMap1[l][c].flip(false, true);
                cell.setTile(new StaticTiledMapTile(splitMap1[l][c]));
                layer.setCell(c, l, cell);
            }
        }
        layers.add(layer);
        TextureRegion t = new TextureRegion(regdar, tileW, tileH);
        t.flip(false, true);
        TiledMapTileLayer layer2 = new TiledMapTileLayer(11, 11, tileW, tileH);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        cell.setTile(new StaticTiledMapTile(t));
        layer2.setCell(3, 1, cell);
        layers.add(layer2);

        globalMapRenderer = new OrthogonalTiledMapRenderer(globalMap);

        gesture = new GestureDetector(new GestureGame(this, camera, map1.getWidth(), map1.getHeight()));
        Gdx.input.setInputProcessor(gesture);
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
        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        globalMapRenderer.setView(camera);
        globalMapRenderer.render();

        //batch.setProjectionMatrix(camera.combined);
        batch.begin();
        //batch.draw(map1, 0, 0);
		/*for (int i = 0; i < regions.length; i++) {
			for (int j = 0; j < regions[i].length; j++) {
				batch.draw(regions[i][j], CELL_SIZE * j, CELL_SIZE*((regions.length-1)-i));
			}
		}*/
        batch.end();
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
        map1.dispose();
        regdar.dispose();
        batch.dispose();
    }
}
