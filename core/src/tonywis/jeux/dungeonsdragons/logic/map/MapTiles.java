package tonywis.jeux.dungeonsdragons.logic.map;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Tony on 17/05/2016.
 */
public class MapTiles {
    private int mapID;
    private Rotation rotationMap;
    private Tile[][] globalTiles;
    private ArrayList<Room> roomList;

    enum Rotation { ROTATION_NOT, ROTATION_90, ROTATION_180, ROTATION_270 }

    public MapTiles(int id, Rotation rot, JSONArray jsonArray) {
        mapID = id;
        rotationMap = rot;
        roomList = new ArrayList<Room>();
        Room room = new Room();
        room.setMapTiles(this);
        roomList.add(room);
        globalTiles = new Tile[jsonArray.length()][jsonArray.length()];
        for (int line=0; line < jsonArray.length(); line++) {
            JSONArray jArrayLine = jsonArray.getJSONArray(line);
            for (int col=0; col < jArrayLine.length(); col++) {
                JSONObject jTileMove = jArrayLine.getJSONObject(col);
                Tile tile = new Tile(line, col, room, jTileMove);
                placeTileWithRotation(tile, line, col);
            }
        }
    }

    public void placeTileWithRotation(Tile tile, int line, int col) {
        switch (rotationMap) {
            case ROTATION_90:
                tile.line = col;
                tile.col = 10-line;
                break;
            case ROTATION_180:
                tile.line = 10-line;
                tile.col = 10-col;
                break;
            case ROTATION_270:
                tile.line = 10-col;
                tile.col = line;
                break;
        }
        tile.applyRotation();
        globalTiles[tile.line][tile.col] = tile;
    }

    public int getMapID() {
        return mapID;
    }

    public void setMapID(int mapID) {
        this.mapID = mapID;
    }

    public Rotation getRotationMap() {
        return rotationMap;
    }

    public void setRotationMap(Rotation rotationMap) {
        this.rotationMap = rotationMap;
    }

    public Tile[][] getGlobalTiles() {
        return globalTiles;
    }

    public void setGlobalTiles(Tile[][] globalTiles) {
        this.globalTiles = globalTiles;
    }
}
