from flask import Flask, render_template, request
import pandas as pd
import requests

app = Flask(__name__)

@app.route('/tpo/getschedule')
def getschedule():
	return "Schedule"

@app.route('/tpo/viewstudents')
def viewstudents():
	response = urllib.request.urlopen('http://127.0.0.1:5003/students/list')
	data = json.load(response)   
	print(data)
	return 'All Students'

@app.route('/tpo/viewcompanies')
def allcompanies():
	return viewcompanies()

def viewcompanies():
	response = urllib.request.urlopen('http://127.0.0.1:5003/company/list')
	data = json.load(response)   
	print(data)
	return 'All companies'

def addcompany(name, date, criteria, package, branches, number):
	return "Request received"

if __name__ == '__main__':
   app.run(port=5004, debug = True)
