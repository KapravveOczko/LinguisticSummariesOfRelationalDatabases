import numpy as np
import matplotlib.pyplot as plt


# function definition
def none(x):
    if x <= 0.05:
        return 1
    elif 0.05 < x <= 0.1:
        return (0.1 - x) / 0.05
    else:
        return 0


def really_slow(x):
    if 0.05 < x <= 0.1:
        return (x - 0.05) / 0.05
    elif 0.1 < x <= 0.2:
        return 1
    elif 0.2 < x <= 0.25:
        return (0.25 - x) / 0.05
    else:
        return 0


def slow(x):
    if 0.2 < x <= 0.25:
        return (x - 0.2) / 0.05
    elif 0.25 < x <= 0.3:
        return 1
    elif 0.3 < x <= 0.4:
        return (0.4 - x) / 0.1
    else:
        return 0


def fast(x):
    if 0.3 < x <= 0.4:
        return (x - 0.3) / 0.1
    elif 0.4 < x <= 0.475:
        return 1
    elif 0.475 < x <= 0.55:
        return (0.55 - x) / 0.075
    else:
        return 0


def fast_af(x):
    if x <= 0.475:
        return 0
    if 0.475 < x <= 0.55:
        return (x - 0.475) / 0.075
    else:
        return 1


x_values = np.linspace(0, 0.65, 1000)


def print_wave_speed():
    y_cold = [none(x) for x in x_values]
    y_cool = [really_slow(x) for x in x_values]
    y_moderate = [slow(x) for x in x_values]
    y_hot = [fast(x) for x in x_values]
    y_really_hot = [fast_af(x) for x in x_values]

    plt.figure(figsize=(10, 4))

    plt.plot(x_values, y_cold, label="praktycznie bez prędkości")
    plt.plot(x_values, y_cool, label="bardzo niska prędkość")
    plt.plot(x_values, y_moderate, label="niska prędkość")
    plt.plot(x_values, y_hot, label="wysoka prędkość")
    plt.plot(x_values, y_really_hot, label="bardzo wysoka prędkość")

    plt.title("Funkcje przynależności dla prędkości fali")
    plt.xlabel("Wartość x")
    plt.ylabel("Przynależność")
    plt.legend()
    plt.grid(True)
    plt.show()
