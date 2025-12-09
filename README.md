# Mock Data Generator
Генератор моковых данных на Java с поддержкой различных типов полей и вывода в CSV/JSON.

## Возможности
- Генерация тестовых данных различных типов
- Гибкая конфигурация через файл app.properties
- Поддержка форматов: CSV и JSON
- Полное покрытие тестами (JUnit 5 + Mockito)
- Детальная документация через JavaDoc
- Автоматическая сборка fat JAR через Shadow Plugin
- CI/CD на GitHub Actions с поддержкой JDK 17/21

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
cd ../../..
.\gradlew.bat shadowJar run
```
