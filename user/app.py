from flask import Flask

app = Flask(__name__)

@app.route('/')
def Index():
    return 'wenas uwu'

@app.route('/users')
def get_all_users():
    return 'all users'

@app.route('/users/<id>')
def get_user_by_id(id):
    return 'user by id: ' + id

@app.route('/login')
def login():
    return 'login user'

@app.route('/register')
def register():
    return 'register user'

@app.route('/users/<id>')
def update_user(id):
    return 'update user by id: ' + id

@app.route('/users/<id>')
def delete_user(id):
    return 'delete user by id: ' + id

if __name__ == '__main__':
    app.run(port = 3000, debug = True)
