import sys
__author__ = 'jeonjiseong'


def escape_count(number, input_list):
    matrix_field = []
    count = 0
    for x in range(number):
        matrix_field.append(change_int(input_list[x]))

    print(matrix_field[number-1][0])


    return count


def change_int(string_line):
    string_line = string_line.strip()
    string_list = string_line.split(' ')
    int_list = []

    for k in string_list:
        temp_list = []
        for j in k:
            int_list.append(int(j))

    return int_list


if __name__ == "__main__":
    r1 = lambda: sys.stdin.readline()

    n = int(r1())

    if n > 10 or n < 1:
        print("Wrong length input")
        exit()

    input_list = list()

    for i in range(n):
        input_list.append(str(r1()))

    result = escape_count(n, input_list)

    print(result)
