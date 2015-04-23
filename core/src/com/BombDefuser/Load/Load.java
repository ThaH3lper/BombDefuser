package com.BombDefuser.Load;

import java.io.File;

import com.BombDefuser.Globals;
import com.BombDefuser.World.World;
import com.BombDefuser.World.Tiles.ETileTexture;
import com.BombDefuser.World.Tiles.TileRec;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

public class Load {

	public static World mapToWorld(File file)
	{
		String[] lines = getStringInFile(file);
		World world = new World(Globals.GRAVITY);
		ELoad current = ELoad.NONE;
		for (String line : lines) {
			switch (line) {
			case "[Tiles]": current = ELoad.TILES; continue;
			case "[Props]": current = ELoad.PROPS; continue;
			case "[Fans]": current = ELoad.FANS; continue;
			case "[Hero]": current = ELoad.HERO; continue;
			case "[Bomb]": current = ELoad.BOMB; continue;
			case "[Enemys]": current = ELoad.ENEMYS; continue;
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
		
		switch (data[0]) {
		case "L": world.getLowerLayer().add(tile);
			break;
		case "M": world.getCollisionLayer().add(tile);	
			break;
		case "T": world.getTopLayer().add(tile);
			break;
		}
	}
	
	private static void loadProps(String line, World world){
		
	}
	
	private static void loadFans(String line, World world){
		
	}
	private static void loadHero(String line, World world){
		String[] data = line.split(":");
		float x = Float.parseFloat(data[0]);
		float y = Float.parseFloat(data[1]);
		world.setHeroSpawn(new Vector2(x, y));
	}
	private static void loadEnemys(String line, World world){
		
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
