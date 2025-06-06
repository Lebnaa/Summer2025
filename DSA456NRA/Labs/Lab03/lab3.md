# Analysis and Reflection for Lab 3

## function 1:

Analyze the following function with respect to number

```python
def function1(value, number):
	if (number == 0):
		return 1
	elif (number == 1):
		return value
	else:
		return value * function1(value, number-1)
```

T(n) = 		if number = 1 OR number = 0……T(0) or T(1)  = 3  
		Otherwise: T(n)  = 5 + T(n-1)				#replacing n with n-1’s
			         T(n- 1) = 5 + T(n – 1 – 1) 
			          T(n-2) = 5 + 5 + T(n-3)
			         T(n-3) = 5 + 5 + 5 + T(n-4)   
				Continue utill reach T(1) 		#now seeing pattern 
			        T(n) = 5k + T(n-k)				n-k = 1 , k = n - 1
			        T(n) = 5(n-1) + T(1)			T(1)  = 3
			        T(n) =  5n – 5 + 3
			       T(n) = 5n -2
Therefore T(n) = O(n) 


## function 2:

Analyze function2 with respect to the length of the mystring.  Hint, you will need to set up two mathematical functions for operator counting.  one for function2 and the other for recursive_function2

```python

def recursive_function2(mystring,a, b):
	if(a >= b ):
		return True
	else:
		if(mystring[a] != mystring[b]):
			return False
		else:
			return recursive_function2(mystring,a+1,b-1)

def function2(mystring):
	return recursive_function2(mystring, 0,len(mystring)-1)

```


		if a = 0 OR a = 1, T(1) and T(0) = 2
T(n) = 		otherwise T(n) = 5 + T(n-2)
			       T(n-2) = 5 + 5 + T(n-2-2)
			        T(n-4) = 5+5+5+5+T(n-6)
				Continue untill reach T(1) 			#pattern found 
			        T(n) = 5k + T(n-2k)				n-2k = 1, k = n-1/2
			         T(n) =  5(n-1) / 2 + T(1)			T(1) = 2
			          T(n) = 2.5n – 2.5 + 2
			          T(n) = 2.5n – 0.5
Therefore T(n) = O(n)


### function 3 (optional challenge):

Analyze the following function with respect to number


```python
def function3(value, number):
	if (number == 0):
		return 1
	elif (number == 1):
		return value
	else:
		half = number // 2
		result = function3(value, half)
		if (number % 2 == 0):
			return result * result
		else:
			return value * result * result

```

## Part C reflection

Answer the following questions

1. Describe how to a approach writing recursive functions, what steps do you take?
   The first step for me is to find the base case of the problem, which is the simplest version of it. After identifying the base case, I find its time complexity to understand how the solution would work as the input size increases. Once I know the time complexity for the base case, I can break the problem down into smaller parts. This helps me identify patterns and relationships that will help me to a complete solution. Finally, I will analyze each step to solve for the worst-case scenario. This means figuring out how the soluiton behaves in the most challenging situations. By following these steps, I can create a solution that works well for both typical and edge cases.

3. Describe the process of analyzing recursive functions.  How does it differ from from analyzing non-recursive functions?  How is it the same?
   Analyzing recursive functions is different from looking at other types of functions. Recursive functions call themselves repeatedly until they reach a base case, which is the simplest part of the problem and stops the recursion. This process creates a stack of function calls, so understanding how this stack works is important and also very confusing for analyzing the function's behavior. To analyze a recursive function, I first need to identify the pattern of its calls. This pattern helps me see how the function breaks down the problem into smaller parts, which will leads to a solution. For me, finding this pattern is a key step in understanding the function.
Once I spot the pattern, I can create a equation that tells me the relationship between the input size and the number of recursive calls. This equation is useful for figuering out how the function will perform. By using these steps, I can evaluate how the recursive function behaves as the input size increases. Overall, analyzing recursive functions involves recognizing patterns and using mathematical reasoning to fully undrestand how they work.

