#!/usr/bin/python

import urllib.request
import sys
import random

print("Started.")

while True:
    cnt = 0
    for i in range(0, 1000):
        try:
            with urllib.request.urlopen("http://127.0.0.1:8080/pingperf/ping/pathparam/Mozilla%2F4.0+%28compatible%3B+MSIE+8.0%3B+AOL+9.6%3B+AOLBuild+4340.17%3B+Windows+NT+5.1%3B+Trident%2F4.0%3B+.NET+CLR+1.1.4322%3B+.NET+CLR+2.0.50727%3B+.NET+CLR+3.0.4506.2152%3B+.NET+CLR+3.5.30729%29") as response:
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
