#!/usr/bin/env bash

TOKEN=ab58c2d1-035e-4df7-b89e-ab51bcfb4f14

echo Retrieve user details

curl -v -H "Authorization: Bearer $TOKEN" localhost:9090/uaa/user

echo Call restricted service

curl -v -H "Authorization: Bearer $TOKEN" localhost:9094/resource/myservice

