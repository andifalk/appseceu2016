#!/usr/bin/env bash

echo get token

curl demo:demo_secret@localhost:9090/uaa/oauth/token -d grant_type=password -d username=user -d password=secret

echo .
echo

