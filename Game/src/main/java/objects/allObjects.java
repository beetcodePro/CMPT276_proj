// package objects;

// import main.Simulator;

// import java.awt.*;
// import java.awt.image.BufferedImage;
// import java.util.Random;



// public class allObjects {
//     public BufferedImage image;
//     public String name;
//     public boolean collision= false;
//     public int world_x, world_y;

//     public void draw(Graphics2D g, Simulator sim)
//     {
//     //This needs to be fixed to place the bananas randomly
//         int column=1;
//         int row=10;
//         int x=48;
//         int y=48;
//         int count=0;
//         while (column<sim.maxScreenCol && row< sim.maxScreenRow && count< sim.num_of_bananas )
//         {
//             Random num= new Random();
//             int factor = num.nextInt(6) + 5;
//             g.drawImage(image,x,y, sim.get_tileSize(),sim.get_tileSize(), null);
//             count ++ ;
//             column++;
//             x+= sim.get_tileSize()*2;
//             if (column== sim.maxScreenCol)
//             {
//                 column=0;
//                 x=0;
//                 row++;
//                 y+= sim.get_tileSize();

//             }
//         }
//     }


// }
