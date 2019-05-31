# -*- coding: utf-8 -*-

import speech_recognition as sr

class MyRecorder:
    def __init__(self, rate=16000):
        self.r = sr.Recognizer()
        self.rate = rate
    def record(self):
        with sr.Microphone(sample_rate=self.rate) as source:
            print("please say something")
            self.audio = self.r.listen(source)
        with open("audios/recording.wav", "wb") as f:
            f.write(self.audio.get_wav_data())
