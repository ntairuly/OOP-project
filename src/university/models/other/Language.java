package university.models.other;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Language {
	INSTANCE;

	private final Map<String, String> ruTranslations = new HashMap<>();
	private final Map<String, String> enTranslations = new HashMap<>();
	private final Map<String, String> kzTranslations = new HashMap<>();

	private String curLanguage = "EN";

	Language() {
		// ADD translations here like
		// path(class name + purpose), en, ru, kz
		// you can ACCESS words from Language.INSTANCE.get(path)
		add("User.toString", "User[id=%s, name=%s, email=%s]", 
		"Пользователь[id=%s, имя=%s, email=%s]", 
		"Пайдаланушы[id=%s, аты=%s, email=%s]" );
	}
	
	public void setLanguage(String newLanguage){
		if (List.of("EN", "RU", "KZ").contains(newLanguage.toUpperCase())) {
			curLanguage = newLanguage.toUpperCase();
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	private void add(String path, String en, String ru, String kz){
		enTranslations.put(path, en);
		ruTranslations.put(path, ru);
		kzTranslations.put(path, kz);
	}

	public String get(String path) {
		Map<String, String> selTranslations;

		switch (curLanguage){
			case "RU": 
				selTranslations = ruTranslations;
				break;
			case "KZ":
				selTranslations = kzTranslations;
				break;
			default:
				selTranslations = enTranslations;
				break;
		}

		return selTranslations.get(path);
	}
}