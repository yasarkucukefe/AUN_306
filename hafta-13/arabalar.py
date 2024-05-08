from urllib.request import urlopen
from bs4 import BeautifulSoup
import pandas as pd

url = "https://www.arabam.com/ikinci-el/otomobil/bmw"
html = urlopen(url)

print(type(html))

soup = BeautifulSoup(html, 'lxml')
print(type(soup))

rows = soup.find_all('tr')
print(rows[:10])

data_all = []
for row in rows:
    tds = row.find_all("td")
    data = BeautifulSoup(str(tds), "lxml").get_text()
    data_all.append(data)

df = pd.DataFrame(data_all)

print(df)