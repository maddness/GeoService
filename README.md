# Гео сервис

1. Генерация исходных таблиц

Скрипт для генерации таблиц - tables_generation/generator.py.
Входные параметры - диапазон широты, долготы и количество пользователей на сетке.
По диапазону создается координатная сетка с приблизительными расстояниями внутри ячеек.
Пользователи в случайном порядке раскидываются по сетке. На выходе получаем файлы - cells.csv и users.csv.

2. Запуск гео сервиса

Финальная версия приложения - release_candidate/geoservice-1.0.0.jar.
Необходимая версия JDK - 1.8
Для запуска необходимо сформировать таблицы на предыдущем этапе и передать абсолютные пути к таблицам в виде аргументов.
Пример запуска сервиса:

```sh
> java -jar geoservice-1.0.0.jar <path_to_cells_file> <path_to_users_file>
```

3. API

http://localhost:8080/cell_info?lat=1&lon=1   											- user count for a cell

http://localhost:8080/update_user?id=1&lat=38.608121&lon=6.017976   - update user with new coordinates

http://localhost:8080/location?id=1&lat=38.608121&lon=6.017976   		- user location details



