package cosmos.webcompile.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

@Service
public class WebCompileServiceImpl implements WebCompileService {

	@Override
	public String compileResult(String wc_code) throws Exception {
		System.out.println(wc_code);
		Runtime wc_runtime = Runtime.getRuntime();
		String wc_javaName = wc_code.substring(13, wc_code.indexOf("{"));
		String wc_result = "";
		String wc_errResult = "";

		// class파일 삭제
		String wc_className = wc_javaName + ".class";
		System.out.println(wc_className);
		wc_fileDelete(wc_className);
		// java파일 생성
		File wc_java = new File(wc_javaName + ".java");
		System.out.println(wc_java);
		wc_java.createNewFile();

		FileWriter wc_writer = new FileWriter(wc_java);
		wc_writer.write(wc_code);
		wc_writer.close();

		Process javac_p = wc_runtime.exec("javac -encoding UTF-8 " + wc_java); // class파일
																				// 생성

		String wc_errStr = "";
		String wc_resultStr = "";

		// error출력
		BufferedReader wc_errorBF = new BufferedReader(new InputStreamReader(javac_p.getErrorStream()));
		while ((wc_errStr = wc_errorBF.readLine()) != null) {
			wc_errResult += wc_errStr + "\n";
		}

		if (wc_errResult != "")
			return wc_errResult;

		Process java_p = wc_runtime.exec("java  -Dfile.encoding=UTF-8 " + wc_javaName);

		// 성공 결과값 출력
		BufferedReader wc_inputBF = new BufferedReader(new InputStreamReader(java_p.getInputStream()));
		while ((wc_resultStr = wc_inputBF.readLine()) != null) {
			wc_result += wc_resultStr + "\n";
		}

		return wc_result;

	}

	public static void wc_fileDelete(String wc_deleteFileName) {
		File wc_file = new File(wc_deleteFileName);

		if (wc_file.exists()) {
			wc_file.delete();
		}
	}


}


