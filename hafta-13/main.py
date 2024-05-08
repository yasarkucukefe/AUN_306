# Web scraping (Python Beatiful Soup library ile)

# https://www.datacamp.com/tutorial/web-scraping-using-python

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

from urllib.request import urlopen
from bs4 import BeautifulSoup
print("Gerekli bütün kütüphaneler yüklendi.");

url = "http://www.hubertiming.com/results/2017GPTR10K"
html = urlopen(url)

soup = BeautifulSoup(html, 'lxml')
print(type(soup))

# Get the title
title = soup.title
print(title)

# bütün 'a' elementlerini bul (<a href='http://google.com'>Link</a>)
links = soup.find_all('a')
print(links)

for link in links:
    print(link.get("href"))

# Print the first 10 rows for sanity check
rows = soup.find_all('tr')
print(rows[:10])

for row in rows:
    tds = row.find_all("td")

print(type(tds)) 
data = BeautifulSoup(str(tds), "lxml").get_text()
print(data)

data_all = []
for row in rows:
    tds = row.find_all("td")
    data = BeautifulSoup(str(tds), "lxml").get_text()
    data_all.append(data)

print(data_all[:4])

df = pd.DataFrame(data_all)

print(df)