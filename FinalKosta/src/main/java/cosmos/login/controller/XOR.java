package cosmos.login.controller;

public class XOR {
	
	public String XOR(String code) {
		// 암호화 키
		byte keyChar[] = { 0x01, 0x03, 0x01, 0x05, 0x01, 0x03, 0x01, 0x01 };

		// 암호화할 대상
		byte codeChar[] = new byte[code.getBytes().length]; // code의 문자열 길이만큼의
															// 배열을 만든다.
		codeChar = code.getBytes(); // code를 Byte형으로 변환한다.

		// XOR 연산
		for (int i = 0, j = 0; i < code.getBytes().length; i++) {
			codeChar[i] = (byte) (codeChar[i] ^ keyChar[j]); // code의 한문자와 key의
																// 한문자를
																// ^(XOR)연산을 한후
																// byte형으로 변환한다.
			j = (++j < keyChar.length ? j : 0); // j의 값이 key문자열의 길이보다 커질경우 0으로
												// 아닐경우는 j의 값을 갖는다.
		}

		return new String(codeChar); // byte배열인 code를 String으로 변환하여 반환한다.
	}
}