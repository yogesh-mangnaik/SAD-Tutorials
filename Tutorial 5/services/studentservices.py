from flask import Flask, render_template, request
import csv
import pandas as pd
import urllib
import json

app = Flask(__name__)

@app.route('/user/login')
def home():
	return render_template("studentlogin.html")

@app.route('/user/authenticateuser', methods=['POST', 'GET'])
def authuser():
	#authenticate user
	name = request.form['username']
	p = request.form['password']
	if validate(name, p):
		return "Successful Login"
	return "Login Failed"

@app.route('/user/viewcompanies', methods=['GET'])
def viewCompanies():
	response = urllib.request.urlopen('http://127.0.0.1:5003/company/list')
	data = json.load(response)   
	print(data)
	return data['hello']

@app.route('/user/allstudents', methods=['GET'])
def allstudents():
	return "All"

def validate(name, password):
	students = pd.read_csv('students.csv')
	print(students)
	n = students['username']
	p = students['pass']
	for i in range(len(students)):
		if(name == password & password == p):
			return True
	return False

if __name__ == '__main__':
   app.run(port=5002, debug = True)
