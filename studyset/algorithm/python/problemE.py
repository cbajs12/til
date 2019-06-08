__author__ = 'jeonjiseong'


def comp_string(string_line):
    wanted_list = list("JENNIFERSOFT")
    string_list = list(string_line)

    if len(wanted_list) > len(string_list):
        string_list, wanted_list = wanted_list, string_list

    long_len = range(len(string_list) + 1)
    for short_i, short_c in enumerate(wanted_list):
        distances_list = [short_i+1]
        for long_i, long_c in enumerate(string_list):
            if long_c == short_c:
                print('compare')
                print(long_c)
                print(long_len[long_i])
                distances_list.append(long_len[long_i])
            else:
                print('else')
                print(long_len)
                print(long_len[long_i])
                distances_list.append(1 + min((long_len[long_i], long_len[long_i + 1], distances_list[-1])))
                print(distances_list)
        long_len = distances_list
        print('long_len')
        print(long_len)
    return long_len[-1]


if __name__ == "__main__":
    r1 = input("Write one string\n")

    n = len(r1)

    if n > 1000 or n < 1:
        print("Wrong length input")
        exit()

    if not r1.isalpha():
        print("Input should be Only alphabets")
        exit()

    string = r1.upper()

    result = comp_string(string)

    print(result)
