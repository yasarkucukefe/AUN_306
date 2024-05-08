from urllib.request import urlopen
from bs4 import BeautifulSoup
import pandas as pd

url = "https://www.arabam.com/ikinci-el/otomobil/bmw"
html = urlopen(url)

print(type(html))

soup = BeautifulSoup(html, 'lxml')
print(type(soup))

resimler = soup.find_all("img", class_='listing-image')

for resim in resimler:
    print(resim['src'])