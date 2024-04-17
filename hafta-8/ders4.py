liste = [0,1,2,3,4,5]

ilk_defa = True

for item in liste:
    if ilk_defa:
        ilk_defa = False
        continue
    print(item)