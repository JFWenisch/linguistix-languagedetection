package languagedetection.linguistics.ws.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import languagedetection.linguistics.ws.LanguageDetectionApplication;
import opennlp.tools.langdetect.Language;

@RestController
@EnableAutoConfiguration
public class Controller {

	@PostMapping( path = "/")
	public ResponseEntity<LanguageDetectionResponse> process(@RequestBody LanguageDetectionRequest input) 
			throws Exception 
	{
		Language[] predictedLanguages = LanguageDetectionApplication.getLanguageDetector().predictLanguage(input.getText());
		LanguageDetectionResponse response = new LanguageDetectionResponse();
		response.setLanguages(predictedLanguages);
		 return new ResponseEntity<LanguageDetectionResponse>(response, HttpStatus.OK);

	}
}
