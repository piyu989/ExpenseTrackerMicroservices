from utils.messageUtil import MessageUtil
from service.llmService import LLMService 

class MessageSerivice:
    def __init__(self):
        self.messageUtil = MessageUtil()
        self.llmService = LLMService()

    def process_message(self,message):
        if(self.messageUtil.isBankSms(message)):
            print("bank sms")
            return self.llmService.runLLM(message)
        else:
            return None