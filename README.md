# Simple Template Engine
Простой шаблонизатор на Java для замены плейсхолдеров в текстовых шаблонах значениями из файлов данных.

## Возможности
- Замена плейсхолдеров формата ${key} в шаблонах
- Поддержка двух форматов данных: .properties и .json
- Автоматическое "уплощение" вложенных JSON структур в плоские ключи
- Гибкая конфигурация через конфигурационный файл
- Автоматическая сборка fat JAR через Shadow Plugin
- CI/CD на GitHub Actions с авто-релизами по тегам
- Полное покрытие тестами (JUnit 5, JaCoCo)

## Технологии
- Java 17 (совместимо с 17, 21)
- Gradle 8.14 + Wrapper
- JUnit 5, Mockito для тестирования
- Shadow JAR Plugin для сборки
- GitHub Actions для CI/CD

## Быстрый старт
### Через консоль (для разработчиков)
**Клонируем репозиторий**
```
cd Desktop
git clone https://github.com/beefmr/mock-data-generator.git
//переходим в проект
cd mock-data-generator
```
**Создаем конфигурационный файл**
```
cd src/main/resources
echo generator.rows.count=10 > app.properties
echo output.format=CSV >> app.properties
echo generator.fields=id:int(1,100000),user_name:name,user_email:email >> app.properties
```

**Запускаем приложение**
```
.\gradlew.bat shadowJar run
```
