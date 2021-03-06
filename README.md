# 1. Гео сервис

### Генерация исходных таблиц

Скрипт для генерации таблиц: *tables_generation/generator.py*

Изменяемые параметры - интервалы широты, долготы и количество пользователей на сетке.
По диапазону создается координатная сетка с приблизительными расстояниями внутри ячеек,
пользователи в случайном порядке раскидываются по ней.

На выходе получаем файлы - *cells.csv* и *users.csv*

### Запуск гео сервиса

Финальная версия приложения: *release_candidate/geoservice-1.0.0.jar*

Необходимая версия Java - 1.8.
Для запуска нужно сформировать таблицы на предыдущем этапе и передать
абсолютные пути к ним в виде аргументов.

Команда для запуска сервиса:

```sh
$ java -jar geoservice-1.0.0.jar <path_to_cells_table> <path_to_users_table>
```

### Примеры HTTP запросов

Расчет категории местоположения для пользователя по координатам:

> http://localhost:8080/location?user_id=1&lat=38.608121&lon=6.017976

Добавление пользователя с меткой / изменение метки пользователя:

> http://localhost:8080/update_user?user_id=1&lat=38.608121&lon=6.017976

Получить количество пользователей с меткой в данной ячейке:

> http://localhost:8080/cell_info?lat=1&lon=1

# 2. Задача о равномерной географической сетке

Вариант разбиения с описанием подхода предложен в файле: *healpy/HEALPix_magic.ipynb*

Формат - Jupiter Notebook, можно просматривать напрямую из гитхаба.






