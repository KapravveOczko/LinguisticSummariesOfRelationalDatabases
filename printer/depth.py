import numpy as np
import matplotlib.pyplot as plt


# function definition
def surface(x):
    if x <= 4:
        return 1
    elif 4 < x <= 10:
        return (10 - x) / 6
    else:
        return 0


def shallow(x):
    if x <= 6:
        return 0
    elif 6 < x <= 10:
        return (x - 6) / 4
    elif 10 < x <= 14:
        return 1
    elif 14 < x <= 16:
        return (16 - x) / 2
    else:
        return 0


def moderately_deep(x):
    if x <= 14:
        return 0
    elif 14 < x <= 18:
        return (x - 14) / 4
    elif 18 < x <= 21:
        return 1
    elif 21 < x <= 28:
        return (28 - x) / 7
    else:
        return 0


def deep(x):
    if 21 < x <= 29:
        return (x - 21) / 8
    elif 29 < x <= 32:
        return 1
    elif 32 < x <= 34:
        return (34 - x) / 2
    else:
        return 0


def profundal(x):
    if x <= 32:
        return 0
    elif 32 < x <= 34:
        return (x - 32) / 2
    else:
        return 1


x_values = np.linspace(2, 40, 1000)


def print_water_depth():
    y_surface = [surface(x) for x in x_values]
    y_shallow = [shallow(x) for x in x_values]
    y_moderate = [moderately_deep(x) for x in x_values]
    y_deep = [deep(x) for x in x_values]
    y_prof = [profundal(x) for x in x_values]

    plt.figure(figsize=(10, 4))

    plt.plot(x_values, y_surface, label="Powierzchniowe")
    plt.plot(x_values, y_shallow, label="płytko")
    plt.plot(x_values, y_moderate, label="umiarkowanie głęboko")
    plt.plot(x_values, y_deep, label="głęboko")
    plt.plot(x_values, y_prof, label="Profundalnie")

    plt.title("Funkcje przynależności dla głębokości")
    plt.xlabel("Wartość x")
    plt.ylabel("Przynależność")
    plt.legend()
    plt.grid(True)
    plt.show()
