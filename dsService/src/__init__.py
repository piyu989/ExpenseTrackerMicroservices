from flask import Flask 
from flask import request,jsonify
from service.messageService import MessageSerivice
from kafka import KafkaProducer
import json

app = Flask(__name__)
app.config.from_pyfile('config.py') 

messageService = MessageSerivice()
producer = KafkaProducer(bootstrap_servers=['localhost:9092'],
                         value_serializer=lambda v: json.dumps(v).encode('utf-8'))

@app.route('/api/v1/ds/message',methods =['POST'])
def handle_message():
    message = request.json.get('message')
    print(message)
    result = messageService.process_message(message)
    # serialized_result = result.json()

    producer.send('expense_json',result.dict())
    return jsonify(result.dict())

@app.route('/',methods = ['GET'])
def get_message():
    return "jai shree ram"

if __name__ == "__main__":
    app.run(host="localhost",port=8000,debug=True)