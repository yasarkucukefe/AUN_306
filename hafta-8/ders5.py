# Problem: 1 metinde yer alan bütün karakter küçük harf mi?

metin = "Yasar Kucukefe" # False: Büyük harfler var

metin = "yasar kucukefe" # True: Bütün harfler küçük

def kontrol_kucuk_harf_mi(metin):
    if metin == metin.lower():
        return True
    else:
        return False

def kontrol_buyuk_harf_mi(metin):
    if metin == metin.upper():
        return True
    else:
        return False

isim = "ahmet"
print(isim.lower())

if kontrol_kucuk_harf_mi(isim):
    print("Hepsi küçük harfli")
else:
    print("Büyük harfler var.")

