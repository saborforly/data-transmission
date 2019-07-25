#coding=utf-8
#For geting Pre-transmitted data, We try to like sentry to read psql
#author:liuyan
# coding:utf-8
import urllib
import psycopg2
import sys
import re
import os
import time

class sql():
    def __init__(self):
        self.connection=None
        self.cursor=None
    
    def start_connection(self):
        self.connection = psycopg2.connect(host='localhost',user='postgres',password='',db='sentry',charset='utf8mb4',cursorclass=psycopg2.cursors.DictCursor)
        self.cursor = connection.cursor()
        
    def stop_connect(self):
        self.cursor.close()
        self.connection.close()
    
    
    def select_seenitem(self,lastwatchedkey_value):
        # 使用 execute()  方法执行 SQL 查询 
        sql = "select item from seenitem where lastwatched_lastwatchedkey >" + lastwatchedkey_value
        self.cursor.execute(sql)
        results = list(self.cursor.fetchall())
        
        line = results.__str__()
        line = line.decode('unicode_escape')        
        return results
    
    
        
    def select_lastwatchedkey(self,lastdatetime_value):
        #'2010-01-01'
        sql = "select lastwatchedkey from lastwatched where lastdatetime > "+ lastdatetime_value
        self.cursor.execute(sql)
        results = list(self.cursor.fetchall())
        #解决编码问题
        line = results.__str__()
        lastwatchedkey_value = line.decode('unicode_escape')
        return lastwatchedkey_value
    
    def add_data(data,iid):
        # 使用 execute()  方法执行 SQL 查询 
        sql = "INSERT INTO fuck_ill(age,sex,time,ill_detail,doctor_answer,title,ill_id) VALUES('"+data['age']+"','"+data['sex']+"','"+data['time']+"','"+data['info']+"','"+data['answer']+"','"+data['title']+"','"+iid+"')"
        self.cur    sor.execute(sql)
        
    def update_data(para,value,key):
        try:
            # 使用 execute()  方法执行 SQL 更新
            sql = "UPDATE EMPLOYEE SET AGE = AGE + 1 WHERE SEX = '%c'" % ('M')
            #sql = "INSERT INTO fuck_ill(age,sex,time,ill_detail,doctor_answer,title,ill_id) VALUES('"+data['age']+"','"+data['sex']+"','"+data['time']+"','"+data['info']+"','"+data['answer']+"','"+data['title']+"','"+iid+"')"
            self.cursor.execute(sql)
            self.connection.commit()
        except:
            self.connection.rollback()
        
    def delete_data(key,value):
        try:
            # 使用 execute()  方法执行 SQL 删除
            sql = "DELETE FROM %s WHERE key > %s"%(key),% (value)
            self.cursor.execute(sql)
            self.connection.commit()
        except:
            connection.rollback()

def save_data(data,filename):
    with open(filename+".json", 'w', encoding="utf-8") as f:
        f.write(json.dumps(data, indent=2, ensure_ascii=False))



