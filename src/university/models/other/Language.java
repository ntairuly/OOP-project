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


	//toStrings:
    add("User.toString", "User[id=%s, name=%s, email=%s]", 
        "Пользователь[id=%s, имя=%s, email=%s]", 
        "Пайдаланушы[id=%s, аты=%s, email=%s]");


	//UniversitySystem:
    add("UniversitySystem.getEmail", "User name(email): ",
        "Имя пользователя(email): ", 
        "Пайдаланушы есімі(email): ");

    add("UniversitySystem.getPw", "Password:",
        "Пароль:", 
        "Құпия сөз:");

    add("UniversitySystem.successlog", "Success of login\n",
        "Вход выполнен успешно\n", 
        "Жүйеге сәтті кірдіңіз\n");

    add("UniversitySystem.pwChange", "Change your password!\n",
        "Смените свой пароль!\n", 
        "Құпия сөзіңізді өзгертіңіз!\n");

    add("UniversitySystem.logError", 
        "The login information you entered is incorrect\n",
        "Введенные данные для входа неверны\n", 
        "Енгізілген логин мәліметтері қате\n");

    add("UniversitySystem.pwCur", "Current password:",
        "Текущий пароль:", 
        "Қазіргі құпия сөз:");

    add("UniversitySystem.pwNew", "New password: ",
        "Новый пароль: ", 
        "Жаңа құпия сөз: ");

    add("UniversitySystem.pwConfirm", "Confirm password: ",
        "Подтвердите пароль: ", 
        "Құпия сөзді растаңыз: ");
	
	add("UniversitySystem.logout", "Logout success",
		"Выход успешен", 
		"Шығу жасалынды");

	add("UniversitySystem.menuTitle", "\n - Menu - \n", 
		"\n - Меню - \n", 
		"\n - Мәзір - \n");

	add("UniversitySystem.menuOptions", "    Options", 
		"    Опции", 
		"    Опциялар");

	add("UniversitySystem.optionProfile", "1. Profile", 
		"1. Профиль", 
		"1. Профиль");

	add("UniversitySystem.optionPassword", "2. Change password", 
		"2. Сменить пароль", 
		"2. Құпия сөзді өзгерту");

	add("UniversitySystem.optionLogout", "3. Logout", 
		"3. Выйти", 
		"3. Шығу");

	add("UniversitySystem.chooseOption", "Choose option(number):", 
		"Выберите опцию(число):", 
		"Опцияны таңдаңыз(сан):");

	add("UniversitySystem.invalidOption", "This option doesn't exist \nPlease try again", 
		"Эта опция не существует \nПожалуйста, попробуйте еще раз", 
		"Бұл опция жоқ \nҚайтадан байқап көріңіз");

	//User
	add("User.pwUpdated", "Password updated", 
   		"Пароль обновлен", 
    	"Құпия сөз жаңартылды");

	add("User.pwTooShort", "Too short password", 
    	"Слишком короткий пароль", 
    	"Құпия сөз тым қысқа");

	add("User.pwMismatch", "Passwords mismatch.", 
    	"Пароли не совпадают.", 
    	"Құпия сөздер сәйкес келмейді.");

	add("User.pwIncorrect", "Incorrect current password", 
    	"Неверный текущий пароль", 
    	"Қазіргі құпия сөз қате");
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