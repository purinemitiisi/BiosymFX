package lives;

import java.util.List;

public class Plant extends Life {
	public static int NUM;

	public Plant() {
		super();
	}
	public Plant(double x, double y, int r) {
		super(x, y, r);
	}


	@Override
	public void act(List<Life> list, List<Life> next) {
		hp += r/5;
		if (hp > mhp) {
			hp = mhp;
			if (NUM > 1000 || Math.random() > 0.5) return;

			double X = x + 20*(Math.random()-0.5);
			double Y = y + 20*(Math.random()-0.5);
			int    R = r + 10*(int) (Math.random()-0.5);
			X = Field.outChkX(X);
			Y = Field.outChkY(Y);
			for (Life life : Field.nearPlant(this)) {
				if ((X-life.x)*(X-life.x) + (Y-life.y)*(Y-life.y) < life.r*life.r)
					return;
			}
			next.add(new Plant(X, Y, R));
			hp /= 2;
		}
	}

}
