from datetime import datetime

print("Please enter the country")
start_time = datetime.now()
country = input()
noofbacktracks=0

class Singletonheuristic(): 

    def __init__(self, vertexs): 
        self.V = vertexs 
        self.graph = [[0 for column in range(vertexs)]
                            for row in range(vertexs)] 

    ## heuristic functions
    #MRV = Most Remaining Values
    def MRV(self, domain_dictionary, colours):
        minimum_values = {0:[],1:[],2:[],3:[],4:[]}

        for key, value in domain_dictionary.items():
            if len(value)==0 and colours[key-1] == 0:
                minimum_values[0].append(key-1)

            elif(len(value)==1 and colours[key-1]==0):
                minimum_values[1].append(key-1)

            elif(len(value)==2 and colours[key-1]==0):
                minimum_values[2].append(key-1)

            elif(len(value)==3 and colours[key-1]==0):
                minimum_values[3].append(key-1)

            elif(len(value)==4 and colours[key-1]==0):
                minimum_values[4].append(key-1)

        if len(minimum_values[0])>0:
            return minimum_values[0]

        elif len(minimum_values[1])>0:
            return minimum_values[1]

        elif len(minimum_values[2])>0:
            return minimum_values[2]

        elif len(minimum_values[3])>0:
            return minimum_values[3]
        else:
            return minimum_values[4]


    def degree_constraint(self, domain_dictionary, colours):
        Maxdegree_constraint = 0
        max_degree_constraint_state = -1

        for v in range(self.V): 
            if colours[v]!=0:
                continue
            count = 0
            for i in range(self.V):
                if self.graph[v][i] == 1:
                    count = count + 1
            if count > Maxdegree_constraint:
                Maxdegree_constraint = count
                max_degree_constraint_state = v
        return max_degree_constraint_state

    #LCV = least_constraining_values
    def LCV(self, domain_dictionary, colours):
        Min_degree_constraint = 0
        min_degree_constraint_state = -1

        for v in range(self.V): 
            if colours[v]!=0:
                continue
            count = 0
            for i in range(self.V):
                if self.graph[v][i] == 0:
                    count = count + 1
            if count > Min_degree_constraint:
                Min_degree_constraint = count
                min_degree_constraint_state = v

        return min_degree_constraint_state

    def get_next_state(self, domain_dictionary, colours):
        next_state = 0

        next_MRV_states = self.MRV(domain_dictionary, colours)
        next_degree_constraint_states = self.degree_constraint(domain_dictionary, colours)
        next_LCV_states= self.LCV(domain_dictionary, colours)

        if (len(next_MRV_states)==1):
            next_state = next_MRV_states[0]
        elif(next_degree_constraint_states!=-1):
            next_state = next_degree_constraint_states
        else:
            next_state = next_LCV_states

        return next_state




    def is_safe(self, v, colour, c):
        for i in range(self.V): 
            if self.graph[v][i] == 1 and colour[i] == c: 
                return False
        return True

    def get_neighbors(self, state):
        neighbours = []
        for i in range(self.V):
            if self.graph[state][i] == 1:
                neighbours.append(i)
        return neighbours


    #singleton methods
    def is_coloured(self, colors):
        totalvertex = 0

        for color in colors:
            if color != 0:
                totalvertex = totalvertex + 1

        if totalvertex == 50:
            return True
        elif totalvertex == 7:
            return True
        else:
            return False

    def check_singleton_constraints(self, v):
        if self.is_coloured(colour):
                return True
        if v == self.V: 
                v = random.choice([a for a in range(len(colour)) if colour[a] == 0])

    def singleton_domain_variables_removing(self, v):
        if c in domain_dictionary[v+1]:
            domain_dictionary[v+1].remove(c)

        if c not in domain_dictionary[v+1]:
            domain_dictionary[v+1].append(c)
            domain_dictionary[v+1].sort()


    def singleton_logic(self, st_states):
        if len(st_states)==0:
            return
        state_tocolor = -1
        for key,value in domain_dictionary.items():
            if len(value) == 1 and colour[key-1] == 0:
                state_tocolor = key
                break

        if state_tocolor == -1:
            current = random.choice([a for a in range(len(colour)) if colour[a] == 0])
            if self.graph_colour(m, colour, current) == True:
                return True
        else:
            if self.graph_colour(m, colour, state_tocolor-1) == True:
                return True

    def graph_colour(self, m, colour, v):
        global noofbacktracks
        try:
            if v == self.V: 
                return True
            if country == 'Australia' or country == 'australia':
                list_st_states = list(range(self.V%7))
            if country == 'America' or country == 'america':
                list_st_states = list(range(self.V%50))
            self.singleton_logic(list_st_states)

            if not domain_dictionary[v+1]:
                return False

            for c in domain_dictionary[v+1]:
                if self.is_safe(v, colour, c) == True:
                    colour[v] = c 
                    neighbors = self.get_neighbors(v) 

                    for neighbor in neighbors:
                        if c in domain_dictionary[neighbor+1]:
                            domain_dictionary[neighbor+1].remove(c)

                    next_state = self.get_next_state(domain_dictionary, colour)
                    if next_state != -1:
                        if self.graph_colour(m, colour, next_state) == True:
                            return True
                    else:
                        if self.graph_colour(m, colour, v+1) == True:
                            return True

                    for neighbor in neighbors:
                        a = neighbor+1
                        if c not in domain_dictionary[a]:
                            domain_dictionary[a].append(c)
                            domain_dictionary[a].sort()
                    colour[v] = 0
                noofbacktracks+=1
        except Exception as e:
            print("something wrong", e)

    def graph_colouring(self, m): 
        colour = [0] * self.V 
        if self.graph_colour(m, colour, 0) == False: 
            return False

        # Print the solution 
        print("Graph is Consistent:")
        for idx, val in enumerate(colour): 
             result_dictionary[state_dictionary[str(idx+1)]] = color_dictionary[str(val)]
        return True





