# -*- coding: utf-8 -*-

import os
import configparser
from aip import AipSpeech

conf = configparser.ConfigParser()
conf.read(os.path.join(os.path.dirname(os.path.realpath(__file__)), 'config.ini'))

APP_ID = conf.get('BaiduAip', 'appid')
API_KEY = conf.get('BaiduAip', 'apikey')
SECRET_KEY = conf.get('BaiduAip', 'secretkey')

class MyOnlineSpeaker:
    def __init__(self):
         self.client = AipSpeech(APP_ID, API_KEY, SECRET_KEY)
         self.curPath = os.path.dirname(os.path.realpath(__file__)) + "/"
    def say(self, text="", speed=4, vol=5, per=4, pit=5):
        result = self.client.synthesis(text, 'zh', 1, {
            # 语速
            'spd': speed,

            # 音量
            'vol': vol,

            # 0->女, 1->男, 2->男, 3->情感男, 4->情感女
            'per': per,

            # 声调
            'pit': pit,
        })

        if not isinstance(result, dict):
            with open(self.curPath + 'audios/audio.mp3', 'wb') as f:
                f.write(result)
        os.system("mpg123 " + self.curPath + "audios/audio.mp3")

