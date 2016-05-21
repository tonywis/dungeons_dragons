package tonywis.jeux.dungeonsdragons.logic.map;


import com.badlogic.gdx.Gdx;

import org.json.JSONObject;

import java.util.HashMap;

import tonywis.jeux.dungeonsdragons.logic.model.GameElement;

/**
 * Created by Tony on 16/05/2016.
 */
public class Tile {
    public MoveFromTileTo moveFromTileTo;
    public int line;
    public int col;
    public HashMap<Integer, GameElement> gameElements;
    public Room room;

    public class MoveFromTileTo {
        public boolean top;
        public boolean right;
        public boolean bottom;
        public boolean left;

        public MoveFromTileTo() {
            top = right = bottom = left = false;
        }

        public MoveFromTileTo(boolean t, boolean r, boolean b, boolean l) {
            top = t;
            right = r;
            bottom = b;
            left = l;
        }

        @Override
        public String toString() {
            Gdx.app.log("MoveFromTileTo", "TOP: "+String.valueOf(top)+" - RIGHT: "+String.valueOf(right)+" - BOTTOM: "+String.valueOf(bottom)+" - LEFT: "+String.valueOf(left));
            return super.toString();
        }
    }

    public Tile(int _line, int _col, Room _room, JSONObject jMoveFromTileTo) {
        moveFromTileTo = new MoveFromTileTo(
                jMoveFromTileTo.getBoolean("top"),
                jMoveFromTileTo.getBoolean("right"),
                jMoveFromTileTo.getBoolean("bottom"),
                jMoveFromTileTo.getBoolean("left"));
        line = _line;
        col = _col;
        room = _room;
        room.addTileToRoom(this);
        gameElements = new HashMap<Integer, GameElement>();
    }

    public void applyRotation() {
        switch (room.getMapTiles().getRotationMap()) {
            case ROTATION_90:
                moveFromTileTo = new MoveFromTileTo(
                        moveFromTileTo.left,
                        moveFromTileTo.top,
                        moveFromTileTo.right,
                        moveFromTileTo.bottom
                );
                break;
            case ROTATION_180:
                moveFromTileTo = new MoveFromTileTo(
                        moveFromTileTo.bottom,
                        moveFromTileTo.left,
                        moveFromTileTo.top,
                        moveFromTileTo.right
                );
                break;
            case ROTATION_270:
                moveFromTileTo = new MoveFromTileTo(
                        moveFromTileTo.right,
                        moveFromTileTo.bottom,
                        moveFromTileTo.left,
                        moveFromTileTo.top
                );
                break;
        }
    }
}
