from flask import Flask, render_template, request
import pandas as pd

app = Flask(__name__)

@app.route('/tpo/viewstudents')
def home():
	students = viewcompanies()
	return students	

def viewcompanies():
	data = pd.read_csv('students.csv')
	return "Students"

if __name__ == '__main__':
   app.run(port=5005, debug = True)
