import random

def get_pivot(l, r):
    return r

def swap(arr, i, j):
    tmp = arr[i]
    arr[i] = arr[j]
    arr[j] = tmp

def partition(arr, p, l, r):
    swap(arr, p, r)
    k = l
    for j in range(l, r):
        if arr[j] < arr[r]:
            swap(arr, j, k)
            k += 1
    swap(arr, k, r)
    return k

def __quicksort(arr, l, r):
    if r - l < 1:
        return arr

    p = get_pivot(l, r)

    k = partition(arr, p, l, r)
    __quicksort(arr, l, k - 1)
    __quicksort(arr, k + 1, r)

def quicksort(arr):
    return __quicksort(arr, 0, len(arr) - 1)


arr = [6, 4, 10, 1, 0, 4, 7, -2, 18]

quicksort(arr)

assert((arr) == sorted(arr), "array isn't sorted correctly")