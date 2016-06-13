#!/usr/bin/env bash

echo -e "line1 f2 f3nline2 f4 f5nline3 f6 f7" | awk '{print "Line No:"NR", No of fields:"NF, "$0="$0, "$1="$1, "$2="$2, "$3="$3}'

#print $NF可以打印出一行中的最后一个字段，使用$(NF-1)则是打印倒数第二个字段
echo -e "line1 f2 f3n line2 f4 f5" | awk '{print $NF}'

#BEGIN END use demo
seq 5 | awk 'BEGIN{ sum=0; print "总和：" } { print $1"+"; sum+=$1 } END{ print "等于"; print sum }'

#借助-v选项，可以将外部值（并非来自stdin）传递给awk
VAR=10000
echo | awk -v VARIABLE=$VAR '{ print VARIABLE }'

var1="aaa"
var2="bbb"
echo | awk '{ print v1,v2 }' v1=$var1 v2=$var2

#printf use demo
awk 'BEGIN{n1=124.113;n2=-1.224;n3=1.2345; printf("%.2f,%.2u,%.2g,%X,%on\r\n",n1,n2,n3,n1,n1);}'

#time use demo
awk 'BEGIN{tstamp=mktime("2001 01 01 12 12 12");tstamp2=systime();print strftime("%c || %c",tstamp,tstamp2);}'

#if use demo
awk 'BEGIN{ test=100; if(test>90){ print "very good"; } else if(test>60){ print "good"; } else{ print "no pass"; } }'

#while use demo
awk 'BEGIN{ test=10; total=0; while(i<=test){ total+=i; i++; } print total; }'

#for use demo
awk 'BEGIN{ total=0; for(i=0;i<=10;i++){ total+=i; } print total; }'

#sed awk xargs use demo
cat 1.txt | awk '{print $(NF)}' | xargs -I % echo %
ls -l | sed '1d' | awk '{print $(NF)}' | xargs -I % ls %



