from flask import Flask, render_template, request

app = Flask(__name__)

@app.route('/user/authenticate', methods=['POST', 'GET'])
def home():
	return render_template("index.html")

if __name__ == '__main__':
   app.run(port=5002, debug = True)
   