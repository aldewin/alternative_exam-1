def decimalisation(num, denom, precision):
    if num > denom:  # отделение целой части
        length = len(str(num // denom))
        for i in range(length - 1, -1, -1):
            k = 0
            while num - k * denom * 10 ** i >= denom:
                k += 1
            if i != 0:
                k = k - 1
            print(k, end='')
            num = num - k * denom * 10 ** i
        print(".", end='')
    else:
        print('0.', end='')
    sign = 1
    while sign < precision:  # поциферное вычисление дробной части
        num *= 10
        curr = 0
        while num >= denom:
            num -= denom
            curr += 1
        print(curr, end='')
        sign += 1
    num *= 10
    curr = 0
    while num >= denom:
        num -= denom
        curr += 1
    num *= 10
    next = 0
    while num >= denom:  # получение цифры разряда, следующего за необходимым
        num -= denom
        next += 1
    if next >= 5:  # округление
        curr += 1
    print(curr)


print("Введите числитель рациональной дроби: ", end='')
num = int(input())
print('\n')
print("Введите знаменатель рациональной дроби: ", end='')
denom = int(input())
print('\n')
print("Введите количество знаков после запятой: ", end='')
precision = int(input())
print('\n')
decimalisation(num, denom, precision)
