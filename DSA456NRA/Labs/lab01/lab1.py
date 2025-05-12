# Write the code for your lab 1 here.  Read the specs carefully.  
# Function name must be exactly as provided.  
# Names of variables and parameters can be whatever you wish it to be
#
# To test, run the following command :
#     python test_lab1.py
#
# Author: Lebna Noori
# Student Number: 157672205


def wins_rock_scissors_paper(test1=None, test2=None):
    winner = True
    choice = ["Paper", "Rock", "Scissors"]
    
    print(f"player 1: {test1}")
    print(f"player 2: {test2}")
    
    test1 = test1.capitalize()
    test2 = test2.capitalize()

    if test1 == test2:
        winner = False 
    
    # Logic to determine the winner
    if (test1 == "Rock" and test2 == "Scissors") or (test1 == "Scissors" and test2 == "Paper") or (test1 == "Paper" and test2 == "Rock"):
        winner = True 
    else:
        winner = False  
    return winner
    
def factorial(n):
    result = 1
    for i in range(1, n +1):
        result = result * i
    return result
	
def fibonacci(n):
    if n <= 0:
        return 0
    elif n == 1:
        return 1
    num1 = 0 #f(0)
    num2 = 1 #f(1)
    for i in range(2, n + 1): #  i = 2 
        result = num1 + num2 # result = 0 + 1 so result = 1
        num1 = num2 # 0 = 1 so num1 becomes 1
        num2 = result # num2 is already 1 for second iteration 1 + 1 = 2
    return num2 # therefore fibonacci(3) = 2

def sum_to_goal(my_list, goal):
    for i in range(len(my_list)):
        for j in range(i+1,len(my_list)):
            if my_list[i] + my_list[j] == goal:
                return my_list[i] * my_list[j]
    return 0
    

class UpCounter:
    def __init__(self, step_size=1):
        self.counter = 0
        self.step_size = step_size
        
    def count(self):
        return self.counter
    
    def update(self):
        self.counter += self.step_size

class DownCounter(UpCounter):
    def __init__(self, step_size=1):
        super().__init__(step_size)  # Use parent class constructor to set up step_size and counter
    
    def count(self):  # Change method name to avoid conflict with the attribute
        return self.counter
    
    def update(self):
        self.counter -= self.step_size