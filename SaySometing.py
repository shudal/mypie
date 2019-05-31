# -*- coding: utf-8 -*-
import MyOnlineSpeaker
import MyChatBot
import sys
import os
import configparser
import pymysql


try:
    # 连接数据库
    cur_path=os.path.dirname(os.path.realpath(__file__))
    config_path=os.path.join(cur_path,'config/mysql.conf')
    print(cur_path)
    conf = configparser.ConfigParser()
    conf.read(config_path)
    mysqlConf = {
        'host'      : conf.get('database', 'host'),
        'port'      : int(conf.get('database', 'port')),
        'user'      : conf.get('database', 'user'),
        'password'  : conf.get('database', 'password'),
        'dbName'    : conf.get('database', 'database')
    }

    db = pymysql.connect(host = mysqlConf['host'], port= mysqlConf['port'], user = mysqlConf['user'], password = mysqlConf['password'], db = mysqlConf['dbName'], charset = 'utf8mb4')
    cursor = db.cursor()

    sayingId = sys.argv[1]

    sql_get_saying = "select * from saying where id='" + sayingId + "'"
    cursor.execute(sql_get_saying)
    saying = cursor.fetchone()

    print(saying)

    _content = saying[1]
    _speed   = saying[4]
    _vol     = saying[5]
    _per     = saying[6]
    _pit     = saying[7]

    myOnlineSpeaker = MyOnlineSpeaker.MyOnlineSpeaker()
    myOnlineSpeaker.say(text=_content, speed=_speed, vol=_vol, per=_per, pit=_pit)

    sql_save_status = "update saying set status='1' where id='" + sayingId +"'"
    cursor.execute(sql_save_status)
    db.commit()

    cursor.close
    db.close
except:
    print(sys.exc_info()[0])
    raise

