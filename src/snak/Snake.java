package snak;

public class Snake {
	int x =100;
	int y = 100;
	int w = 20;
	
	public Snake(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}

	public void snake (int x ,int y){
		this.x = x;
		this.y = y;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Snake b = (Snake) obj;
		if(this.x>=b.x&&this.x<=b.x+30&&this.y>=b.y&&this.y<=b.y+30){
			return true;
		}else{
		return false;
		}
	}
}
