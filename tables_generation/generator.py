from math import *
from random import uniform


def create_cells_list():
    cells = []
    for i in range(latitude_start, latitude_end + 1, 1):
        for j in range(longitude_start, longitude_end + 1, 1):
            cells.append((i, j, calc_avg_distance(i, j)))
    return cells


def calc_avg_distance(lat, lon):
    lat_x = lat * pi / 180
    lon_x = lon * pi / 180
    lat_y = (lat + 1) * pi / 180
    lon_y = (lon + 1) * pi / 180

    lonDelta = abs(lon_x - lon_y)
    result = acos(sin(lat_x) * sin(lat_y) + cos(lat_x) * cos(lat_y) * cos(lonDelta))
    return int(result * 6372795)


def create_users_list(user_count):
    users = []
    for i, val in enumerate(range(user_count)):
        users.append(
            (i + 1, random_in_range(latitude_start, latitude_end), random_in_range(longitude_start, longitude_end)))
    return users


def random_in_range(x, y):
    return round(uniform(x, y + 1), 6)


def create_table(file_name, items):
    f = open(file_name, 'w')
    for item in items:
        f.write(','.join(map(lambda x: str(x), item)) + '\n')
    f.close()


if __name__ == "__main__":
    latitude_start = 30
    latitude_end = 50
    longitude_start = 20
    longitude_end = 80

    create_table('cells.csv', create_cells_list())
    create_table('users.csv', create_users_list(100000))
