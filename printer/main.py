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
    if x <= 0.2:
        return 0
    elif 0.2 < x <= 0.3:
        return 10 * (x - 0.2)
    elif 0.3 < x <= 0.36:
        return 1
    elif 0.36 < x <= 0.46:
        return 1 - 10 * (x - 0.36)
    else:
        return 0


def almost_half(x):
    if x <= 0.4:
        return 0
    elif 0.4 < x <= 0.47:
        return 15 * (x - 0.4)
    elif 0.47 < x <= 0.53:
        return 1
    elif 0.53 < x <= 0.6:
        return 1 - 15 * (x - 0.53)
    else:
        return 0


def around_2_3(x):
    if x <= 0.53:
        return 0
    elif 0.53 < x <= 0.63:
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


x_values = np.linspace(0, 1, 100)

if __name__ == '__main__':
    y_little = [little(x) for x in x_values]
    y_around_1_3 = [around_1_3(x) for x in x_values]
    y_almost_half = [almost_half(x) for x in x_values]
    y_around_2_3 = [around_2_3(x) for x in x_values]
    y_almost_all = [almost_all(x) for x in x_values]

    plt.figure(figsize=(10, 6))

    plt.plot(x_values, y_little, label="Mało")
    plt.plot(x_values, y_around_1_3, label="Około 1/3")
    plt.plot(x_values, y_almost_half, label="Połowa")
    plt.plot(x_values, y_around_2_3, label="Około 2/3")
    plt.plot(x_values, y_almost_all, label="Prawie wszystkie")

    plt.title("Funkcje przynależności dla kwantyfikatorów lingwistycznych")
    plt.xlabel("Wartość x")
    plt.ylabel("Przynależność")
    plt.legend()
    plt.grid(True)
    plt.show()
