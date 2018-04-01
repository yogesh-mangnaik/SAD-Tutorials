from flask import Flask, render_template, request

fields = {}

app = Flask(__name__)

@app.route('/', methods=['POST', 'GET'])
def home():
	return render_template("home.html")

@app.route('/login' methods=['POST', 'GET'])
def login():
	

if __name__ == '__main__':
   app.run(debug = True)

