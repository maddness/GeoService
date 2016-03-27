from math import *
from random import uniform


def create_cells_list():
    cells = []
    for i in range(latitude_range[0], latitude_range[1] + 1):
        for j in range(longitude_range[0], longitude_range[1] + 1):
            cells.append((i, j, calc_avg_distance(i, j)))
    print(len(cells), 'cells created')
    return cells


def calc_avg_distance(lat, lon):
    lat_x = lat * pi / 180
    lon_x = lon * pi / 180
    lat_y = (lat + 1) * pi / 180
    lon_y = (lon + 1) * pi / 180

    result = acos(sin(lat_x) * sin(lat_y) + cos(lat_x) * cos(lat_y) * cos(abs(lon_x - lon_y)))
    return int(result * 6372795 / sqrt(2))


def create_users_list():
    users = []
    for i, val in enumerate(range(users_number)):
        users.append(
            (i + 1, random_in_range(latitude_range[0], latitude_range[1]),
             random_in_range(longitude_range[0], longitude_range[1])))
    print(len(users), 'users created')
    return users


def random_in_range(x, y):
    return round(uniform(x, y + 1), 6)


def create_table(file_name, items):
    f = open(file_name, 'w')
    for item in items:
        f.write(','.join(map(lambda x: str(x), item)) + '\n')
    f.close()


if __name__ == "__main__":
    latitude_range = (1, 50)  # should be in range [-90, 90)
    longitude_range = (1, 20)  # should be in range [-180, 180)

    users_number = 100000

    create_table('cells.csv', create_cells_list())
    create_table('users.csv', create_users_list())
