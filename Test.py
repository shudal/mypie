# -*- coding: utf-8 -*-
import MyOnlineSpeaker
import MyChatBot

oriText = "赵陈杨"

chatBoy = MyChatBot.MyChatBot(userId="boy")
chatGirl = MyChatBot.MyChatBot(userId="girl")
myOnlineSpeaker = MyOnlineSpeaker.MyOnlineSpeaker()

per = 4
for i in range(0, 100):
    try:
        if i % 2 == 0:
            per = 3
            print("Boy:" + oriText)
        else:
            per = 4
            print("Girl:" + oriText)

        myOnlineSpeaker.say(oriText, per=per)

        if per == 3:
            oriText = chatGirl.getResponse(oriText)
        else:
            oriText = chatBoy.getResponse(oriText)
    except:
        print("出现了点错误")
