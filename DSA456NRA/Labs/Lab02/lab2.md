# Lab 2


### function 1:

Analyze the following function with respect to number

def function1(number): 
	total = 0 # 1 ops
	for i in range(number): # n+1 
		x = i + 1 # 2 ops
		total += x * x      #3 ops 
	return total            # 1 ops
	
	T(n) = 1 + n + 1 + 6
	= 1n + 7, Therefore T(n) is O(n) 
	because:
 		T(n)≤cf(n) for all n≥n0 
		1n+7 ≤ 10n 
 ​​




### function 2:

Analyze the following function with respect to number

def function2(number):
	return (number * (number + 1) * (2 * number + 1)) // 6 # 7 ops 

T(n) = 7, therefore T(n) is O(1) as it constant 
	because:
	T(n)≤cf(n) for all n≥n0 
	7 ≤ 20
   

### function 3:

Analyze the following with respect to the length of the list.  Note that the function call len() which returns the length of the list is constant (O(1)) with respect to the length of the list.
```python

def function3(list):
	n = len(list) # 2 ops
	for i in range(n - 1): #  n + 2  
		for j in range(n - 1 - i): # 0.5n² + 2.5n - 3
			if list[j] > list[j+1]: 7 * (0.5n² - 0.5n ) 
				tmp = list[j]
				list[j] = list[j+1]
				list[j + 1] = tmp
 ​​
 
 i 	|# time both loop runs  					j | # ops for j
 0 	| (n-1)								0 | 3 + (n-1) = n + 2
 1	| (n-2) 							1 | 3 + (n-2) = n + 1
 2 	| (n-3)								2 | 3 + (n-3) = n 
 |	    |								|   |	  |	   |
 |	    |								|   |	  |	   |
n-2	    1								n-2 3 +	  1	   4

1 + 2 + 3 + .....+n(n-1)/2						4 + 5 +....+(n+2)

									(n+2)(n+3) - 6 			n(n-1)/2
									//2				= 0.5n² - 0.5n 
									= 0.5n² + 2.5n - 3

	T(n) = 2 + (n+1) + 0.5n²+ 2.5n – 3 + 3.5n² + 3.5n
		= (n+1)-1 4n² + 6n
		= 7n + 4n² therefore T(n) is O(n²)
		 because:
			T(n)≤cf(n) for all n≥n0 
			7n + 4n² ≤ 20n²
		 

```
### function 4:

Analyze the following function with respect to number

def function4(number):
	total = 1 # 1 ops
	for i in range(1, number): # 1 + n
		total *= i + 1  # 3 ops
	return total # 1 
	
	T(n)= 1 + 1 + n + 3 +1
	= 1n + 6, Therefore T(n) is O(n) 
	because:
		T(n)≤cf(n) for all n≥n0 
		1n+6 ≤ 10n 


## In class portion


### Group members
List the members of your group member below:

	* Lebna Noori
	* ...
 


1. What do the functions do?
   All three functions are comparing and looking for a pair that sums up to the key we are looking for. All three funcs are using different algorithms, which effects its time complexity. 


3. **WITHOUT DOING AN ANALYSIS** (so by gut feeling alone), rank your 3 functions individually... does your group's rankings match?
   Without doing an analysis we though the first one would be the fastest because of for loop and doesn't have a lot of loops and or do anything complex. The code is very short and straight forward. 

5. Run lab2timing.py.  Does the timing validate your ranking?  Any surprises?
   We did run the timing file and we were shocked. The function we thought would take the longest, takes the shortest. which is the binary search because it divides the list into two and it's time complexity is nlog which is way better then our guess which was the function and that function time complexity is O(n²)


7. Analyze at least one of the 3 functions ( one(), two() or three() ):
```python




 	def one(mylist, key):
		total = 0 # 1 ops
		for i in range(len(mylist)): 			# 2 + n
			for j in range(i+1,len(mylist)): 	# 0.5n² + 2.5n - 3
				if i != j:			# 6 * 0.5n² - 0.5n 
					if mylist[i] + mylist[j] == key:
						total += 1
		return total					# 1 ops 

  	i 	|# time both loop runs  					j | # ops for j
	 0 	| (n-1)								0 | 3 + (n-1) = n + 2
	 1	| (n-2) 							1 | 3 + (n-2) = n + 1
	 2 	| (n-3)								2 | 3 + (n-3) = n 
	 |	   |								|	|  |	|
	 |	   |								|	|  |	|
	n-1	   1								n-1	3 +1	4
	
	1 + 2 + 3 + .....+n(n-1)/2						4 + 5 +....+(n+2)
										(n+2)(n+3) - 6 			n(n-1)/2
										//2				= 0.5n² - 0.5n 
										= 0.5n² + 2.5n - 3




	T(n) = 1 + (n+2) + 0.5n²+ 2.5n – 3 + 3n² + 3n
		 = (n+1)-1 3.5n² + 5.5n
		 = 6.5n + 4n² therefore T(n) is O(n²)
		 because:
			T(n)≤cf(n) for all n≥n0 
			6.5n + 4n² ≤ 20n²

```

6. Run lab2timing.py with increasing values of the amount of data (increase by 1000 each time).  Is there a pattern? (Note: ensure that you are using the same "machine" as you change the data size.  Ideally a local computer to avoid inconsistencies).  Does the timing reflect what you expect based on your analysis?
7. We changed the data size by 2 or 3000 every time and looked at the time run of the functions. The run time did change but it the ranking function did not. The slowest one remained slow and the faster function remained faster


