import numpy as np
import matplotlib.pyplot as plt


# function definition
def latitude_south(x):
    if x <= 51.5:
        return 1
    elif 51.5 < x <= 52:
        return (52 - x) / 0.5
    else:
        return 0


def latitude_Irland(x):
    if 51.5 < x <= 52:
        return (x - 51.5) / 0.5
    elif 52 < x < 55:
        return 1
    elif 55 < x <= 56:
        return (56 - x)
    else:
        return 0


def latitude_north(x):
    if x <= 55:
        return 0
    elif 55 < x <= 56:
        return x - 55
    else:
        return 1


def print_latitude():
    x_values = np.linspace(48, 59, 1000)

    y_ls = [latitude_south(x) for x in x_values]
    y_i = [latitude_Irland(x) for x in x_values]
    y_ln = [latitude_north(x) for x in x_values]

    plt.figure(figsize=(10, 6))

    plt.plot(x_values, y_ls, label="południe od Irlandii")
    plt.plot(x_values, y_i, label="szerokość Irlandii")
    plt.plot(x_values, y_ln, label="północ od Irlandii")

    plt.title("Funkcje przynależności dla serokości geograficznej na której robiony był pomiar")
    plt.xlabel("Wartość x")
    plt.ylabel("Przynależność")
    plt.legend()
    plt.grid(True)
    plt.show()


# function definition
def longitude_west(x):
    if x <= -10.5:
        return 1
    elif -10.5 < x <= -9:
        return (-9 - x) / 1.5
    else:
        return 0


def longitude_Irland(x):
    if -10.5 < x <= -9:
        return (x - -10.5) / 1.5
    elif -10.5 < x < -6.3:
        return 1
    elif -6.3 < x <= -5.4:
        return (-5.4 - x) / 0.9
    else:
        return 0


def longitude_east(x):
    if x <= -6.3:
        return 0
    elif -6.3 < x <= -5.4:
        return (x - -6.3) / 0.9
    else:
        return 1


def print_longitude():
    x_values = np.linspace(-18, -2, 1000)

    y_ls = [longitude_west(x) for x in x_values]
    y_i = [longitude_Irland(x) for x in x_values]
    y_ln = [longitude_east(x) for x in x_values]

    plt.figure(figsize=(10, 6))

    plt.plot(x_values, y_ls, label="zachód od Irlandii")
    plt.plot(x_values, y_i, label="szerokość Irlandii")
    plt.plot(x_values, y_ln, label="wschód od Irlandii")

    plt.title("Funkcje przynależności dla wysokości geograficznej na której robiony był pomiar")
    plt.xlabel("Wartość x")
    plt.ylabel("Przynależność")
    plt.legend()
    plt.grid(True)
    plt.show()
