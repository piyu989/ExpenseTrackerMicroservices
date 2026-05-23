from dotenv import load_dotenv
from langchain_core.prompts import ChatPromptTemplate,MessagesPlaceholder
from langchain_mistralai import ChatMistralAI
from service.expense import Expense

import os

class LLMService:
    def __init__(self):
        load_dotenv()
        self.prompt = ChatPromptTemplate.from_messages(
            [
                (
                    "system",
                    "You are an expert extraction algorithm. "
                    "Only extract relevant information from the text. "
                    "If you know the value of an attribute asked to extract, "
                    "return null for the attribute value"
                ),
                (
                    "human","{text}"
                )
            ]
        )
        self.apiKey = os.getenv('OPENAI_API_KEY')
        self.llm = ChatMistralAI(api_key=self.apiKey,model="mistral-large-latest")
        print("llm model define")
        self.runnable = self.prompt | self.llm.with_structured_output(schema=Expense)
        print("after runnable")

    def runLLM(self,message):
        print("invoking message")
        return self.runnable.invoke({"text":message})