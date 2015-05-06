package com.BombDefuser.Load;

import com.BombDefuser.Globals;
import com.BombDefuser.World.World;
import com.BombDefuser.World.Fans.EDirection;
import com.BombDefuser.World.Fans.FanTile;
import com.BombDefuser.World.Tiles.ETileTexture;
import com.BombDefuser.World.Tiles.PropTile;
import com.BombDefuser.World.Tiles.TileRec;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

import java.io.File;

public class Load {

	public static World mapToWorld(File file)
	{
		String[] lines = getStringInFile(file);
		World world = new World(Globals.GRAVITY);
		ELoad current = ELoad.NONE;
		for (String line : lines) {
            /*

            Edited by Robin, Android only supports Java 1.6; cannot have strings in switches.... :(

			switch (line) {
			case "[Tiles]": current = ELoad.TILES; continue;
			case "[Props]": current = ELoad.PROPS; continue;
			case "[Fans]": current = ELoad.FANS; continue;
			case "[Hero]": current = ELoad.HERO; continue;
			case "[Bomb]": current = ELoad.BOMB; continue;
			case "[Enemys]": current = ELoad.ENEMYS; continue;
			}*/

            if(line.equals("[Tiles]")){
                current = ELoad.TILES; continue;
            } else if(line.equals("[Props]")){
                current = ELoad.PROPS; continue;
            } else if(line.equals("[Fans]")){
                current = ELoad.FANS; continue;
            } else if(line.equals("[Hero]")){
                current = ELoad.HERO; continue;
            } else if(line.equals("[Bomb]")){
                current = ELoad.BOMB; continue;
            } else if(line.equals("[Enemys]")){
                current = ELoad.ENEMYS; continue;
            }


			switch (current) {
			case TILES:
				loadTiles(line, world);
				break;
			case PROPS:
				loadProps(line, world);
				break;
			case FANS:
				loadFans(line, world);
				break;
			case HERO:
				loadHero(line, world);
				break;
			case BOMB:
				loadBomb(line, world);
				break;
			case ENEMYS:
				loadEnemys(line, world);
				break;
			case NONE:	break;
			}
		}
		return world;
	}
	
	private static void loadTiles(String line, World world){
		String[] data = line.split(":");
		
		float x = Float.parseFloat(data[1]);
		float y = Float.parseFloat(data[2]);
		float width = Float.parseFloat(data[3]);
		float height = Float.parseFloat(data[4]);	
		ETileTexture type = ETileTexture.valueOf(data[5]);
		
		System.out.println(x + " " + y + " " + " " + width + " " + height);
		TileRec tile= new TileRec(type, x, y, width, height);

        /*
		switch (data[0]) {
		case "L": world.getLowerLayer().add(tile);
			break;
		case "M": world.getCollisionLayer().add(tile);	
			break;
		case "T": world.getTopLayer().add(tile);
			break;
		}*/
		
        if(data[0].equals("L")){
            world.getLowerLayer().add(tile);
        } else if(data[0].equals("M")){
            world.getCollisionLayer().add(tile);
        } else if(data[0].equals("T")){
            world.getTopLayer().add(tile);
        }

	}
	
	private static void loadProps(String line, World world){
		String[] data = line.split(":");
		
		int xSource = Integer.parseInt(data[0]);
		int ySource = Integer.parseInt(data[1]);
		float x = Float.parseFloat(data[2]);
		float y = Float.parseFloat(data[3]);
		float size = Float.parseFloat(data[4]);
		
		PropTile tile = new PropTile(xSource, ySource, x, y, size, size);
		world.getCollisionLayer().add(tile);
	}
	
	private static void loadFans(String line, World world){
		String[] data = line.split(":");
		
		float x = Float.parseFloat(data[0]);
		float y = Float.parseFloat(data[1]);
		float width = Float.parseFloat(data[2]);
		float height = Float.parseFloat(data[3]);
		float distance = Float.parseFloat(data[4]);
		EDirection direction = EDirection.valueOf(data[5]);
		
		FanTile fan;
		if(data.length == 7){
			float interval = Float.parseFloat(data[6]);
			fan = new FanTile(direction, x, y, width, height, distance, interval);
		}
		else
			fan = new FanTile(direction, x, y, width, height, distance);
		
		world.getCollisionLayer().add(fan);
	}
	private static void loadHero(String line, World world){
		String[] data = line.split(":");
		float x = Float.parseFloat(data[0]);
		float y = Float.parseFloat(data[1]);
		world.setHeroSpawn(new Vector2(x, y));
	}
	private static void loadEnemys(String line, World world){
		String[] data = line.split(":");
		
		float x = Float.parseFloat(data[0]);
		float y = Float.parseFloat(data[1]);
		world.addEnemy(new Vector2(x, y));
	}
	private static void loadBomb(String line, World world){
		String[] data = line.split(":");
		float x = Float.parseFloat(data[0]);
		float y = Float.parseFloat(data[1]);
		float time = Float.parseFloat(data[2]);
		world.setBomb(new Vector2(x, y), time);
	}
	
	private static String[] getStringInFile(File file)
	{
		FileHandle filehandler = Gdx.files.internal(file.getPath());
		String line = filehandler.readString();
		String[] list = line.split("\\r?\\n");
		return list;
	}
}
