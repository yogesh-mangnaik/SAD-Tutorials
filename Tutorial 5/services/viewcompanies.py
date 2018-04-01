from flask import Flask, render_template, request
import pandas as pd

app = Flask(__name__)

@app.route('/user/viewcompanies')
def home():
	companies = viewcompanies()
	return companies	

def viewcompanies():
	data = pd.read_csv('companies.csv')
	return "companies"

if __name__ == '__main__':
   app.run(port=5004, debug = True)
