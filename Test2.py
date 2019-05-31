# -*- coding: utf-8 -*-

import MyRecorder
import MyListener

myRecorder = MyRecorder.MyRecorder()
myListener = MyListener.MyListener()

myRecorder.record()

t = myListener.listen()
print(t)
