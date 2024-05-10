import numpy as np
import matplotlib.pyplot as plt


# function definition
def calm_waters(x):
    if x <= 0.5:
        return 1
    elif 0.5 < x <= 0.7:
        return (0.7 - x) / 0.2
    else:
        return 0


def low_waves(x):
    if x <= 0.5:
        return 0
    elif 0.5 < x <= 1.2:
        return (x - 0.5) / 0.7
    elif 1.2 < x <= 1.8:
        return 1
    elif 1.8 < x <= 2:
        return (2 - x) / 0.2
    else:
        return 0


def moderate(x):
    if x <= 1.8:
        return 0
    elif 1.8 < x < 2.5:
        return (x - 1.8) / 0.7
    elif x == 2.5:
        return 1
    elif 2.5 < x <= 2.7:
        return (2.7 - x) / 0.2
    else:
        return 0


def warm(x):
    if x <= 2.6:
        return 0
    elif 2.6 < x <= 2.7:
        return (x - 2.6) / 0.1
    else:
        return 1


x_values = np.linspace(0, 3, 1000)


def print_wave_high():
    y_calm = [calm_waters(x) for x in x_values]
    y_moderate = [low_waves(x) for x in x_values]
    y_x = [moderate(x) for x in x_values]
    y_high = [warm(x) for x in x_values]

    plt.figure(figsize=(10, 6))

    plt.plot(x_values, y_calm, label="spokojne morze")
    plt.plot(x_values, y_moderate, label="umiarkowana wysokość")
    plt.plot(x_values, y_x, label="średnie")
    plt.plot(x_values, y_high, label="wysokie")

    plt.title("Funkcje przynależności dla wysokości fali")
    plt.xlabel("Wartość x")
    plt.ylabel("Przynależność")
    plt.legend()
    plt.grid(True)
    plt.show()
