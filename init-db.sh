#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 -d keycloak -U "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE api;
    CREATE DATABASE kong;
EOSQL