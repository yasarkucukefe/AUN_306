import requests
import csv
from http import HTTPStatus
from fake_useragent import UserAgent ## pip install fake_useragent

def oku_websites(csv_dosya: str)-> list[str]:
    websites = []
    with open(csv_dosya, 'r') as dosya:
        reader = csv.reader(dosya)
        for row in reader:
            websites.append(row[1])

    return websites

def get_status_description(status_code: int) -> str:
    for value in HTTPStatus:
        if value == status_code:
            description: str = f"({value} {value.name}) {value.description}"
            return description
        
    return '(???) Bilinmeyen status kod.'

def check_website(website: str, user_agent: str):
    website = "https://" + website
    # Web sitesi çalışıyor mu?
    try:
        code: int = requests.get(website, headers={'User-Agent': user_agent}).status_code
        print(website, get_status_description(code))
        #print(f"{website} => {code}")
    except Exception:
        print(f"{website} şu anda erişilemiyor.")

def get_user_agent() -> str:
    ua = UserAgent()
    return ua.chrome

def main():
    web_sites: list[str] = oku_websites("websites.csv")
    user_agent = get_user_agent()
    for website in web_sites:
        check_website(website,user_agent)


if __name__ == "__main__":
    main()