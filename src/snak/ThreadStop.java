package snak;

public class ThreadStop extends Thread{
	public void run() {
		String name = getName();
		for (int i = 0; i < 5; i++) {
			System.out.println(name + "---" + i);
			try {
				Thread.sleep(2000);
				//stop();
				interrupt();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		System.out.println(name+"Ö´ÐÐÍê±Ï");
	}
	public static void main(String[] args) {
		ThreadStop t = new ThreadStop();
		ThreadStop b = new ThreadStop();
		t.start();
		b.start();
		try {
			System.out.println(Thread.currentThread().getName());
			Thread.currentThread().sleep(3000);
			Thread.currentThread().stop();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
