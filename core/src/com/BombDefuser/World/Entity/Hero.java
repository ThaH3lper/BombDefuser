package com.BombDefuser.World.Entity;

import com.BombDefuser.BombMain;
import com.BombDefuser.SoundManager.ESounds;
import com.BombDefuser.StateSystem.EScreen;
import com.BombDefuser.Utilities.Animation;
import com.BombDefuser.Utilities.Button;
import com.BombDefuser.World.World;
import com.BombDefuser.World.Entity.Weapon.TaserGun;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hero extends MoveableEntity{

	private final static float drawWidth = 32, drawHeight = 32;
	private final static float hitWidth = 22, hitHeight = 32;
	
	TaserGun taser;
	
	Animation run, runTaser, idle, turn, current, idleFire;
	boolean turnDone;

    private Button btnLeft, btnRight, btnA, btnB;

	public Hero(float x, float y, World world) {
		super(drawWidth, drawHeight, x, y, hitWidth, hitHeight, world);
		
		run = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 5, 14, 0, 64, 64, 4, 4, 2, 2, 0.06f);
		runTaser = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 5, 14, 1, 64, 64, 4, 4, 2, 2, 0.06f);
		idle = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 0, 22, 2, 64, 64, 4, 4, 2, 2, 0.08f);
		turn = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 0, 4, 0, 64, 64, 4, 4, 2, 2, 0.04f);
		idleFire = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 0, 0, 3, 64, 64, 4, 4, 2, 2, 1f);
		
		current = turn;
		setTexture(current.getTexture());
		taser = new TaserGun(this);
	}
	
	public void createAndroidButtons(OrthographicCamera hudCamera){
        btnLeft = new Button(hudCamera, BombMain.assets.get("left.jpg", Texture.class), 0, 0);
        btnRight = new Button(hudCamera, BombMain.assets.get("right.jpg", Texture.class), 150, 0);
        btnA = new Button(hudCamera, BombMain.assets.get("A.jpg", Texture.class), 1280-(150*2), 0);
        btnB = new Button(hudCamera, BombMain.assets.get("B.jpg", Texture.class), 1280-150, 0);
        
        btnLeft.setBounds(150, 150);
        btnRight.setBounds(150, 150);
        btnA.setBounds(150, 150);
        btnB.setBounds(150, 150);


        float alpha = 0.6f;
        btnLeft.setAlpha(alpha);
        btnRight.setAlpha(alpha);
        btnA.setAlpha(alpha);
        btnB.setAlpha(alpha);
	}
	
	@Override
	public void update(float delta){
		switch (Gdx.app.getType()){
            case Android:
                updateAndroidControls(delta);
                break;
            case Desktop:
                updateDesktopControls(delta);
                break;
            case HeadlessDesktop:
                break;
            case Applet:
                break;
            case WebGL:
                break;
            case iOS:
                break;
        }
		current.update(delta);
		setRecSource(current.getRecSource());
		super.update(delta);
		taser.update(delta);

		// Taser hit enemy logic
		if(taser.isActive()){
			BombMain.soundBank.playSound(ESounds.taser);
			
			// Check if the tazer 'bullet' hits the enemy
			for(int i = 0; i < world.getEnemies().size(); i++){
				
				if(taser.getBullet().getRecDraw().overlaps(world.getEnemies().get(i).hitBox)){
					if(!world.TileBetweenVectors(this.getCenterPosition(), world.getEnemies().get(i).getCenterPosition()))
						world.getEnemies().get(i).setHit(true);
					else
						taser.deactivate();
				} else{
					world.getEnemies().get(i).setHit(false);
				}
			}
		}else{
			BombMain.soundBank.stopTaser();
			
			// deactivates the tazer so enemies dont take damage
			for(int i = 0; i < world.getEnemies().size(); i++){
				world.getEnemies().get(i).setHit(false);
			}
		}
	}
	
	public void updateAndroidControls(float delta){
		// A button
		if(btnA.isHoldDown())
			Jump();
		// B button
		if(btnB.isHoldDown()){
			if(world.getBomb().getHitbox().overlaps(hitBox) && world.getEnemies().size() == 0){
				BombMain.stateManager.setState(EScreen.defuse);
			}else{
				taser.fire(delta);
			}
		}else{
			if(taser.isActive())
				taser.deactivate();
		}
		
		// C & D buttons
		if(btnLeft.isHoldDown() || btnRight.isHoldDown()){
			if(taser.getBullet() != null)
				current = runTaser;
			else{
				if(turnDone){
					if(taser.isActive())
						current = runTaser;
					else
						current = run;
				}
				else
				{
					current = turn;
					if(turn.getTimes() >= 1)
					{
						turnDone = true;
					}
				}
			}
			if(btnRight.isHoldDown()){
				MoveRight();
				setXFliper(false);
			}
			else if(btnLeft.isHoldDown()){
				MoveLeft();
				setXFliper(true);
			}
		}
		else{
			if(taser.getBullet() != null)
				current = idleFire;
			else{
				if(current != idle){
					idle.resetTimes();
					current = idle;
				}
				turnDone = false;
				turn.resetTimes();
			}
		}
	}
	
	public void updateDesktopControls(float delta){
		// A button
		if(Gdx.input.isKeyPressed(Keys.W))
			Jump();
		// B button
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			if(world.getBomb().getHitbox().overlaps(hitBox) && world.getEnemies().size() == 0){
				BombMain.stateManager.setState(EScreen.defuse);
			}else{
				taser.fire(delta);
			}
		}else{
			if(taser.isActive())
				taser.deactivate();
		}
		
		// C & D buttons
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.D)){
			if(taser.getBullet() != null)
				current = runTaser;
			else{
				if(turnDone){
					if(taser.isActive())
						current = runTaser;
					else
						current = run;
				}
				else
				{
					current = turn;
					if(turn.getTimes() >= 1)
					{
						turnDone = true;
					}
				}
			}
			if(Gdx.input.isKeyPressed(Keys.A)){
				MoveLeft();
				setXFliper(true);
			}
			else if(Gdx.input.isKeyPressed(Keys.D)){
				MoveRight();
				setXFliper(false);
			}
		}
		else{
			if(taser.getBullet() != null)
				current = idleFire;
			else{
				if(current != idle){
					idle.resetTimes();
					current = idle;
				}
				turnDone = false;
				turn.resetTimes();
			}
		}
	}

    public void renderAndroidButtons(SpriteBatch batch){
        batch.setColor(new Color(1, 1, 1, 100));
        btnLeft.render(batch);
        btnRight.render(batch);
        btnA.render(batch);
        btnB.render(batch);
        batch.setColor(Color.WHITE);
    }

	@Override
	public void render(SpriteBatch batch)
	{
		taser.render(batch);
		super.render(batch);
	}
	
	public TaserGun getGun(){
		return taser;
	}
}
