#!/usr/bin/env bash
curl client:secret@localhost:8080/oauth/token -d grant_type=password -d username=user -d password=secret
