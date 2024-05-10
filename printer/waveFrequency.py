import numpy as np
import matplotlib.pyplot as plt


# function definition
def wrincles(x):
    if x <= 0.2:
        return 1
    elif 0.2 < x <= 0.5:
        return (0.5 - x) / 0.3
    else:
        return 0


def rare_windy(x):
    if x <= 0.2:
        return 0
    elif 0.2 < x <= 1:
        return (x - 0.2) / 0.8
    elif 1 < x <= 4:
        return 1
    elif 4 < x <= 5.5:
        return (5.5 - x) / 1.5
    else:
        return


def windy(x):
    if x <= 4:
        return 0
    elif 4 < x <= 5.5:
        return (x - 4) / 1.5
    else:
        return 1


x_values = np.linspace(0, 7, 1000)


def print_wave_frequency():
    y_ns = [wrincles(x) for x in x_values]
    y_ms = [rare_windy(x) for x in x_values]
    y_s = [windy(x) for x in x_values]

    plt.figure(figsize=(10, 6))

    plt.plot(x_values, y_ns, label="zmarszczki")
    plt.plot(x_values, y_ms, label="rzadkie wiatrowe")
    plt.plot(x_values, y_s, label="wiatrowe")

    plt.title("Funkcje przynależności dla częstotliwości fal")
    plt.xlabel("Wartość x")
    plt.ylabel("Przynależność")
    plt.legend()
    plt.grid(True)
    plt.show()
