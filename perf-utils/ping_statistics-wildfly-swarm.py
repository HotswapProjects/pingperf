#!/usr/bin/python

import urllib.request
import sys
import os
import json
from time import sleep

try:
    with urllib.request.urlopen("http://127.0.0.1:8080/statistic") as response:
        js = json.loads(response.read())
        print("Total count :" + str(js['total_requests']))
        print("Total secs  :" + str(js['total_nanos']/1000000000))
        print("Avg req/s :" + str(js['avg_throughput']))
except urllib.error.HTTPError as e:
    pass
