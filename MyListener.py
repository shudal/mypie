# -*- coding: utf-8 -*-

import os
import configparser
from aip import AipSpeech

conf = configparser.ConfigParser()
conf.read(os.path.join(os.path.dirname(os.path.realpath(__file__)), 'config.ini'))

APP_ID = conf.get('BaiduAip', 'appid')
API_KEY = conf.get('BaiduAip', 'apikey')
SECRET_KEY = conf.get('BaiduAip', 'secretkey')

class MyListener:
    def __init__(self):
        self.client = AipSpeech(APP_ID, API_KEY, SECRET_KEY)
    def listen(self):
        with open('audios/recording.wav', 'rb') as f:
            self.audio_data = f.read()

        self.result = self.client.asr(self.audio_data, 'wav', 16000, {
        'dev_pid': 1536,
        })

        self.result_text = self.result["result"][0]

        return self.result_text
