from flask import Flask, render_template, request, Response
import json
import csv
import pandas as pd

app = Flask(__name__)

@app.route('/company/application')
def home():
	return render_template("companyapply.html")

@app.route('/company/applicationsubmit', methods=['POST'])
def apply():
	#authenticate user
	name = request.form['name']
	date = request.form['date']
	criteria = request.form['criteria']
	package = request.form['package']
	branches = request.form['branches']
	print(name)
	mydata = [[name, date, criteria, package, branches]]
	with open('companies.csv', 'a') as file:
		writer = csv.writer(file)
		writer.writerows(mydata)
	return "Successfully Registered"

@app.route('/company/list', methods=['GET'])
def list():
    data = {}
    file = pd.read_csv('companies.csv')
    cols = file.columns.values
    for i in range(len(file)):
    	data[str(i)] = "Company name : " + file[cols[0]][i]
    	data[str(i)] += "Visiting Date : " + file[cols[1]][i]
    	data[str(i)] += "Criteria : " + file[cols[2]][i]
    	data[str(i)] += "Package : " + file[cols[3]][i]
    	data[str(i)] += "Branches Allowed : " + file[cols[4]][i]
    js = json.dumps(data)
    resp = Response(js, status=200, mimetype='application/json')
    resp.headers['Link'] = 'http://127.0.0.1:5003/company/list'
    return resp

@app.route('/company/studentlist')
def studentlist():
	response = urllib.request.urlopen('http://127.0.0.1:5003/company/list')
	data = json.load(response)   
	print(data)
	return "All Students"

def addcompany(name, date, criteria, package, branches, number):
	fields = {}
	mydata = [[name, date, criteria, package, branches, number]]
	with open('companies.csv', 'a') as file:
		writer = csv.writer(file)
		writer.writerows(mydata)

if __name__ == '__main__':
   app.run(port=5003, debug = True)
