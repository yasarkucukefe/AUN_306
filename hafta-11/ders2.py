# type hinting: Data type'ların belirtilmesi

def func1_no_type_hinting(a, b):
    sonuc = a * b
    return sonuc

def funct_with_type_inting(a: int, b: int) -> int:
    sonuc: int = a * b
    return sonuc

print(func1_no_type_hinting(3,4)) # prints 12

print(funct_with_type_inting(3, 4)) # prints 12

def func_no_return(a: int, b: int) -> None:
    print(a,b)

func_no_return(3,4)

def func_returns_list(a: int, b: int, c: int) -> list[int]:
    return [a,b,c]

def func_returns_list_with_mixed_data_types(a: int, b: int, c: int) -> list[any]:
    return [a,str(b),c]

def func_returns_list_with_int_but_some_includes_strings(a: int, b: int, c: int) -> list[int]:
    return [a,str(b),str(c)]

print(func_returns_list(4,5,6))

print(func_returns_list_with_mixed_data_types(4,5,6))

print(func_returns_list_with_int_but_some_includes_strings(4,5,6))