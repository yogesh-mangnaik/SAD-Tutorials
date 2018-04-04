from flask import Flask, render_template, request
import csv
import pandas as pd
import urllib
import json

app = Flask(__name__)

@app.route('/user/login')
def home():
	return render_template("studentlogin.html")

#working
@app.route('/user/authenticateuser', methods=['POST', 'GET'])
def authuser():
	#authenticate user
	name = request.form['username']
	p = request.form['password']
	if validate(name, p):
		return "Successful Login"
	return "Login Failed"
#working
def validate(name, password):
	students = pd.read_csv('students.csv')
	print(students)
	col = students.columns.values
	n = students[str(col[0])]
	p = students[str(col[1])]
	for i in range(len(students)):
		print(str(n[i]), str(name), str(p[i]), str(password))
		if(str(n[i]).replace(' ', '') == str(name).replace(' ', '') and str(p[i]).replace(' ', '') == str(password).replace(' ', '')):
			return True
	return False

@app.route('/user/viewcompanies', methods=['GET'])
def viewCompanies():
	response = urllib.request.urlopen('http://127.0.0.1:5003/company/list')
	data = json.load(response)   
	print(data)
	return data['0']

@app.route('/user/allstudents', methods=['GET'])
def allstudents():
	return "All"


if __name__ == '__main__':
   app.run(port=5002, debug = True)
