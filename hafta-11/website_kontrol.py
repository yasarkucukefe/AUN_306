import requests
from fake_useragent import UserAgent ## pip install fake_useragent

def check_website(website: str, user_agent: str):
    print(user_agent)
    # Web sitesi çalışıyor mu?
    try:
        code: int = requests.get(website, headers={'User-Agent': user_agent}).status_code
        print(code)
    except Exception:
        print(f"{website} şu anda erişilemiyor.")

def get_user_agent() -> str:
    ua = UserAgent()
    return ua.chrome

def main():
    web_site: str = "https://plantdx.com"
    user_agent: str = get_user_agent()
    check_website(web_site, user_agent)


if __name__ == "__main__":
    main()