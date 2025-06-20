# def search_insert(nums, target):
#     left = 0 
#     right = len(nums) - 1
#     while left <= right:
#         mid = (left + right) // 2
#         if nums[mid] == target:
#             return mid
#         elif nums[mid] < target:
#             left = mid + 1
#         else:
#             right = mid - 1
#     return left

# # Example usage:
# nums = [1, 3, 5, 6]
# target = 4
# print(search_insert(nums, target))



# def count(nums):
#     if nums == []:
#         return  0 
#     try:
#         _ = nums[0] + 1
#         _ = nums[0] * 2
#         return 1 + count(nums[1:])
#     except:
#         return count(nums[1:])

# nums = [1, 2 ,90,5,"9", "10"]
# print(count(nums))


def linear_search(num, target):
    n = len(num) - 1
    for i in range(n):
        if num[i] == target:
            return 1 + linear_search(num[i:], target)
    return -1

nums = [1, 2, 3, 4, 5]
target = 3
print(linear_search(nums, target))