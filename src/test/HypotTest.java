package test;

public class HypotTest {
	static double startTime;
	static double endTime;

	public static void main(String[] args) {
		startTime = System.currentTimeMillis();
		for (int i = 0; i<1000000; i++) {
			double x = Math.random();
			double y = Math.random();
		}
		endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);

		startTime = System.currentTimeMillis();
		for (int i = 0; i<1000000; i++) {
			double x = Math.random();
			double y = Math.random();
			double r = Math.hypot(x, y);
		}
		endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);

		startTime = System.currentTimeMillis();
		for (int i = 0; i<1000000; i++) {
			double x = Math.random();
			double y = Math.random();
			double r=x*x+y*y;
			r *= r;
		}
		endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);

		startTime = System.currentTimeMillis();
		for (int i = 0; i<1000000; i++) {
			double x = Math.random();
			double y = Math.random();
			double r=Math.pow(x, 2)+Math.pow(y, 2);
			r *= r;
		}
		endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);

		startTime = System.currentTimeMillis();
		for (int i = 0; i<1000000; i++) {
			double x = Math.random();
			double y = Math.random();
			double r=pow2(x)+pow2(y);
			r *= r;
		}
		endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}

	static double pow2(double x) {
		return x*x;
	}
}
