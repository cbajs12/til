import sys
__author__ = 'jeonjiseong'


def calculate_distance(number, string_lists):
    dot_dict = {0: [0, 0]}

    for x in range(number):
        dot_dict[x+1] = change_int(string_lists[x])

    dot_dict[number+1] = [10, 10]
    dot_dict_len = len(dot_dict)
    val_matrix = [[0 for x in range(dot_dict_len)] for x in range(dot_dict_len)]

    for t, p in dot_dict.items():
        for r, q in dot_dict.items():
            temp = cal_dis(p, q)
            if temp == 0.0:
                val_matrix[t][r] = 1000
            else:
                val_matrix[t][r] = temp

    data_list = [x+1 for x in range(number)]

    temp_list = list(k_perm(set(data_list), number))

    val_dict = dict()
    for t in temp_list:
        t.insert(0, 0)
        t.append(dot_dict_len-1)
        temp_total = 0

        for q in range(len(t)):
            if q >= (len(t) - 1):
                continue
            temp_call = val_matrix[t[q]][t[q+1]]
            temp_total += temp_call

        val_dict[temp_total] = t

    # print(val_dict)
    min_val = 1000
    for p in val_dict.keys():
        if min_val > p:
            min_val = p

    result_list = val_dict[min_val]
    result_string = ''
    for z in result_list:
        result_string += str(dot_dict[z])
        if z is result_list[-1]:
            continue
        result_string += " -> "

    return result_string


def k_perm(base, k):    # permutation
    for e in base:
        if k == 1:
            yield [e]
        else:
            for perm in k_perm(base-set([e]), k-1):
                yield [e] + perm


def cal_dis(dot1, dot2):
    result_value = ((dot2[1] - dot1[1]) ** 2) + ((dot2[0] - dot1[0]) ** 2)
    return result_value**(1/2)


def change_int(string_line):
    string_line = string_line.strip()
    string_list = string_line.split(' ')
    int_list = []

    for k in string_list:
        int_list.append(int(k))

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

    result = calculate_distance(n, input_list)

    print(result)
