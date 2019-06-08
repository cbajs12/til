import sys
__author__ = 'jeonjiseong'


def count_seats(string_lists):
    counts = 0

    for x in range(len(string_lists)):
        counts += check_seats(string_lists[x])

    return counts


def check_seats(string_list):
    string_list = string_list.strip()
    connection_list = []
    connectedness = 0
    count = 0

    for k in string_list:
        if k is '*':
            connection_list.append(connectedness)
            connectedness = 0
        else:
            connectedness += 1

    connection_list.append(connectedness)

    for t in connection_list:
        if len(connection_list) is 0:
            break
        if t > 1:
            count = t - 1

    # print(count)
    return count


if __name__ == "__main__":
    r1 = lambda: sys.stdin.readline()

    n = int(r1())

    if n > 1000 or n < 1:
        print("Wrong length input")
        exit()

    input_list = list()

    for i in range(n):
        input_list.append(str(r1()))

    result = count_seats(input_list)

    print(result)
