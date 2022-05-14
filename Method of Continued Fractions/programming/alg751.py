def input_odds():
    n = int(input("Введите максимальную степень многочлена: "))
    x = [0] * (n + 1)
    x[0] = int(input("Введите коээфициент при степени 0: "))
    for i in range(1, n + 1):
        x[i] = int(input(f"Введите коээфициент при степени {i}: "))
    return x


def theorem751(a):
    n = len(a)
    search = True

    last_sign = 1 if a[0] > 0 else -1

    max_element = -a[0] if a[0] < 0 else a[0]
    sum_element = 0
    for i in range(1, n):
        curr_sign = 1 if a[i] > 0 else -1
        if search and last_sign != curr_sign:
            search = False

        if search:
            a[i] = -a[i] if a[i] < 0 else a[i]
            tmp = max(max_element, a[i])
            max_element = -tmp if tmp < 0 else tmp
        else:
            sum_element += a[i]

    b = (max_element / sum_element) + 1
    return b


def a751():
    x = input_odds()
    n = len(x)

    a = int(input("Введите левую границу изолирующего интервала"))
    b = theorem751(x)
    e = 0.01

    mid = (a + b) / 2
    while (b - a) > e:
        mid = (a + b) / 2
        result = x[0]
        result_b = x[0]
        result_a = x[0]
        for i in range(1, n):
            result += x[i] * mid
            result_b += x[i] * b
            result_a += x[i] * a

        if result == 0:
            return mid

        if (result_b > 0 and result > 0) or (result_b < 0 and result < 0):
            b = mid
        else:
            a = mid

    return mid


if __name__ == '__main__':
    print(a751())
