import numpy as np
import matplotlib.pyplot as plt


# function definition
def no_salty(x):
    if x <= 22:
        return 1
    elif 22 < x <= 26:
        return (26 - x) / 4
    else:
        return 0


def moderately_salty(x):
    if 24 < x <= 26:
        return (x - 24) / 2
    elif 26 < x <= 30.5:
        return 1
    elif 30.5 < x <= 32:
        return (32 - x) / 1.5
    else:
        return 0


def strong(x):
    if 30.5 < x < 34:
        return (x - 30.5) / 3.5
    elif x == 34:
        return 1
    elif 34 < x <= 36:
        return (36 - x) / 2
    else:
        return 0


def really_salty(x):
    if x <= 35:
        return 0
    elif 35 < x <= 36:
        return x - 35
    else:
        return 1


x_values = np.linspace(20, 38, 1000)


def print_water_salinity_depth():
    y_ns = [no_salty(x) for x in x_values]
    y_ms = [moderately_salty(x) for x in x_values]
    y_s = [strong(x) for x in x_values]
    y_ss = [really_salty(x) for x in x_values]

    plt.figure(figsize=(10, 4))

    plt.plot(x_values, y_ns, label="nie słona")
    plt.plot(x_values, y_ms, label="umiarkowane słona")
    plt.plot(x_values, y_s, label="silnie słona")
    plt.plot(x_values, y_ss, label="bardzo silnie słona")

    plt.title("Funkcje przynależności dla zasolenia na dnie")
    plt.xlabel("Wartość x")
    plt.ylabel("Przynależność")
    plt.legend()
    plt.grid(True)
    plt.show()
