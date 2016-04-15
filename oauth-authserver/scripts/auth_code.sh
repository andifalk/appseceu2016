#!/usr/bin/env bash

curl -d "response_type=code&client_id=acme&redirect_uri=http://localhost" http://localhost:8080/uaa/oauth/authorize