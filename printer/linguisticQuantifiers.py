import numpy as np
import matplotlib.pyplot as plt


# function definition
def little(x):
    if x <= 0.1:
        return 1
    elif 0.1 < x <= 0.3:
        return (0.3 - x) / 0.2
    else:
        return 0


def around_1_3(x):
    if 0.2 < x <= 0.3:
        return 10 * (x - 0.2)
    elif 0.3 < x <= 0.36:
        return 1
    elif 0.36 < x <= 0.46:
        return 1 - 10 * (x - 0.36)
    else:
        return 0


def almost_half(x):
    if 0.4 < x <= 0.47:
        return (x - 0.4) * 14
    elif 0.47 < x <= 0.53:
        return 1
    elif 0.53 < x <= 0.6:
        return 1 - 15 * (x - 0.53)
    else:
        return 0


def around_2_3(x):
    if 0.53 < x <= 0.63:
        return 10 * (x - 0.53)
    elif 0.63 < x <= 0.7:
        return 1
    elif 0.7 < x <= 0.8:
        return 1 - 10 * (x - 0.7)
    else:
        return 0


def almost_all(x):
    if x <= 0.7:
        return 0
    elif 0.7 < x <= 0.9:
        return (x - 0.7) / 0.2
    else:
        return 1


x_values = np.linspace(0, 1, 1000)


def print_linguistic_quantifiers():
    y_little = [little(x) for x in x_values]
    y_around_1_3 = [around_1_3(x) for x in x_values]
    y_almost_half = [almost_half(x) for x in x_values]
    y_around_2_3 = [around_2_3(x) for x in x_values]
    y_almost_all = [almost_all(x) for x in x_values]

    plt.figure(figsize=(10, 4))

    plt.plot(x_values, y_little, label="Mało")
    plt.plot(x_values, y_around_1_3, label="Około 1/3")
    plt.plot(x_values, y_almost_half, label="Połowa")
    plt.plot(x_values, y_around_2_3, label="Około 2/3")
    plt.plot(x_values, y_almost_all, label="Prawie wszystkie")

    plt.title("Funkcje przynależności dla kwantyfikatory rozmytych względnych")
    plt.xlabel("Wartość x")
    plt.ylabel("Przynależność")
    plt.legend()
    plt.grid(True)
    plt.show()

def less_than_1000(x):
    if x <= 0.1:
        return 1
    else:
        return 0

def about_2000(x):
    if 0.1 < x < 0.2:
        return (x - 0.1) / 0.1
    elif x == 0.2:
        return 1
    elif 0.2 < x <= 0.3:
        return (0.3 - x) / 0.1
    else:
        return 0

def about_3000(x):
    if 0.2 < x <= 0.3:
        return (x - 0.2) / 0.1
    elif x == 0.3:
        return 1
    elif 0.3 < x <= 0.4:
        return (0.4 - x) / 0.1
    else:
        return 0

def about_4000(x):
    if 0.3 < x <= 0.4:
        return (x - 0.3) / 0.1
    elif x == 0.4:
        return 1
    elif 0.4 < x <= 0.5:
        return (0.5 - x) / 0.1
    else:
        return 0

def about_5000(x):
    if 0.4 < x <= 0.5:
        return (x - 0.4) / 0.1
    elif x == 0.5:
        return 1
    elif 0.5 < x <= 0.6:
        return (0.6 - x) / 0.1
    else:
        return 0

def about_6000(x):
    if 0.5 < x <= 0.6:
        return (x - 0.5) / 0.1
    elif x == 0.6:
        return 1
    elif 0.6 < x <= 0.7:
        return (0.7 - x) / 0.1
    else:
        return 0

def about_7000(x):
    if 0.6 < x <= 0.7:
        return (x - 0.6) / 0.1
    elif x == 0.7:
        return 1
    elif 0.7 < x <= 0.8:
        return (0.8 - x) / 0.1
    else:
        return 0

def about_8000(x):
    if 0.7 < x <= 0.8:
        return (x - 0.7) / 0.1
    elif x == 0.8:
        return 1
    elif 0.8 < x <= 0.9:
        return (0.9 - x) / 0.1
    else:
        return 0

def more_than_9000(x):
    if x <= 0.9:
        return 0
    else:
        return 1

def print_linguistic_quantifiers_bw():
    x_values = np.linspace(0, 1, 1000)

    y_less_than_1000 = [less_than_1000(x) for x in x_values]
    y_about_2000 = [about_2000(x) for x in x_values]
    y_about_3000 = [about_3000(x) for x in x_values]
    y_about_4000 = [about_4000(x) for x in x_values]
    y_about_5000 = [about_5000(x) for x in x_values]
    y_about_6000 = [about_6000(x) for x in x_values]
    y_about_7000 = [about_7000(x) for x in x_values]
    y_about_8000 = [about_8000(x) for x in x_values]
    y_more_than_9000 = [more_than_9000(x) for x in x_values]

    plt.figure(figsize=(10, 4))

    plt.plot(x_values, y_less_than_1000, label="Mniej niż 10% rekordów")
    plt.plot(x_values, y_about_2000, label="Około 20% rekordów")
    plt.plot(x_values, y_about_3000, label="Około 30% rekordów")
    plt.plot(x_values, y_about_4000, label="Około 40% rekordów")
    plt.plot(x_values, y_about_5000, label="Około 50% rekordów")
    plt.plot(x_values, y_about_6000, label="Około 60% rekordów")
    plt.plot(x_values, y_about_7000, label="Około 70% rekordów")
    plt.plot(x_values, y_about_8000, label="Około 80% rekordów")
    plt.plot(x_values, y_more_than_9000, label="Więcej niż 90% rekordów")

    plt.title("Funkcje przynależności dla kwantyfikatorów rozmytych absolutnych")
    plt.xlabel("Liczba rekordów")
    plt.ylabel("Przynależność")
    plt.legend()
    plt.grid(True)
    plt.show()

print_linguistic_quantifiers_bw()

