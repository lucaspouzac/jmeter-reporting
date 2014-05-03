#!/bin/sh

. $(pwd)/localhost.properties

mongo $mongohost/jmeter-reporting -u$mongouser -p$mongopass --eval 'db.samples.drop()'
mongo $mongohost/jmeter-reporting -u$mongouser -p$mongopass --eval 'db.load_tests.drop()'

curl -X POST --form upload=@api-test-0.0.1.jtl http://$jmeterreportinghost/api/upload/api-test/0.0.1
curl -X POST --form upload=@api-test-0.1.0.jtl http://$jmeterreportinghost/api/upload/api-test/0.1.0
curl -X POST --form upload=@api-test-1.0.0#1.jtl http://$jmeterreportinghost/api/upload/api-test/1.0.0
curl -X POST --form upload=@api-test-1.0.0#2.jtl http://$jmeterreportinghost/api/upload/api-test/1.0.0
curl -X POST --form upload=@api-test-1.0.0#3.jtl http://$jmeterreportinghost/api/upload/api-test/1.0.0
curl -X POST --form upload=@api-test-1.0.0#4.jtl http://$jmeterreportinghost/api/upload/api-test/1.0.0

curl -X POST --form upload=@api-demo-1.0.0#1.jtl http://$jmeterreportinghost/api/upload/api-demo/1.0.0
curl -X POST --form upload=@api-demo-1.0.0#2.jtl http://$jmeterreportinghost/api/upload/api-demo/1.0.0

curl -X POST http://$jmeterreportinghost/api/reference/api-test/1.0.0/2/true
