package languagedetection.linguistics.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LanguageDetectionApplication {
	static LanguageDetector detector;
	public static void main(String[] args)
	{
		detector = new LanguageDetector();
		SpringApplication.run(LanguageDetectionApplication.class, args);
	}
	public static LanguageDetector getLanguageDetector()
	{
		return detector;
	}

}
