package main;

import entities.AnimateEntity;

import javax.swing.text.html.parser.Entity;

public class CheckCollision {

    Simulator gp;

    public CheckCollision(Simulator gp){
        this.gp = gp;
    }

    public void checkTile(AnimateEntity entity){
        int entityLeftX = entity.get_coordinate_X()+ 8;
        int entityRightX = entity.get_coordinate_X() + 8 + 32;
        int entityTopY = entity.get_coordinate_Y() + 16;
        int entityBottomY = entity.get_coordinate_Y() + 16 + 32;

        int entityLeftCol = entityLeftX/gp.get_tileSize();
        int entityRightCol = entityRightX/gp.get_tileSize();
        int entityTopRow = entityTopY/gp.get_tileSize();
        int entityBottomRow = entityBottomY/gp.get_tileSize();

        int tile1, tile2;

        switch(entity.get_direction()){
            case "up":
                entityTopRow = (entityTopY - entity.get_moveSpeed())/gp.get_tileSize();
                tile1 = gp.Tile_c.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gp.Tile_c.mapTileNum[entityRightCol][entityTopRow];
                if (gp.Tile_c.tile[tile1].collision == true || gp.Tile_c.tile[tile2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.get_moveSpeed())/gp.get_tileSize();
                tile1 = gp.Tile_c.mapTileNum[entityLeftCol][entityBottomRow];
                tile2 = gp.Tile_c.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.Tile_c.tile[tile1].collision == true || gp.Tile_c.tile[tile2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.get_moveSpeed())/gp.get_tileSize();
                tile1 = gp.Tile_c.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gp.Tile_c.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.Tile_c.tile[tile1].collision == true || gp.Tile_c.tile[tile2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.get_moveSpeed())/gp.get_tileSize();
                tile1 = gp.Tile_c.mapTileNum[entityRightCol][entityTopRow];
                tile2 = gp.Tile_c.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.Tile_c.tile[tile1].collision == true || gp.Tile_c.tile[tile2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
