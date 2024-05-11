import numpy as np
import matplotlib.pyplot as plt

def north(x):
    if x <= 12.5:
        return 1
    elif 12.5 < x <= 32.5:
        return (32.5 - x) / 20
    elif 327.5 < x <= 347.5:
        return (x - 327.5) / 20
    elif x >= 347.5:
        return 1
    else:
        return 0

def northeast(x):
    if 12.5 < x <= 32.5:
        return (x - 12.5) / 20
    elif 32.5 < x <= 57.5:
        return 1
    elif 57.5 < x <= 77.5:
        return (77.5 - x) / 20
    else:
        return 0

def east(x):
    if 57.5 < x <= 77.5:
        return (x - 57.5) / 20
    elif 77.5 < x <= 102.5:
        return 1
    elif 102.5 < x <= 122.5:
        return (122.5 - x) / 20
    else:
        return 0

def southeast(x):
    if 102.5 < x <= 122.5:
        return (x - 102.5) / 20
    elif 122.5 < x <= 147.5:
        return 1
    elif 147.5 < x <= 167.5:
        return (167.5 - x) / 20
    else:
        return 0

def south(x):
    if 147.5 < x <= 167.5:
        return (x - 147.5) / 20
    elif 167.5 < x <= 192.5:
        return 1
    elif 192.5 < x <= 212.5:
        return (212.5 - x) / 20
    else:
        return 0

def southwest(x):
    if 192.5 < x <= 212.5:
        return (x - 192.5) / 20
    elif 212.5 < x <= 237.5:
        return 1
    elif 237.5 < x <= 257.5:
        return (257.5 - x) / 20
    else:
        return 0

def west(x):
    if 237.5 < x <= 257.5:
        return (x - 237.5) / 20
    elif 257.5 < x <= 282.5:
        return 1
    elif 282.5 < x <= 302.5:
        return (302.5 - x) / 20
    else:
        return 0

def northwest(x):
    if 282.5 < x <= 302.5:
        return (x - 282.5) / 20
    elif 302.5 < x <= 327.5:
        return 1
    elif 327.5 < x <= 347.5:
        return (347.5 - x) / 20
    else:
        return 0


x_values = np.linspace(0, 360, 1000)

def print_wave_degree():
    y_north = [north(x) for x in x_values]
    y_northeast = [northeast(x) for x in x_values]
    y_east = [east(x) for x in x_values]
    y_southeast = [southeast(x) for x in x_values]
    y_south = [south(x) for x in x_values]
    y_southwest = [southwest(x) for x in x_values]
    y_west = [west(x) for x in x_values]
    y_northwest = [northwest(x) for x in x_values]

    plt.figure(figsize=(12, 4))

    plt.plot(x_values, y_north, label='Północny', color='blue')
    plt.plot(x_values, y_northeast, label='Północny-wschód', color='green')
    plt.plot(x_values, y_east, label='Wschód', color='red')
    plt.plot(x_values, y_southeast, label='Południowy-wschód', color='orange')
    plt.plot(x_values, y_south, label='Południowy', color='purple')
    plt.plot(x_values, y_southwest, label='Południowy-zachód', color='brown')
    plt.plot(x_values, y_west, label='Zachód', color='yellow')
    plt.plot(x_values, y_northwest, label='Północny-zachód', color='pink')

    plt.title('Funkcje przynależności dla kierunku fal')
    plt.xlabel('Kierunek [stopnie]')
    plt.ylabel('Przynależność')
    plt.legend()
    plt.grid(True)
    plt.show()