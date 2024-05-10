import numpy as np
import matplotlib.pyplot as plt


# function definition
def cold(x):
    if x <= 3:
        return 1
    elif 3 < x <= 5:
        return (5 - x) / 2
    else:
        return 0


def cool(x):
    if x <= 3:
        return 0
    elif 3 < x <= 6:
        return (x - 3) / 3
    elif 3 < x <= 8:
        return 1
    elif 8 < x <= 9:
        return 9 - x
    else:
        return 0


def moderate(x):
    if x <= 8:
        return 0
    elif 8 < x <= 10:
        return (x - 8) / 2
    elif 0.10 < x <= 11:
        return 1
    elif 11 < x <= 13:
        return (13 - x) / 2
    else:
        return 0


def warm(x):
    if x <= 12:
        return 0
    elif 12 < x <= 13:
        return x - 12
    elif 13 < x <= 14:
        return 1
    elif 14 < x <= 17:
        return (17 - x) / 3
    else:
        return 0


def hot(x):
    if x <= 15.5:
        return 0
    elif 15.5 < x < 17:
        return (x - 15.5) / 1.5
    elif x == 17:
        return 1
    elif 17 < x <= 18:
        return (18 - x)
    else:
        return 0


def really_hot(x):
    if x <= 17.5:
        return 0
    elif 17.5 < x <= 18.5:
        return (x - 17.5)
    else:
        return 1


x_values = np.linspace(0, 22, 1000)


def print_water_temp_depth():
    y_cold = [cold(x) for x in x_values]
    y_cool = [cool(x) for x in x_values]
    y_moderate = [moderate(x) for x in x_values]
    y_warm = [warm(x) for x in x_values]
    y_hot = [hot(x) for x in x_values]
    y_really_hot = [really_hot(x) for x in x_values]

    plt.figure(figsize=(10, 6))

    plt.plot(x_values, y_cold, label="zimna")
    plt.plot(x_values, y_cool, label="chłodna")
    plt.plot(x_values, y_moderate, label="umiarkowana")
    plt.plot(x_values, y_warm, label="ciepła")
    plt.plot(x_values, y_hot, label="bardzo ciepła")
    plt.plot(x_values, y_really_hot, label="skrajnie ciepła")

    plt.title("Funkcje przynależności dla temeperatury na dnie")
    plt.xlabel("Wartość x")
    plt.ylabel("Przynależność")
    plt.legend()
    plt.grid(True)
    plt.show()
