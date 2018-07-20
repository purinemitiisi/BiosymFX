package lives;

import java.util.List;

public abstract class Life {
	int mhp;
	int hp;
	public int r;
	public double x;
	public double y;


	public Life() {
		x = 600*Math.random();
		y = 600*Math.random();
		r = (int) (40*Math.random()) + 10;
		r = 10;
		mhp = r*r;
		hp = r*r/2;

	}

	public Life(double x, double y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.r = 10;
		mhp = r*r;
		hp = r*r/2;
	}

	abstract public void act(List<Life> list, List<Life> next);

	public boolean isDead() {
		return hp <= 0 ? true : false;
	}

}
