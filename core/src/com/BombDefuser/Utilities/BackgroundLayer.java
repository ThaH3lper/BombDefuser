package com.BombDefuser.Utilities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class BackgroundLayer {

    private Texture texture;
    private Rectangle rec1, rec2, rec3;
    private float follow, yPadding;

    public BackgroundLayer(Texture texture, float follow, float yPadding)
    {
        this.follow = follow;
        this.yPadding = yPadding;
        this.texture = texture;
        this.rec1 = new Rectangle(0, yPadding, texture.getWidth()/2, texture.getHeight()/2);
        this.rec2 = new Rectangle(texture.getWidth()/2, yPadding, texture.getWidth()/2, texture.getHeight()/2);
        this.rec3 = new Rectangle(-texture.getWidth()/2, yPadding, texture.getWidth()/2, texture.getHeight()/2);
    }
    public void update(float delta, OrthographicCamera camera)
    {
        float camPos = camera.position.x - (camera.position.x * follow);
        int pos = (int) (camPos / (texture.getWidth()/2));
        rec1.x = (pos * texture.getWidth()/2) - (texture.getWidth()/2/2);
        rec2.x = ((pos + 1) * texture.getWidth()/2) - (texture.getWidth()/2 / 2);
        rec3.x = ((pos - 1) * texture.getWidth()/2) - (texture.getWidth()/2 / 2);
        rec1.x += camera.position.x * follow;
        rec2.x += camera.position.x * follow;
        rec3.x += camera.position.x * follow;
        rec1.y = camera.position.y + yPadding;
        rec2.y = camera.position.y + yPadding;
        rec3.y = camera.position.y + yPadding;
        System.out.println(pos + " " + camPos);
    }
    public void render(SpriteBatch batch)
    {
        batch.draw(texture, rec1.x, rec1.y, rec1.width, rec1.height);
        batch.draw(texture, rec2.x, rec2.y, rec2.width, rec2.height);
        batch.draw(texture, rec3.x, rec3.y, rec3.width, rec3.height);
    }
}
