from lxml import html
import requests
from time import sleep
import json
import argparse
from collections import OrderedDict
from time import sleep

def parseWiki():
    # code to get all stocks from wiki page
    url = "https://en.wikipedia.org/wiki/List_of_S%26P_500_companies"
    response = requests.get(url, verify=False)
    print("Parsing")
    parser = html.fromstring(response.text)
    summary_table = parser.xpath('//table[contains(@id,"constituents")]//tr')
    summary_data = OrderedDict()
    n = 0
    try:
        for table_data in summary_table:
            raw_table_value = table_data.xpath('.//a[contains(@class,"external text")]//text()')
            table_value = ' '.join(raw_table_value).strip()
            x = table_value.split(" reports")
            # table_value = table_value[0:5]
            summary_data.update({n: x[0]})
            n = n + 1
        return summary_data
    except:
        print("Failed to parse json response")
        return {"error": "Failed to parse json response"}


if __name__ == "__main__":
    print ("Fetching data")
    scraped_data = parseWiki()
    print ("Writing data to output file")
    with open('allStocks.json', 'w') as fp:
        json.dump(scraped_data, fp, indent=4)