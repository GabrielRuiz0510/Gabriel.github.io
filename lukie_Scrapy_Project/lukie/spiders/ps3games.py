import scrapy

# Gabriel Ruiz
#https://www.lukiegames.com/playstation-3-games#/perpage:64
#view-source:https://www.lukiegames.com/playstation-3-games#/perpage:64

class Ps3gamesSpider(scrapy.Spider):
    name = 'ps3games'
    allowed_domains = ['www.lukiegames.com']

    def parse(self, response):
        for a in response.css('div.productBlockContainer div.products-info a'):
            href = "https://www.lukiegames.com/" 
            href= href + a.attrib['href']
            yield scrapy.Request(href, self.parse_item)
            
            
    def start_requests(self):
       start_urls = ['https://www.lukiegames.com/playstation-3-games#/perpage:64', 'https://www.lukiegames.com/playstation-3-games?page=2#/perpage:64']
       for url in start_urls:
           yield scrapy.Request(url = url, callback = self.parse)
            
    def parse_item(self, response):
        d = {
            #10
               'url': response.url,
               "title": response.selector.xpath("/html/body/div[1]/div[2]/div[5]/div[1]/div/div/section/form/div[4]/h1/text()").get(),
                "price": response.selector.xpath('/html/body/div[1]/div[2]/div[5]/div[1]/div/div/section/form/div[4]/div[4]/div[4]/span/text()').get(),
                "upc": response.selector.xpath("/html/body/div[1]/div[2]/div[5]/div[1]/div/div/section/form/div[7]/div/div[4]/div[2]/div/span/text()").get(),
                "retail price": response.selector.xpath('/html/body/div[1]/div[2]/div[5]/div[1]/div/div/section/form/div[4]/div[4]/div[2]/s/text()').get(),
                "availability": response.selector.xpath("/html/body/div[1]/div[2]/div[5]/div[1]/div/div/section/form/div[4]/div[9]/div/div[1]/span/text()").get(),
                "included": response.selector.xpath("/html/body/div[1]/div[2]/div[5]/div[1]/div/div/section/form/div[4]/div[3]/div/div/div[3]/div/div[1]/span/text()").get(),
                "lukie points" : response.selector.xpath('/html/body/div[1]/div[2]/div[5]/div[1]/div/div/section/form/div[4]/div[13]/div[3]/text()').get(),
                "# of reviews": response.selector.xpath("/html/body/div[1]/div[2]/div[5]/div[1]/div/div/section/form/div[4]/div[2]/a[1]/div/text()").get(),
                "description": response.selector.xpath("/html/body/div[1]/div[2]/div[5]/div[1]/div/div/section/form/div[7]/div/div[1]/div[2]/text()").get()
                
        }       
        yield d
        
