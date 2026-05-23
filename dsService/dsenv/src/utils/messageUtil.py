import re

class MessageUtil:
    def isBankSms(self,message):
        words_to_search = ['bank','spent','card']
        pattern = r'\b(?:' + '|'.join(words_to_search) + r')\b'
        return bool(re.search(pattern, message, flags=re.IGNORECASE))
    