package tonywis.jeux.dungeonsdragons.logic.view.gesture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Tony on 14/05/2016.
 */
public class GestureGame implements GestureDetector.GestureListener {

    private static final String TAG = "GestureGame";
    OrthographicCamera camera;
    private float initialScale;
    public static float ZOOM_MAX = 1f;// Default
    public final static float ZOOM_MIN = 0.5f; // Default
    public float maxMapH;
    public float maxMapW;
    public tonywis.jeux.dungeonsdragons.logic.view.screen.GameScreen gameScreen;

    public GestureGame(tonywis.jeux.dungeonsdragons.logic.view.screen.GameScreen g, OrthographicCamera c, float maxMapWidth, float maxMapHeight) {
        gameScreen = g;
        camera = c;
        maxMapW = maxMapWidth;
        maxMapH = maxMapHeight;
        initialScale = 1;
        if (maxMapH >= maxMapW)
            ZOOM_MAX = maxMapH / camera.viewportHeight;
        else
            ZOOM_MAX = maxMapW / camera.viewportWidth;
    }

    public void replaceBorder() {
        /*Gdx.app.log(TAG+" x: ", java.lang.String.valueOf(camera.position.x));
        Gdx.app.log(TAG+" y: ", java.lang.String.valueOf(camera.position.y));
        Gdx.app.log(TAG+" Zoom: ", java.lang.String.valueOf(camera.zoom));*/

        // droite
        if ((camera.position.x) > maxMapW - ((camera.viewportWidth*0.5f)*camera.zoom))
            camera.position.x = maxMapW - ((camera.viewportWidth*0.5f)*camera.zoom);
        // gauche
        if (camera.position.x < (camera.viewportWidth*0.5f)*camera.zoom)
            camera.position.x = (camera.viewportWidth*0.5f)*camera.zoom;
        // haut
        if ((camera.position.y) > maxMapH - ((camera.viewportHeight*0.5f)*camera.zoom))
            camera.position.y = maxMapH - ((camera.viewportHeight*0.5f)*camera.zoom);
        // bas
        if ((camera.position.y) < (camera.viewportHeight*0.5f)*camera.zoom)
            camera.position.y = (camera.viewportHeight*0.5f)*camera.zoom;
    }

    public void getCellOnClick(float x, float y, String from) {
        Gdx.app.log(TAG+" "+from+" Camera", String.valueOf(camera.position.x)+" / "+String.valueOf(camera.position.y) + " / "+String.valueOf(camera.zoom));
        Gdx.app.log(TAG+" "+from+" Click", String.valueOf(x)+" / "+String.valueOf(y));
        int decalY = (int) ((camera.position.y/camera.zoom) - (camera.viewportHeight*0.5f));
        int decalX = (int) ((camera.position.x/camera.zoom) - (camera.viewportWidth*0.5f));
        int line = (int)(Math.floor((y+decalY)/(186/camera.zoom)));
        int col = (int) Math.floor((x+decalX)/(186/camera.zoom));
        gameScreen.globalMapModel.selectTile(line, col);
        Gdx.app.log(TAG+" "+from+" Click", "Ligne: "+String.valueOf(line)+" / Col: "+String.valueOf(col));
    }

    /**
     * @param x
     * @param y
     * @param pointer
     * @param button
     * @see InputProcessor#touchDown(int, int, int, int)
     */
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        initialScale = camera.zoom;
        return false;
    }

    /**
     * Called when a tap occured. A tap happens if a touch went down on the screen and was lifted again without moving outside
     * of the tap square. The tap square is a rectangular area around the initial touch position as specified on construction
     * time of the {@link GestureDetector}.
     *
     * @param x
     * @param y
     * @param count  the number of taps.
     * @param button
     */
    @Override
    public boolean tap(float x, float y, int count, int button) {
        getCellOnClick(x, y, "tap");
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        getCellOnClick(x, y, "longPress");
        return false;
    }

    /**
     * Called when the user dragged a finger over the screen and lifted it. Reports the last known velocity of the finger in
     * pixels per second.
     *
     * @param velocityX velocity on x in seconds
     * @param velocityY velocity on y in seconds
     * @param button
     */
    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    /**
     * Called when the user drags a finger over the screen.
     *
     * @param x
     * @param y
     * @param deltaX the difference in pixels to the last drag event on x.
     * @param deltaY the difference in pixels to the last drag event on y.
     */
    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        camera.translate(-deltaX*camera.zoom, -deltaY*camera.zoom);
        replaceBorder();
        return false;
    }

    /**
     * Called when no longer panning.
     *
     * @param x
     * @param y
     * @param pointer
     * @param button
     */
    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    /**
     * Called when the user performs a pinch zoom gesture. The original distance is the distance in pixels when the gesture
     * started.
     *
     * @param initialDistance distance between fingers when the gesture started.
     * @param distance        current distance between fingers.
     */
    @Override
    public boolean zoom(float initialDistance, float distance) {
        float ratio = initialDistance / distance;
        float zoomFinal = initialScale * ratio;
        if (zoomFinal > ZOOM_MAX)
            zoomFinal = ZOOM_MAX;
        else if (zoomFinal < ZOOM_MIN)
            zoomFinal = ZOOM_MIN;
        camera.zoom = zoomFinal;
        replaceBorder();
        return false;
    }

    /**
     * Called when a user performs a pinch zoom gesture. Reports the initial positions of the two involved fingers and their
     * current positions.
     *
     * @param initialPointer1
     * @param initialPointer2
     * @param pointer1
     * @param pointer2
     */
    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
