package com.BombDefuser.Particle;

import java.util.ArrayList;
import java.util.List;

import com.BombDefuser.BombMain;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ParticleManager {

	private static List<IParticle> particles = new ArrayList<IParticle>();
	
	public static void addParticle(IParticle particle){
		particles.add(particle);
	}
	
	public static void addFanParticle(Vector2 position, Vector2 velocity, float distance){
		particles.add(new FanParticle(BombMain.assets.get("dot.png", Texture.class), velocity, position, 2, Color.DARK_GRAY, distance));
	}
	
	public static void Clear(){
		particles.clear();
	}
	
	public static void update(float delta){
		List<IParticle> remove = new ArrayList<IParticle>();
		for(IParticle particle : particles){
			particle.update(delta);
			if(particle.isDead())
				remove.add(particle);
		}
		for(IParticle particle : remove)
			particles.remove(particle);
		remove.clear();
	}
	
	public static void render(SpriteBatch batch){
		for(IParticle particle : particles)
			particle.render(batch);
	}
}
