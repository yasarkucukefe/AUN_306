
import re

# captions_text.vtt dosyasını okuyalım
dosya = "captions_text.vtt"

with open(dosya,'r') as file:
    lines = file.readlines()

print(lines)

pattern1 = r'\d+\n' # 101\n gibi satırlar için

metin = []
ilk = True
for line in lines:
    
    if ilk :
        ilk = False
        continue

    if "-->" in line:
        continue

    matches = re.findall(pattern1,line)
    if len(matches)>0 :
        continue

    if len(line.strip())>0:
        metin.append(line.strip())

yeni_metin = "".join(metin)

print(yeni_metin)

with open("transript.txt","w") as file:
    file.write(yeni_metin)