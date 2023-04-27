# Question 188

What will this code print out?

```python3
def make_functions():
    flist = []

    for i in [1, 2, 3]:
        def print_i():
            print(i)
        flist.append(print_i)

    return flist

functions = make_functions()

for f in functions:
    f()
```

```
3
3
3
```

How can we make it print out what we apparently want?

```python
def make_functions():
    flist = []

    def create_print_i_function(i):
        def print_i():
            print(i)
        return print_i

    for i in [1, 2, 3]:
        flist.append(create_print_i_function(i))

    return flist

functions = make_functions()
for f in functions:
    f()
```

```
1
2
3
```

By calling `create_print_i_function(i)` with `i` as an argument, 
we create a new function that captures the current value of `i` and add it to the `flist`. 
This ensures that each `print_i` function in the list has its own unique value of i.