from flask import Flask 
from flask import request,jsonify
from service.messageService import MessageSerivice

app = Flask(__name__)
app.config.from_pyfile('config.py') 

messageService = MessageSerivice()

@app.route('/api/v1/ds/message',methods =['POST'])
def handle_message():
    message = request.json.get('message')
    print(message)
    result = messageService.process_message(message)
    return jsonify(result)

@app.route('/',methods = ['GET'])
def get_message():
    return "jai shree ram"

if __name__ == "__main__":
    app.run(host="localhost",port=8000,debug=True)