#!/usr/bin/env bash

TOKEN=2219199c-966e-4466-8b7e-12bb9038c9bb
curl -H "Authorization: Bearer $TOKEN" localhost:8080/uaa/user
curl -H "Authorization: Bearer $TOKEN" localhost:8082/resource/myservice
