package main;

import entities.*;

public class CheckCollision 
{
    Simulator sim;
    KeyBoard keyboard;
    EntityList entityList;

    // Default constructor
    public CheckCollision(Simulator sim, KeyBoard key, EntityList eList){
        this.sim = sim;
        this.keyboard = key;
        this.entityList = eList;
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
            case "down":
                entityBottomRow = (entityBottomY + entity.get_moveSpeed())/sim.get_tileSize();
                tile1 = sim.Tile_c.mapTileNum[entityLeftCol][entityBottomRow];
                tile2 = sim.Tile_c.mapTileNum[entityRightCol][entityBottomRow];
                if (sim.Tile_c.tile[tile1].collision == true || sim.Tile_c.tile[tile2].collision == true){
                    entity.set_canCollide(true);
                }
                break;
            case "up":
                entityTopRow = (entityTopY - entity.get_moveSpeed())/sim.get_tileSize();
                tile1 = sim.Tile_c.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = sim.Tile_c.mapTileNum[entityRightCol][entityTopRow];
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
            case "left":
                entityLeftCol = (entityLeftX - entity.get_moveSpeed())/sim.get_tileSize();
                tile1 = sim.Tile_c.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = sim.Tile_c.mapTileNum[entityLeftCol][entityBottomRow];
                if (sim.Tile_c.tile[tile1].collision == true || sim.Tile_c.tile[tile2].collision == true){
                    entity.set_canCollide(true);
                }
                break;
        }
    }

    // Checks AnimateEntity to Enemy collisions
    public int checkEnemy(AnimateEntity entity)
    {
        int index = -1;

        for(int i = 0; i < this.entityList.get_enemyList_size(); i++)
        {
            if(this.entityList.get_enemy_at_index(i) != null)
            {
                // Get enemy at index in entityList
                Enemy enemy = this.entityList.get_enemy_at_index(i);

                // Store entity hitbox original value
                int entityOriginalX = entity.get_hitbox().x;
                int entityOriginalY = entity.get_hitbox().y;
                int enemyOriginalX = enemy.get_hitbox().x;
                int enemyOriginalY = enemy.get_hitbox().y;

                // Get entity hitbox position
                entity.set_hitbox_x(entity.get_coordinate_X() - entity.get_hitbox().x);
                entity.set_hitbox_y(entity.get_coordinate_Y() - entity.get_hitbox().y);

                // Get enemy hitbox position
                enemy.set_hitbox_x(enemy.get_coordinate_X() - enemy.get_hitbox().x);
                enemy.set_hitbox_y(enemy.get_coordinate_Y() - enemy.get_hitbox().y);

                // Check for collision based on direction
                switch(entity.get_direction())
                {
                    case "down":
                        entity.set_hitbox_y(entity.get_hitbox().y + entity.get_moveSpeed());
                        if(entity.get_hitbox().intersects(enemy.get_hitbox()))
                        {
                            entity.set_canCollide(true);
                            index = i;
                        }
                        break;
                    case "up":
                        entity.set_hitbox_y(entity.get_hitbox().y - entity.get_moveSpeed());
                        if(entity.get_hitbox().intersects(enemy.get_hitbox()))
                        {
                            entity.set_canCollide(true);
                            index = i;
                        }
                        break;
                    case "right":
                        entity.set_hitbox_x(entity.get_hitbox().x + entity.get_moveSpeed());
                        if(entity.get_hitbox().intersects(enemy.get_hitbox()))
                        {
                            entity.set_canCollide(true);
                            index = i;
                        }
                        break;
                    case "left":
                        entity.set_hitbox_x(entity.get_hitbox().x - entity.get_moveSpeed());
                        if(entity.get_hitbox().intersects(enemy.get_hitbox()))
                        {
                            entity.set_canCollide(true);
                            index = i;
                        }
                        break;
                }
                // Reset entity hitbox position
                entity.set_hitbox_x(entityOriginalX);
                entity.set_hitbox_y(entityOriginalY);
                enemy.set_hitbox_x(enemyOriginalX);
                enemy.set_hitbox_y(enemyOriginalY);
            }
        }
        return index;
    }

    // Checks AnimateEntity to Player collisions
    public void checkPlayerForAnimateEntity(AnimateEntity entity)
    {
        Player player = sim.get_player();

        // Store entity hitbox original value
        int entityOriginalX = entity.get_hitbox().x;
        int entityOriginalY = entity.get_hitbox().y;
        int playerOriginalX = player.get_hitbox().x;
        int playerOriginalY = player.get_hitbox().y;

        // Get entity hitbox position
        entity.set_hitbox_x(entity.get_coordinate_X() - entity.get_hitbox().x);
        entity.set_hitbox_y(entity.get_coordinate_Y() - entity.get_hitbox().y);

        // Get enemy hitbox position
        player.set_hitbox_x(player.get_coordinate_X() - player.get_hitbox().x);
        player.set_hitbox_y(player.get_coordinate_Y() - player.get_hitbox().y);

        // Check for collision based on direction
        switch(entity.get_direction())
        {
            case "down":
                entity.set_hitbox_y(entity.get_hitbox().y + entity.get_moveSpeed());
                if(entity.get_hitbox().intersects(player.get_hitbox()))
                {
                    entity.set_canCollide(true);
                }
                break;
            case "up":
                entity.set_hitbox_y(entity.get_hitbox().y - entity.get_moveSpeed());
                if(entity.get_hitbox().intersects(player.get_hitbox()))
                {
                    entity.set_canCollide(true);
                }
                break;
            case "right":
                entity.set_hitbox_x(entity.get_hitbox().x + entity.get_moveSpeed());
                if(entity.get_hitbox().intersects(player.get_hitbox()))
                {
                    entity.set_canCollide(true);
                }
                break;
            case "left":
                entity.set_hitbox_x(entity.get_hitbox().x - entity.get_moveSpeed());
                if(entity.get_hitbox().intersects(player.get_hitbox()))
                {
                    entity.set_canCollide(true);
                }
                break;
        }
        // Reset entity hitbox position
        entity.set_hitbox_x(entityOriginalX);
        entity.set_hitbox_y(entityOriginalY);
        player.set_hitbox_x(playerOriginalX);
        player.set_hitbox_y(playerOriginalY);
    }
}
