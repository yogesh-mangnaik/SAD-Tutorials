from flask import Flask, render_template, request

app = Flask(__name__)

@app.route('/company/application')
def home():
	return render_template("companyapply.html")

@app.route('/company/applicationsubmit', methods=['POST'])
def add():
	#authenticate user
	name = request.form['name']
	date = request.form['date']
	criteria = request.form['criteria']
	package = request.form['package']
	branches = request.form['branches']
	number = request.form['number']
	addcompany(name, date, criteria, package, branches, number)
	return "Successfully Registered"

def addcompany(name, date, criteria, package, branches, number):
	fields = {}
	mydata = [[name, date, criteria, package, branches, number]]
	with open('companies.csv', 'a') as file:
		writer = csv.writer(file)
		writer.writerows(mydata)

if __name__ == '__main__':
   app.run(port=5003, debug = True)
