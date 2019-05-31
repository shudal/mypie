# -*- coding: utf-8 -*-

import requests
import json
import os
import configparser

class MyChatBot:
    def __init__(self, userId="perci"):
        self.conf = configparser.ConfigParser()
        self.conf.read(os.path.join(os.path.dirname(os.path.realpath(__file__)), 'config.ini'))
        self.TURING_KEY = self.conf.get("chatbot", "apiKey")
        self.URL = "http://openapi.tuling123.com/openapi/api/v2"
        self.HEADERS = {'Content-Type': 'application/json;charset=UTF-8'}
        self.data = {
            "reqType": 0,
            "perception": {
                "inputText": {
                    "text": ""
                },
                "selfInfo": {
                    "location": {
                        "city": "杭州",
                        "street": "网商路"
                    }
                }
            },
            "userInfo": {
                "apiKey": self.TURING_KEY,
                "userId": userId
            }
        }

    def getResponse(self, text):
        self.data["perception"]["inputText"]["text"] = text
        self.response = requests.request("post", self.URL, json=self.data, headers=self.HEADERS)
        self.response_dict = json.loads(self.response.text)

        self.result = self.response_dict["results"][0]["values"]["text"]
        return self.result
