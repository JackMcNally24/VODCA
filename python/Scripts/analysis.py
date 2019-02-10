from lxml import html
import requests
from time import sleep
import json
import argparse
from collections import OrderedDict
from time import sleep

def parse(ticker):
    # Code to get the stock price
    url = "http://finance.yahoo.com/quote/%s?p=%s" % (ticker, ticker)
    response = requests.get(url, verify=False)
    print("Parsing %s" % (url))
    parser = html.fromstring(response.text)
    summary_table = parser.xpath('//div[contains(@data-test,"summary-table")]//tr')
    summary_data = OrderedDict()
    try:
        for table_data in summary_table:
            raw_table_key = table_data.xpath('.//td[contains(@class,"C(black)")]//text()')
            raw_table_value = table_data.xpath('.//td[contains(@class,"Ta(end)")]//text()')
            table_key = ''.join(raw_table_key).strip()
            table_value = ''.join(raw_table_value).strip()
            summary_data.update({table_key: table_value})
    except:
        print("Failed to parse json response")
        return {"error": "Failed to parse json response"}
    # code to get the current avg estimate, next avg estimate, and next 5 years growth estimate
    url = "http://finance.yahoo.com/quote/%s/analysis?p=%s" % (ticker, ticker)
    response = requests.get(url, verify=False)
    print("Parsing %s" % (url))
    parser = html.fromstring(response.text)
    summary_table = parser.xpath('//section[contains(@data-test,"qsp-analyst")]//tr')
    # summary_data = OrderedDict()
    try:
        for table_data in summary_table:
            raw_table_key = table_data.xpath('.//td[contains(@class,"Py(10px)")]//text()')
            raw_table_value = table_data.xpath('.//td[contains(@class,"Ta(end)")]//text()')
            table_key = ' '.join(raw_table_key).strip()
            table_value = ' '.join(raw_table_value).strip()
            summary_data.update({table_key: table_value})
        summary_data.update({'ticker': ticker})
        return summary_data
    except:
        print("Failed to parse json response")
        return {"error": "Failed to parse json response"}

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

def parseAll():
    arr = []
    for item in list(parseWiki().items())[1:]:
        arr.append(parse(item[1]))
    return arr


if __name__ == "__main__":
    """argparser = argparse.ArgumentParser()
    argparser.add_argument('ticker', help='')
    args = argparser.parse_args()
    ticker = args.ticker
    print ("Fetching data for %s" % (ticker))"""
    scraped_data = parseAll()
    print ("Writing data to output file")
    with open('%s-analysis.json', 'w') as fp:
        json.dump(scraped_data, fp, indent=4)
    f = open("done.txt", "w+")
    for i in range(10):
        f.write("This is line %d\r\n" % (i + 1))
    f.close()