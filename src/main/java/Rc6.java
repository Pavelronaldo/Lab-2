public class Rc6 {

	static int w = 32, r = 20;
	static int[] S;
	static int Pw = 0xb7e15163, Qw = 0x9e3779b9;

	public static byte[] stringToByte(String str) {

		byte[] b = str.getBytes();
		return b;
	}

	public static int[] keySchedule(byte[] key) {


		int[] S = new int[2 * r + 4];
		S[0] = Pw;

		int c = key.length / (w / 8);
		int[] L = bytestoWords(key, c);

		for (int i = 1; i < (2 * r + 4); i++) {

			S[i] = S[i - 1] + Qw;
		}

		int A, B, i, j;

		A = B = i = j = 0;

		int v = 3 * Math.max(c, (2 * r + 4));

		for (int s = 0; s < v; s++) {

			A = S[i] = rotateLeft((S[i] + A + B), 3);
			B = L[j] = rotateLeft(L[j] + A + B, A + B);
			i = (i + 1) % (2 * r + 4);
			j = (j + 1) % c;

		}

		return S;
	}

	public static byte[] intToByte(int[] array) {

		byte[] byteArray = new byte[array.length];
		for (int i = 0; i < array.length; i++) {

			byteArray[i] = (byte) (array[i]);
		}

		return byteArray;
	}

	private static int[] bytestoWords(byte[] userkey, int c) {

		int[] bytes_to_words = new int[c];

		for (int i = 0, off = 0; i < c; i++){

			bytes_to_words[i] = ((userkey[off++] & 0xFF)) | ((userkey[off++] & 0xFF) << 8)
				| ((userkey[off++] & 0xFF) << 16) | ((userkey[off++] & 0xFF) << 24);
		}

		return bytes_to_words;
	}

	private static int rotateLeft(int val, int pas) {

		return (val << pas) | (val >>> (32 - pas));
	}

	private static int rotateRight(int val, int pas) {

		return (val >>> pas) | (val << (32 - pas));
	}
}