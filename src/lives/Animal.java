package lives;

import java.util.ArrayList;
import java.util.List;

public class Animal extends Life {
	public static int NUM;
	static List<Animal> activePool = new ArrayList<Animal>(500);
	static List<Animal> Pool = new ArrayList<Animal>(500);
	double vx;
	double vy;
	int time;
	int speed = 10;

	public Animal() {
		super();
	}

	public Animal(double x, double y, int r) {
		super(x, y, r);
	}

	@Override
	public void act(List<Life> list, List<Life> next) {
		if (hp > mhp) {
			hp=mhp;
			if (NUM > 500) return;
			double X = x + 20*(Math.random()-0.5);
			double Y = y + 20*(Math.random()-0.5);
			int    R = r + 10*(int) (Math.random()-0.5);
//			for (Life life : Field.nearPlant(this)) {
//				if ((X-life.x)*(X-life.x) + (Y-life.y)*(Y-life.y) < 25) {
//					move();
//					return;
//				}
//			}
			next.add(new Animal(X, Y, R));
			hp /= 2;
			return;
		}

		for (Life life : list) {
			if (life.getClass() == Plant.class && isHitted(life)) {
				eat((Plant) life);
				return;
			}
		}

		move();
	}

	void move() {
		if (time == 0) {
			vx = Math.sqrt(2)*speed*(Math.random()-0.5);
			vy = Math.sqrt(2)*speed*(Math.random()-0.5);
			time = (int) (10*(Math.random()));
		}
		x = Field.outChkX(x+vx);
		y = Field.outChkY(y+vy);
		time--;
		hp -= (int) speed * r/20 + 1;
	}

	void eat(Plant p) {
		p.hp -= r;
		hp += r;
	}
}
