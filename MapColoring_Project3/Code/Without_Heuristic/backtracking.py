# Python program for solution of M Coloring 
# problem using backtracking 

from datetime import datetime

print("Please Enter the Country")
start_time = datetime.now()
country = input()
noofbacktracks=0


class Backtracking():

    def __init__(self, vertices):
        self.V = vertices
        self.graph = [[0 for column in range(vertices)]
                      for row in range(vertices)]

    def is_safe(self, v, colour, c):
        for i in range(self.V):
            if self.graph[v][i] == 1 and colour[i] == c:
                return False
        return True

    def graph_colour(self, m, colour, v):
        global noofbacktracks
        if v == self.V:
            return True

        for c in range(1, m + 1):
            if self.is_safe(v, colour, c) == True:
                colour[v] = c
                if self.graph_colour(m, colour, v + 1) == True:
                    return True
                colour[v] = 0
            else:
                noofbacktracks+= 1

    def graph_colouring(self, m):
        colour = [0] * self.V
        if self.graph_colour(m, colour, 0) == False:
            return False

        # Print the solution
        print("Graph is Consistent:")
        for idx, val in enumerate(colour):
            result_dictionary[state_dictionary[str(idx + 1)]] = color_dictionary[str(val)]
        return True


if country == 'America' or country == 'america':
    Adjacency_matrix = [[9, 10, 24, 42], [], [5, 6, 28, 31, 44], [18, 24, 25, 36, 42, 43], [3, 28, 37],
                        [3, 16, 27, 31, 34, 36, 50], [21, 32, 39], [20, 30, 38], [1, 10], [1, 9, 33, 40, 42], [],
                        [26, 28, 37, 44, 47, 50], [14, 15, 17, 22, 25, 49], [13, 17, 22, 35], [13, 23, 25, 27, 41, 49],
                        [6, 25, 27, 36], [13, 14, 25, 35, 42, 46, 48], [4, 24, 43], [29], [8, 38, 46, 48],
                        [7, 29, 32, 39, 45], [13, 14, 23, 35, 49], [15, 22, 34, 41, 49], [1, 18, 42, 43],
                        [4, 13, 15, 16, 17, 27, 36, 42], [12, 34, 41, 50], [6, 15, 16, 25, 41, 50], [3, 5, 12, 37, 44],
                        [19, 21, 45], [8, 32, 38], [3, 6, 36, 43, 44], [7, 21, 30, 38, 39, 45],
                        [10, 23, 26, 40, 41, 42, 46], [23, 26, 41], [14, 17, 22, 38, 48], [4, 6, 16, 25, 31, 43],
                        [5, 12, 28, 47], [8, 20, 30, 32, 35, 48], [7, 21, 32], [10, 33], [15, 23, 26, 27, 34, 50],
                        [1, 4, 10, 17, 24, 25, 33], [4, 18, 31, 36], [3, 6, 12, 28, 31, 50], [21, 29, 32],
                        [17, 20, 33, 42, 48], [12, 37], [17, 20, 35, 38, 46], [13, 15, 20, 22, 23],
                        [6, 12, 26, 27, 41, 44]]

    positions = []
    for i in range(0, 50):
        individual = []
        for j in range(0, 50):
            individual.append(0)
        for j in Adjacency_matrix[i]:
            individual[j - 1] = 1
        positions.append(individual)

    states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware',
              'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana',
              'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana',
              'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico', 'New York', 'North Carolina',
              'North Dakota', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island', 'South Carolina',
              'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 'West Virginia',
              'Wisconsin', 'Wyoming']
    i = 1
    state_dictionary = {}
    for state in states:
        state_dictionary[str(i)] = state
        i = i + 1
    color_dictionary = {"1": "red", "2": "green", "3": "yellow", "4": "blue"}
    result_dictionary = {}

    # Driver Code
    g = Backtracking(50)

    g.graph = positions

    m = 4  ## chromataic number

if country == 'Australia' or country == 'australia':
    Adjacency_matrix = [[3, 4, 6], [3, 4, 7], [1, 2, 4], [1, 2, 3, 4, 6, 7], [], [1, 4], [2, 4]]
    positions = []
    for i in range(0, 7):
        individual = []
        for j in range(0, 7):
            individual.append(0)
        for j in Adjacency_matrix[i]:
            individual[j - 1] = 1
        positions.append(individual)

    states = ['New South Wales', 'Northern Territory', 'Queensland', 'South Australia', 'Tasmania', 'Victoria',
              'Westren Australia']
    i = 1
    state_dictionary = {}
    for state in states:
        state_dictionary[str(i)] = state
        i = i + 1
    color_dictionary = {"1": "red", "2": "green", "3": "blue"}
    result_dictionary = {}
    domain_dictionary = {}


    # Driver Code
    g = Backtracking(7)

    g.graph = positions

    m = 3  ## chromataic number

g.graph_colouring(m)
end_time = datetime.now()

Time_difference = end_time - start_time
print(f'THE TOTAL EXECUTION TIME -->',str(Time_difference.total_seconds()))
print(f'Number of backtracks : {noofbacktracks}')
for key, value in result_dictionary.items():
	print(f'{key} --> {value}')





