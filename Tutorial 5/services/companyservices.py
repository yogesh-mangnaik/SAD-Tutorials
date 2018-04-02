from flask import Flask, render_template, request, Response
import json

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
	number = request.form['number']
	addcompany(name, date, criteria, package, branches, number)
	mydata = [[name, date, criteria, package, branches, number]]
	r = requests.post("http://127.0.0.1:5004/tpo/addcompany", data={'name' : "h"})
	print(r.status_code, r.reason)
	return "Successfully Registered"

@app.route('/company/list', methods=['GET'])
def list():
    js = json.dumps({
        'hello'  : 'world',
        'number' : 3
    })
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
