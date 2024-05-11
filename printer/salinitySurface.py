import numpy as np
import matplotlib.pyplot as plt


# function definition
def moderately_salty(x):
    if x <= 26:
        return 1
    elif 26 < x <= 30:
        return (30 - x) / 4
    else:
        return 0


def strong(x):
    if x <= 26:
        return 0
    elif 26 < x <= 30:
        return (x - 26) / 4
    elif 30 < x < 33:
        return 1
    elif 33 < x <= 35:
        return (35 - x) / 2
    else:
        return 0


def really_salty(x):
    if x <= 34:
        return 0
    elif 34 < x <= 35:
        return x - 34
    else:
        return 1


x_values = np.linspace(20, 38, 1000)


def print_water_salinity_surface():
    y_ms = [moderately_salty(x) for x in x_values]
    y_s = [strong(x) for x in x_values]
    y_ss = [really_salty(x) for x in x_values]

    plt.figure(figsize=(10, 6))

    plt.plot(x_values, y_ms, label="umiarkowane słona")
    plt.plot(x_values, y_s, label="silnie słona")
    plt.plot(x_values, y_ss, label="bardzo silnie słona")

    plt.title("Funkcje przynależności dla zasolenia na powierzchni")
    plt.xlabel("Wartość x")
    plt.ylabel("Przynależność")
    plt.legend()
    plt.grid(True)
    plt.show()
