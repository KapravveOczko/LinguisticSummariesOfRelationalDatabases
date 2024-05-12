import numpy as np
import matplotlib.pyplot as plt


# function definition
def cold(x):
    if x <= 12.5:
        return 1
    elif 12.5 < x <= 14:
        return (14 - x) / 1.5
    else:
        return 0


def cool(x):
    if x <= 13:
        return 0
    elif 13 < x < 14.5:
        return (x - 13) / 1.5
    elif x == 14.5:
        return 1
    elif 14.5 < x <= 16.5:
        return (16.5 - x) / 2
    else:
        return 0


def moderate(x):
    if x <= 15.5:
        return 0
    elif 15.5 < x < 17:
        return (x - 15.5) / 1.5
    elif x == 17:
        return 1
    elif 17 < x <= 18.5:
        return (18.5 - x) / 1.5
    else:
        return 0


def warm(x):
    if x <= 18:
        return 0
    elif 18 < x <= 19.5:
        return (x - 18) / 1.5
    else:
        return 1


x_values = np.linspace(11, 21, 1000)


def print_water_temp_surface():
    y_cold = [cold(x) for x in x_values]
    y_cool = [cool(x) for x in x_values]
    y_moderate = [moderate(x) for x in x_values]
    y_warm = [warm(x) for x in x_values]

    plt.figure(figsize=(10, 4))

    plt.plot(x_values, y_cold, label="zimna")
    plt.plot(x_values, y_cool, label="chłodna")
    plt.plot(x_values, y_moderate, label="umiarkowana")
    plt.plot(x_values, y_warm, label="ciepła")

    plt.title("Funkcje przynależności dla temeperatury na powierzchni")
    plt.xlabel("Wartość x")
    plt.ylabel("Przynależność")
    plt.legend()
    plt.grid(True)
    plt.show()
