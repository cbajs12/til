__author__ = 'jeonjiseong'


def sort_alphabet(string_line):
    word_hash = dict()
    word_list = []

    for k in string_line:
        if word_hash.get(k) is None:
            word_hash[k] = 1
        else:
            word_hash[k] += 1

    sorted_hash_keys = sorted(word_hash.items(), key=lambda x: x[0])
    sorted_hash_values = sorted(sorted_hash_keys, key=lambda x: x[1])

    for i, j in sorted_hash_values:
        for k in range(j):
            word_list.append(i)

    result_string = ''.join(word_list)

    return result_string

if __name__ == "__main__":
    r1 = input("Write one string\n")

    n = len(r1)

    if n > 1000000 or n < 1:
        print("Wrong length input")
        exit()

    if not r1.isalpha():
        print("Input should be Only alphabets")
        exit()

    string = r1.upper()

    result = sort_alphabet(string)

    print(result)





