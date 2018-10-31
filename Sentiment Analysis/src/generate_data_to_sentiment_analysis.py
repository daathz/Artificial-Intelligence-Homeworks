from random import randint

with open('random_data_for_homework.txt', 'w') as f:
    # inputData
    for i in range(8000):
        x = randint(5, 15)
        for j in range(x):
            if j < x - 1:
                print(randint(0, 200), end='\t', file=f)
            else:
                print(randint(0, 200), end='\n', file=f)

    # inputLabels
    for i in range(2000):
        print(randint(0, 1), file=f)

    # targetData
    for i in range(8000):
        x = randint(5, 15)
        for j in range(x):
            if j < x - 1:
                print(randint(0, 200), end='\t', file=f)
            else:
                print(randint(0, 200), end='\n', file=f)
