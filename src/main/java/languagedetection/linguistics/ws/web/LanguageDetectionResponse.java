package languagedetection.linguistics.ws.web;

import opennlp.tools.langdetect.Language;

public class LanguageDetectionResponse {

	Language[] languages;

	public Language[] getLanguages() {
		return languages;
	}

	public void setLanguages(Language[] languages) {
		this.languages = languages;
	}
}
