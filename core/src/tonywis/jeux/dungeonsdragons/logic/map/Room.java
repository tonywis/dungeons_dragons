package tonywis.jeux.dungeonsdragons.logic.map;

import java.util.ArrayList;

/**
 * Created by Tony on 16/05/2016.
 */
public class Room {
    private ArrayList<Tile> tilesList;
    private MapTiles mapTiles;

    public Room() {
        tilesList = new ArrayList<Tile>();
    }

    public void addTileToRoom(Tile tile) {
        tilesList.add(tile);
    }

    public MapTiles getMapTiles() {
        return mapTiles;
    }

    public void setMapTiles(MapTiles mTile) {
        mapTiles = mTile;
    }
}
