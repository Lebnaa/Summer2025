#
# Author: 
# Student Number:
#
# Place the code for your lab 2 here. Read the specs carefully.  
#
# To test, run the following command :
#     python test_lab2.py
#

# Write the following 3 functions recursively

# Recursive factorial function
def factorial(number):
    if number <= 1:
        return 1
    return number * factorial(number - 1)

# Recursive linear search
def linear_search(lst, key, index=0):
    if index >= len(lst):
        return -1
    if lst[index] == key:
        return index
    return linear_search(lst, key, index + 1)

# Recursive binary search (only works if the list is sorted)
def binary_search(lst, key, low=0, high=None):
    if high is None:
        high = len(lst) - 1

    if low > high:
        return -1 

    mid = (low + high) // 2

    if lst[mid] == key:
        return mid
    elif key < lst[mid]:
        return binary_search(lst, key, low, mid - 1)
    else:
        return binary_search(lst, key, mid + 1, high)
