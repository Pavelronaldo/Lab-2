import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		String key_value;
		key_value = "02 13 24 35 46 57 68 79 8a 9b ac bd ce df e0 f1";

		System.out.println(Arrays.toString(Rc6.intToByte(Rc6.keySchedule(Rc6.stringToByte(key_value)))));
	}
}
