package snak;

public class DeamonThread extends Thread{
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(getName()+"----"+i);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		DeamonThread d = new DeamonThread();
		DeamonThread d1 = new DeamonThread();
		d.setDaemon(true);
		d.start();
		//d1.start();
		try {
			Thread.sleep(7000);
			currentThread().stop();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
