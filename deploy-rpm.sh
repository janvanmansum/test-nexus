#!/usr/bin/env bash

curl -v --user 'admin:admin' \
 --upload-file $1 \
 "http://test.dans.knaw.nl:8081/repository/test/$(basename $1)"