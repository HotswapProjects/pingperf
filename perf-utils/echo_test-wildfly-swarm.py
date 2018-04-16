#!/usr/bin/python

import urllib.request
import sys
import random

print("Started.")

while True:
    cnt = 0
    for i in range(0, 1000):
        try:
            with urllib.request.urlopen("http://127.0.0.1:8080/ping/simple") as response:
                content = response.read()
                cnt = cnt + 1
                sys.stdout.write('\r')
                pos = int(cnt * 100 / 1000)
                sys.stdout.write("[%-100s] %d (%d%%)" % ('=' * pos, cnt, pos))
                sys.stdout.flush()
        except urllib.error.HTTPError as e:
            pass
        except urllib.error.URLError as e:
            pass
        except ConnectionResetError as e:
            pass
