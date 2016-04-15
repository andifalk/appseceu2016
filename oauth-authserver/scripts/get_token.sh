#!/usr/bin/env bash

CODE=1234
curl acme:acmesecret@localhost:9999/uaa/oauth/token -d grant_type=authorization_code -d client_id=demo -d redirect_uri=http://localhost -d code=$CODE
