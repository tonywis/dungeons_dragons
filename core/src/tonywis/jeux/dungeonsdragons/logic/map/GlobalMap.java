package tonywis.jeux.dungeonsdragons.logic.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Tony on 17/05/2016.
 */
public class GlobalMap {

    private Tile[][] globalTiles; // [line][col]
    private MapTiles[][] globalMapTile;
    private ArrayList<Room> roomList;

    public GlobalMap() {
        FileHandle file = Gdx.files.internal("datas/maps/2.json");
        String text = file.readString();
        JSONArray jsonArray = new JSONArray(text);
        globalMapTile = new MapTiles[1][1];
        generateTiles(jsonArray);
        //Gdx.app.log("GlobalMap", jsonObject.toString());
    }

    public void generateTiles(JSONArray jsonArray) {
        globalMapTile[0][0] = new MapTiles(2, MapTiles.Rotation.ROTATION_NOT, jsonArray);
        globalTiles = globalMapTile[0][0].getGlobalTiles();
    }

    public void selectTile(int line, int col) {
        Tile tile = globalTiles[line][col];
        tile.moveFromTileTo.toString();
    }
}
