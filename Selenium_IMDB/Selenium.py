# -*- coding: utf-8 -*-
"""
Created on Mon Mar 20 09:36:14 2023

@author: Gabriel Ruiz
the website that was used: https://www.imdb.com/list/ls054383657/
My program clicks over 200 different links; note that half of them are just duplicates of the
same site. So its 100 unique sites but each has two different links that leads to it so its still all unique links
I used options and told the driver to implicitly wait. 
 resources used
 https://selenium-python.readthedocs.io/locating-elements.html
 stackoverflow in general https://stackoverflow.com/questions/17540971/how-to-use-selenium-with-python
"""

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.action_chains import ActionChains
import time
import json

def main():
    options = Options()

    options.add_argument("--disable-popup-blocking")
    driver = webdriver.Chrome(options=options)
    driver.maximize_window()
    driver.get("https://www.imdb.com/list/ls054383657/")



    # Find all the links
    main = driver.find_element(By.CSS_SELECTOR, ".lister-list")
    links = main.find_elements(By.CSS_SELECTOR, "a")

    # Click only the video games links in the top list
    for n, el in enumerate(links):
        
        main = driver.find_element(By.CSS_SELECTOR, ".lister-list")
        links = main.find_elements(By.CSS_SELECTOR, "a")
        
        try:
            if "title/" in links[n].get_attribute("href"):
                #action for clicking 
                action = ActionChains(driver)
                action.move_to_element(links[n]).click().perform()
               
                driver.implicitly_wait(1)
                
                with open("seleniumMeta.jl", "a+") as f:
                    json.dump(parse_item(driver), f)
                    f.write("\n")
                    
                    f.close()
                driver.back()
                time.sleep(1)
        except Exception as e:
            print(f"Error clicking {links[n].text}: {e}")
            
    # Close the browser
    driver.quit()



def parse_item(driver):
    #try and except in case nothing is found which it can happen in this case run it again
    try:
        num_critics = driver.find_element(By.XPATH, '//*[@id="__next"]/main/div/section[1]/section/div[3]/section/section/div[3]/div[2]/div[2]/ul/li[2]/a/span/span[1]').text
    except: num_critics = "0"    
    try:
        age_rating = driver.find_element(By.XPATH, '/html/body/div[2]/main/div/section[1]/section/div[3]/section/section/div[2]/div[1]/ul/li[3]/a').text
    except:
        age_rating = "No Rating"
    try:
        release_year = driver.find_element(By.XPATH, '//*[@id="__next"]/main/div/section[1]/section/div[3]/section/section/div[2]/div[1]/ul/li[2]/a').text
        
    except:
        release_year = "no Data"
    d = {
            "Url": driver.current_url,
            "Page_Title": driver.title,
            "Number of Critics": num_critics,
            "Age Rating": age_rating,
            "Release Year": release_year,
        }
    return d

    
if __name__ == '__main__':
    main()