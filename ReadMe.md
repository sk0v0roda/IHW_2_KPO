# Второе индивидуальное домашнее задание по КПО
## Пономарев Степан Алексеевич, БПИ216
#### Что получилось:  
На вход программа принимает абсолютный путь на папку, которую нужно принять за корневую и начинает работу. В этой же папке программа создаёт (или перезаписывает) файл result.txt.

#### Особенности работы программы:
Разумеется, пути в require внутри текстовых файлов должны быть прописаны относительно корневой папки, вне зависимости от того, в каком файле находится require (это важно). В тестовой директории, которая  лежит в проекте, это достаточно наглядно продемонстрировано.

Программа не обрабатывает ошибки в циклических зависимостях и работает ТОЛЬКО при корректно написанных require (за это извините).