package lives;

import java.util.ArrayList;
import java.util.List;

public class Field {
	static final double X = 600;
	static final double Y = 600;
	static List<Plant>[][] pGrid  = new List[60][60];
	static List<Animal>[][] aGrid = new List[60][60];
	static {
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 60; j++) {
				pGrid[i][j] = new ArrayList<Plant>();
				aGrid[i][j] = new ArrayList<Animal>();
			}
		}
	}

	public static void update(List<Life> list) {
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 60; j++) {
				pGrid[i][j].clear();
				aGrid[i][j].clear();
			}
		}
		for (Life life : list) setLife(life);
	}

	public static void setLife(Life life) {
		int x = (int) (life.x/X*60);
		int y = (int) (life.y/Y*60);
		if (life.getClass() == Plant.class)  pGrid[x][y].add((Plant)  life);
		if (life.getClass() == Animal.class) aGrid[x][y].add((Animal) life);
	}

	public static List<Plant> nearPlant(Life life) {
		List<Plant> res = new ArrayList<Plant>();
		int x0 = (int) (life.x/X*60);
		int y0 = (int) (life.y/Y*60);
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				int x = x0 + i;
				int y = y0 + j;
				if (x >= 60) x -= 60;
				if (x <   0) x += 60;
				if (y >= 60) y -= 60;
				if (y <   0) y += 60;
				res.addAll(pGrid[x][y]);
			}
		}

		return res;
	}

	public static double outChkX(double x) {
		if (x > X) x -= X;
		if (x < 0) x += X;
		return x;
	}

	public static double outChkY(double y) {
		if (y > Y) y -= Y;
		if (y < 0) y += Y;
		return y;
	}
}
