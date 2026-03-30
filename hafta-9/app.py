# pip install flask
from flask import Flask, render_template, jsonify, request


app = Flask(__name__)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/login', methods=['GET'])
def login():
    return render_template('login.html')


@app.route('/login', methods=['POST'])
def login_post():
    username = request.form['username']
    password = request.form['password']
    if username == 'admin' and password == 'password':
        return "Giriş başarılı!"
    else:
        return "Giriş başarısız!"


@app.route('/about')
def about():
    return "Bu bir Flask uygulamasıdır."

@app.route('/data')
def data():
    return jsonify({'name': 'Yaşar', 'age': 55})

if __name__ == '__main__':
    app.run(debug=True) # port=5000, http://127.0.0.1:5000