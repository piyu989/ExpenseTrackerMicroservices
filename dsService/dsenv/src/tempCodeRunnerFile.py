@app.route('/api/v1/ds/message',methods =['POST'])
def handle_message():
    message = request.json.get('message')
    result = messageService.process_message(message)
    return result