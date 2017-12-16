package bundle;

import java.util.ResourceBundle;

public class Demo {

	public static void main(String[] args) {
		ResourceBundle rb = ResourceBundle.getBundle("params");
		String string = rb.getString("com.a");
		System.out.println(string);
	}
}
