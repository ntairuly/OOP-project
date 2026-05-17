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

	add("Admin.toString", "Admin[id=%s, name=%s, email=%s]",
		"Администратор[id=%s, имя=%s, email=%s]",
		"Администратор[id=%s, аты=%s, email=%s]");

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

	add("Student.toString","Student[id=%s, gpa=%.2f, year=%d, major=%s]",
		"Студент[id=%s, СРБ=%.2f, курс=%d, специальность=%s]",
		"Студент[id=%s, СРБ=%.2f, курс=%d, мамандық=%s]");

	add("GraduateStudent.toString", "GraduateStudent[id=%s, gpa=%.2f, supervisor=%s]",
		"GraduateStudent[id=%s, СРБ=%.2f, научный руководитель=%s]",
			"GraduateStudent[id=%s, СРБ=%.2f, жетекші=%s]");

	//Admin
	add("Admin.selectEmail", "Select user email: ",
		"Выберите email пользователя: ",
		"Пайдаланушының email-ін таңдаңыз: ");

	add("Admin.selectPassword", "Select user password: ",
		"Выберите пароль пользователя: ",
		"Пайдаланушының құпия сөзін таңдаңыз: ");

	add("Admin.selectOccupation", "Select user occupation(eng): ",
		"Выберите должность пользователя(eng): ",
		"Пайдаланушының лауазымын таңдаңыз(eng): ");

	add("Admin.occupationError", "Such occupation doesn't exist or grammatical error in input\n",
		"Такой должности не существует или ошибка в вводе\n",
		"Мұндай лауазым жоқ немесе енгізуде қателік бар\n");

	add("Admin.occupationCannotSelect", "This occupation cant be selected",
		"Эта должность не может быть выбрана",
		"Бұл лауазымды таңдау мүмкін емес");

	add("Admin.occupationAbstract", "Because it is abstract",
		"Потому что это абстрактный класс",
		"Өйткені ол абстрактты класс");

	add("Admin.logAddUser", "Added user: ",
		"Добавлен пользователь: ",
		"Пайдаланушы қосылды: ");

	add("Admin.userAddedSuccessfully", "User added succesfully",
		"Пользователь успешно добавлен",
		"Пайдаланушы сәтті қосылды");

	add("Admin.availableOccupations", "Available occupations:",
		"Доступные должности:",
		"Қол жетімді лауазымдар:");

	add("Admin.cannotBeSelected", "cant be selected",
		"не может быть выбрана",
		"таңдау мүмкін емес");

	add("Admin.enterEmailRemove", "Enter email of user to remove: ",
		"Введите email пользователя для удаления: ",
		"Жойылатын пайдаланушының email-ін енгізіңіз: ");

	add("Admin.logRemoveUser", "Removed user: ",
		"Удален пользователь: ",
		"Пайдаланушы жойылды: ");

	add("Admin.userRemovedSuccessfully", "User removed succesfully",
		"Пользователь успешно удален",
		"Пайдаланушы сәтті жойылды");

	add("Admin.userNotRemoved", "User wasn't removed",
		"Пользователь не был удален",
		"Пайдаланушы жойылмады");

	add("Admin.userDoesNotExist", "because it doesnt exist",
		"потому что он не существует",
		"өйткені ол жоқ");

	add("Admin.enterEmailUpdate", "Enter email of user to update: ",
		"Введите email пользователя для обновления: ",
		"Жаңартылатын пайдаланушының email-ін енгізіңіз: ");

	add("Admin.userNotFound", "User not found: ",
		"Пользователь не найден: ",
		"Пайдаланушы табылмады: ");

	add("Admin.whatToUpdate", "What to update? (id / firstname / lastname / email)",
		"Что обновить? (id / firstname / lastname / email)",
		"Не жаңартау керек? (id / firstname / lastname / email)");

	add("Admin.newId", "New id: ",
		"Новый id: ",
		"Жаңа id: ");

	add("Admin.newFirstName", "New first name: ",
		"Новое имя: ",
		"Жаңа аты: ");

	add("Admin.newLastName", "New last name: ",
		"Новая фамилия: ",
		"Жаңа тегі: ");

	add("Admin.newEmail", "New email: ",
		"Новый email: ",
		"Жаңа email: ");

	add("Admin.unknownField", "Unknown field",
		"Неизвестное поле",
		"Белгісіз өріс");

	add("Admin.logUpdateUser", "Updated user: ",
		"Обновлен пользователь: ",
		"Пайдаланушы жаңартылды: ");

	add("Admin.logField", " field=",
		" поле=",
		" өріс=");

	add("Admin.userUpdatedSuccessfully", "User updated successfully",
		"Пользователь успешно обновлен",
		"Пайдаланушы сәтті жаңартылды");

	add("Admin.adminLogs", "--Admin logs--",
		"--Логи админа--",
		"--Админ журналы--");

	add("Admin.menu", """
		--Admin panel--\n
		add    - add new User\n
		remove - remove User\n
		update - update User info\n
		logs   - view logs\n
		-- account -- \n
		change - change password \n
		info   - get info about yourself\n
		""",
		"""
		--Панель администратора--\n
		add    - добавить нового пользователя\n
		remove - удалить пользователя\n
		update - обновить информацию пользователя\n
		logs   - просмотр логов\n
		-- account -- \n
		change - изменить пароль \n
		info   - получить информацию о себе\n""",
		"""
		--Админ панелі--\n
		add    - жаңа пайдаланушы қосу\n
		remove - пайдаланушыны жою\n
		update - пайдаланушы ақпаратын жаңарту\n
		logs   - журналдарды көру\n
		-- account -- \n
		change - құпия сөзді өзгерту \n
		info   - өзіңіз туралы ақпарат алу\n""");

	add("Admin.notification", "Admin notification: ",
		"Уведомление администратора: ",
		"Администратордың хабарламасы: ");
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