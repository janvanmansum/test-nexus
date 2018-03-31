#!/usr/bin/env bash

groovy src/main/groovy/wrapInJson.groovy -f src/main/groovy/createRepos.groovy > src/main/ansible/roles/nexus/files/create-repos.json
