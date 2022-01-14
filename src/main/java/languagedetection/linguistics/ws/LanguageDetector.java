package languagedetection.linguistics.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import opennlp.tools.langdetect.Language;
import opennlp.tools.langdetect.LanguageDetectorME;
import opennlp.tools.langdetect.LanguageDetectorModel;

public class LanguageDetector 
{
	static LanguageDetectorModel languageModel;
	static HashMap<String, String> languageMapping;
	LanguageDetectorME languageDetector;

	LanguageDetector()
	{
		try 
		{
			initModel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			initMapping();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		languageDetector = new LanguageDetectorME(languageModel);
	}

	public Language[] predictLanguage(String input)
	{
		Language[] languages = languageDetector.predictLanguages(input);

		for(Language language:languages){
			System.out.println(getLanguage(language.getLang())+"  confidence:"+language.getConfidence());
		}
		return languages;
	}


	private String getLanguage(String langCode) {

		return languageMapping.get(langCode);

	}
	public void initModel() throws IOException
	{

		languageModel = new LanguageDetectorModel(getClass().getResourceAsStream("/langdetect-183.bin"));

	}

	private void initMapping() throws IOException {
		languageMapping= new HashMap<String, String>();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				this.getClass().getResourceAsStream("/language_mapping.txt")));
		String line = "";
		try
		{
			while ((line = in.readLine()) != null) {
				String parts[] = line.split("\t");
				languageMapping.put(parts[0], parts[1]);
			}
		}
		finally
		{	in.close();

		}




	}
}
