package vista;

import org.junit.jupiter.api.Test;

class UITest {

	@Test
	void test() {
		new Runnable() {
			boolean seguir = false;
			@Override
			public void run() {
				do {
					if (!seguir) {
						new Runnable() {
							public void run() {
								try {
									GUI frame = new GUI();
									frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}.run();
						seguir=true;
					}
				} while (seguir);
			}
		}.run();
	}

}