def createdomain_dictionary(n):
	for key, value in enumerate(state_dictionary):
		integers = list(range(1,n))
		domain_dictionary[key+1] = integers


if country == 'America':
    change_position = [[9, 10, 24, 42], [], [5, 6, 28, 31, 44], [18, 24, 25, 36, 42, 43], [3, 28, 37],
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
        for j in change_position[i]:
            individual[j - 1] = 1
        positions.append(individual)

    states = states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware',
                       'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky',
                       'Louisiana', 'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi',
                       'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico',
                       'New York', 'North Carolina', 'North Dakota', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania',
                       'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont',
                       'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming']
    i = 1
    state_dictionary = {}
    for state in states:
        state_dictionary[str(i)] = state
        i = i + 1
    color_dictionary = {"1": "red", "2": "green", "3": "yellow", "4": "blue"}
    result_dictionary = {}
    domain_dictionary = {}

    createdomain_dictionary(5)

    # Driver Code
    g = Singletonheuristic(50)

    g.graph = positions

    m = 4  ## chromataic number

if country == 'Australia':
    change_position = [[3, 4, 6], [3, 4, 7], [1, 2, 4], [1, 2, 3, 4, 6, 7], [], [1, 4], [2, 4]]
    positions = []
    for i in range(0, 7):
        individual = []
        for j in range(0, 7):
            individual.append(0)
        for j in change_position[i]:
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

    createdomain_dictionary(4)

    # Driver Code
    g = Singletonheuristic(7)  # number of states

    g.graph = positions

    m = 3  ## chromataic number
g.graph_colouring(m) 

end_time = datetime.now()

Time_difference = end_time - start_time
print(f'THE TOTAL EXECUTION TIME -->',str(Time_difference.total_seconds()))
print(f'Number of backtracks : {noofbacktracks}')
for key, value in result_dictionary.items():
	print(f'{key} --> {value}')
