curl -v -u admin:admin -X \
  POST 'http://test.dans.knaw.nl:8081/service/rest/v1/components?repository=maven-releases' \
  -F maven2.groupId=nl.knaw.dans.easy \
  -F maven2.artifactId=test \
  -F maven2.version=1.0.0 \
  -F maven2.asset1=@/Users/janm/Downloads/Amazon_S3.pdf \
  -F maven2.asset1.extension=pdf
