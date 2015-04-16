package com.BombDefuser.World.Entity;

import com.BombDefuser.BombMain;
import com.BombDefuser.StateSystem.EScreen;
import com.BombDefuser.Utilities.Animation;
import com.BombDefuser.Utilities.Button;
import com.BombDefuser.Utilities.TaserGun;
import com.BombDefuser.World.World;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
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
		
		run = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 5, 14, 0, 64, 64, 0.06f);
		runTaser = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 5, 14, 1, 64, 64, 0.06f);
		idle = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 0, 22, 2, 64, 64, 0.08f);
		turn = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 0, 4, 0, 64, 64, 0.04f);
		idleFire = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 0, 0, 3, 64, 64, 1f);
		
		current = turn;
		setTexture(current.getTexture());
		taser = new TaserGun(this);

        if(Gdx.app.getType() == Application.ApplicationType.Android){
            btnLeft = new Button(BombMain.stateManager.getHudCamera(), BombMain.assets.get("arrow.png", Texture.class), 0, 100);
            btnRight = new Button(BombMain.stateManager.getHudCamera(), BombMain.assets.get("arrow.png", Texture.class), 100, 0);
            btnA = new Button(BombMain.stateManager.getHudCamera(), BombMain.assets.get("A.png", Texture.class), 1280-200, 0);
            btnB = new Button(BombMain.stateManager.getHudCamera(), BombMain.assets.get("B.png", Texture.class), 1280-100, 100);
        }
	}
	
	@Override
	public void update(float delta){
		switch (Gdx.app.getType()){
            case Android:
                updateAndoridControls(delta);
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
			for(int i = 0; i < world.getEnemies().size(); i++){
				if(taser.getBullet().getRecDraw().overlaps(world.getEnemies().get(i).hitBox)){
					world.getEnemies().get(i).setHit(true);
				}
			}
		}else{
			for(int i = 0; i < world.getEnemies().size(); i++){
				if(taser.getBullet().getRecDraw().overlaps(world.getEnemies().get(i).hitBox)){
					world.getEnemies().get(i).setHit(false);
				}
			}
		}
	}

    public void updateAndoridControls(float delta){
        // A button
        if(btnA.isPressed())
            Jump();
        // B button
        if(btnB.isPressed()){
            if(world.getBomb().getHitbox().overlaps(hitBox)){
                BombMain.stateManager.setState(EScreen.defuse);
            }else{
                taser.fire(delta);
            }
        }

        // C & D buttons
        if(btnLeft.isPressed() || btnRight.isPressed()){
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
            if(btnLeft.isPressed()){
                MoveLeft();
                setXFliper(true);
            }
            else if(btnRight.isPressed()){
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

	public void updateDesktopControls(float delta){
		// A button
		if(Gdx.input.isKeyPressed(Keys.W))
			Jump();
		// B button
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			if(world.getBomb().getHitbox().overlaps(hitBox)){
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
