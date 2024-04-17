import re

txt = "109\n"
print(txt)
x = re.search("\d\n", txt)

print(x.start())

