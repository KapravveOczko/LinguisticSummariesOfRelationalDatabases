import numpy as np
import matplotlib.pyplot as plt


# function definition
import math

def wrinkles(x):
    mu = 0.5
    sigma = 0.5
    return math.exp(-((x - mu) ** 2) / (2 * sigma ** 2))

def rare_windy(x):
    mu = 2.6
    sigma = 0.6
    return math.exp(-((x - mu) ** 2) / (2 * sigma ** 2))

def windy(x):
    mu = 5
    sigma = 0.75
    return math.exp(-((x - mu) ** 2) / (2 * sigma ** 2))



x_values = np.linspace(0, 7, 1000)


def print_wave_frequency():
    y_ns = [wrinkles(x) for x in x_values]
    y_ms = [rare_windy(x) for x in x_values]
    y_s = [windy(x) for x in x_values]

    plt.figure(figsize=(10, 4))

    plt.plot(x_values, y_ns, label="zmarszczki")
    plt.plot(x_values, y_ms, label="rzadkie fale wiatrowe")
    plt.plot(x_values, y_s, label="fale wiatrowe")

    plt.title("Funkcje przynależności dla częstotliwości fal")
    plt.xlabel("Wartość x")
    plt.ylabel("Przynależność")
    plt.legend()
    plt.grid(True)
    plt.show()
