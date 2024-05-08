from urllib.request import urlopen
from bs4 import BeautifulSoup

url = "https://bbc.com/news"
html = urlopen(url)

print(type(html))

soup = BeautifulSoup(html, 'lxml')

haber = soup.find("h2", class_='sc-4fedabc7-3 dsoipF')
print(haber.get_text())