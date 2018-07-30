package gui;

public class Point2 {
	
	public float x, y;
	public float outcome;
	
	public Point2(float x, float y) {
		this.x = x;
		this.y = y;
		
		if (y < x) {
			outcome = -1;
		}else {
			outcome = 1;
		}
	}

}
