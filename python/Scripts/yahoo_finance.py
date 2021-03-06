from lxml import html  
import requests
from time import sleep
import json
import argparse
from collections import OrderedDict
from time import sleep

def parse(ticker):
	url = "http://finance.yahoo.com/quote/%s?p=%s"%(ticker,ticker)
	response = requests.get(url, verify=False)
	print ("Parsing %s"%(url))
	sleep(4)
	parser = html.fromstring(response.text)
	summary_table = parser.xpath('//div[contains(@data-test,"summary-table")]//tr')
	summary_data = OrderedDict()
	try:
		for table_data in summary_table:
			raw_table_key = table_data.xpath('.//td[contains(@class,"C(black)")]//text()')
			raw_table_value = table_data.xpath('.//td[contains(@class,"Ta(end)")]//text()')
			table_key = ''.join(raw_table_key).strip()
			table_value = ''.join(raw_table_value).strip()
			summary_data.update({table_key:table_value})
		summary_data.update({'ticker': ticker})
		return summary_data
	except:
		print ("Failed to parse json response")
		return {"error":"Failed to parse json response"}
		
if __name__=="__main__":
	argparser = argparse.ArgumentParser()
	argparser.add_argument('ticker',help = '')
	args = argparser.parse_args()
	ticker = args.ticker
	print ("Fetching data for %s"%(ticker))
	scraped_data = parse(ticker)
	print ("Writing data to output file")
	with open('%s-summary.json'%(ticker),'w') as fp:
		json.dump(scraped_data,fp,indent = 4)