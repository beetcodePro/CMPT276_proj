package main;

import entities.AnimateEntity;
import entities.Player;

public class CheckCollision 
{
    Simulator sim;
    KeyBoard keyboard;

    public CheckCollision(Simulator sim, KeyBoard key){
        this.sim = sim;
        this.keyboard = key;
    }

    // Checks tile for PLAYER ONLY
    public void checkTileForPlayer(Player plr){
        int entityLeftX = plr.get_coordinate_X() + plr.get_hitbox().x;
        int entityRightX = plr.get_coordinate_X() + plr.get_hitbox().x + plr.get_hitbox().width;
        int entityTopY = plr.get_coordinate_Y() + plr.get_hitbox().y;
        int entityBottomY = plr.get_coordinate_Y() + plr.get_hitbox().y + plr.get_hitbox().height;

        int entityLeftCol = entityLeftX/sim.get_tileSize();
        int entityRightCol = entityRightX/sim.get_tileSize();
        int entityTopRow = entityTopY/sim.get_tileSize();
        int entityBottomRow = entityBottomY/sim.get_tileSize();

        int tile1, tile2;

        if(keyboard.PressedRT == true)
        {
            entityRightCol = (entityRightX + plr.get_moveSpeed())/sim.get_tileSize();
            tile1 = sim.Tile_c.mapTileNum[entityRightCol][entityTopRow];
            tile2 = sim.Tile_c.mapTileNum[entityRightCol][entityBottomRow];
            if (sim.Tile_c.tile[tile1].collision == true || sim.Tile_c.tile[tile2].collision == true){
                plr.set_canCollide(true);
            }
        }
        if(keyboard.PressedLF == true)
        {
            entityLeftCol = (entityLeftX - plr.get_moveSpeed())/sim.get_tileSize();
            tile1 = sim.Tile_c.mapTileNum[entityLeftCol][entityTopRow];
            tile2 = sim.Tile_c.mapTileNum[entityLeftCol][entityBottomRow];
            if (sim.Tile_c.tile[tile1].collision == true || sim.Tile_c.tile[tile2].collision == true){
                plr.set_canCollide(true);
            }
        }
        if(keyboard.PressedUp == true)
        {
            entityTopRow = (entityTopY - plr.get_moveSpeed())/sim.get_tileSize();
            tile1 = sim.Tile_c.mapTileNum[entityLeftCol][entityTopRow];
            tile2 = sim.Tile_c.mapTileNum[entityRightCol][entityTopRow];
            if (sim.Tile_c.tile[tile1].collision == true || sim.Tile_c.tile[tile2].collision == true){
                plr.set_canCollide(true);
            }
        }
        if(keyboard.PressedDown == true)
        {
            entityBottomRow = (entityBottomY + plr.get_moveSpeed())/sim.get_tileSize();
            tile1 = sim.Tile_c.mapTileNum[entityLeftCol][entityBottomRow];
            tile2 = sim.Tile_c.mapTileNum[entityRightCol][entityBottomRow];
            if (sim.Tile_c.tile[tile1].collision == true || sim.Tile_c.tile[tile2].collision == true){
                plr.set_canCollide(true);
            }
        }
    }

    // Checks tile for all generic animate entities
    public void checkTile(AnimateEntity entity){
        int entityLeftX = entity.get_coordinate_X() + entity.get_hitbox().x;
        int entityRightX = entity.get_coordinate_X() + entity.get_hitbox().x + entity.get_hitbox().width;
        int entityTopY = entity.get_coordinate_Y() + entity.get_hitbox().y;
        int entityBottomY = entity.get_coordinate_Y() + entity.get_hitbox().y + entity.get_hitbox().height;

        int entityLeftCol = entityLeftX/sim.get_tileSize();
        int entityRightCol = entityRightX/sim.get_tileSize();
        int entityTopRow = entityTopY/sim.get_tileSize();
        int entityBottomRow = entityBottomY/sim.get_tileSize();

        int tile1, tile2;

        switch(entity.get_direction()){
            case "up":
                entityTopRow = (entityTopY - entity.get_moveSpeed())/sim.get_tileSize();
                tile1 = sim.Tile_c.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = sim.Tile_c.mapTileNum[entityRightCol][entityTopRow];
                if (sim.Tile_c.tile[tile1].collision == true || sim.Tile_c.tile[tile2].collision == true){
                    entity.set_canCollide(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.get_moveSpeed())/sim.get_tileSize();
                tile1 = sim.Tile_c.mapTileNum[entityLeftCol][entityBottomRow];
                tile2 = sim.Tile_c.mapTileNum[entityRightCol][entityBottomRow];
                if (sim.Tile_c.tile[tile1].collision == true || sim.Tile_c.tile[tile2].collision == true){
                    entity.set_canCollide(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.get_moveSpeed())/sim.get_tileSize();
                tile1 = sim.Tile_c.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = sim.Tile_c.mapTileNum[entityLeftCol][entityBottomRow];
                if (sim.Tile_c.tile[tile1].collision == true || sim.Tile_c.tile[tile2].collision == true){
                    entity.set_canCollide(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.get_moveSpeed())/sim.get_tileSize();
                tile1 = sim.Tile_c.mapTileNum[entityRightCol][entityTopRow];
                tile2 = sim.Tile_c.mapTileNum[entityRightCol][entityBottomRow];
                if (sim.Tile_c.tile[tile1].collision == true || sim.Tile_c.tile[tile2].collision == true){
                    entity.set_canCollide(true);
                }
                break;
        }
    }
}
