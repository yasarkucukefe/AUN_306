def function1():
    print("Function 1")

def function2(a, b):
    sonuc = a * b
    print(sonuc)

def function3(a, b, c):
    sonuc = a + b + c
    return sonuc


def main():
    function1() # prints: Function 1

    function2(2,3) # prints: 6

    sonuc = function3(3, 4, 5) # returns: 12
    print(sonuc) # prints 12

# print(f"{__name__=}")

if __name__ == "__main__":
    main()