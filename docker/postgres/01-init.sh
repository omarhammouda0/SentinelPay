#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 \
  --username "$POSTGRES_USER" \
  --dbname "postgres" \
  --set=transaction_migrator_user="$TRANSACTION_MIGRATOR_USER" \
  --set=transaction_migrator_password="$TRANSACTION_MIGRATOR_PASSWORD" \
  --set=transaction_app_user="$TRANSACTION_APP_USER" \
  --set=transaction_app_password="$TRANSACTION_APP_PASSWORD" \
  --set=risk_migrator_user="$RISK_MIGRATOR_USER" \
  --set=risk_migrator_password="$RISK_MIGRATOR_PASSWORD" \
  --set=risk_app_user="$RISK_APP_USER" \
  --set=risk_app_password="$RISK_APP_PASSWORD" \
  --set=audit_migrator_user="$AUDIT_MIGRATOR_USER" \
  --set=audit_migrator_password="$AUDIT_MIGRATOR_PASSWORD" \
  --set=audit_app_user="$AUDIT_APP_USER" \
  --set=audit_app_password="$AUDIT_APP_PASSWORD" \
  --set=notification_migrator_user="$NOTIFICATION_MIGRATOR_USER" \
  --set=notification_migrator_password="$NOTIFICATION_MIGRATOR_PASSWORD" \
  --set=notification_app_user="$NOTIFICATION_APP_USER" \
  --set=notification_app_password="$NOTIFICATION_APP_PASSWORD" <<-EOSQL

CREATE ROLE :"transaction_migrator_user"
    WITH LOGIN PASSWORD :'transaction_migrator_password';

CREATE ROLE :"transaction_app_user"
    WITH LOGIN PASSWORD :'transaction_app_password';

CREATE ROLE :"risk_migrator_user"
    WITH LOGIN PASSWORD :'risk_migrator_password';

CREATE ROLE :"risk_app_user"
    WITH LOGIN PASSWORD :'risk_app_password';

CREATE ROLE :"audit_migrator_user"
    WITH LOGIN PASSWORD :'audit_migrator_password';

CREATE ROLE :"audit_app_user"
    WITH LOGIN PASSWORD :'audit_app_password';

CREATE ROLE :"notification_migrator_user"
    WITH LOGIN PASSWORD :'notification_migrator_password';

CREATE ROLE :"notification_app_user"
    WITH LOGIN PASSWORD :'notification_app_password';

CREATE DATABASE transaction_db
    OWNER :"transaction_migrator_user";

CREATE DATABASE risk_db
    OWNER :"risk_migrator_user";

CREATE DATABASE audit_db
    OWNER :"audit_migrator_user";

CREATE DATABASE notification_db
    OWNER :"notification_migrator_user";

REVOKE CONNECT ON DATABASE transaction_db FROM PUBLIC;

GRANT CONNECT ON DATABASE transaction_db
    TO :"transaction_app_user";

REVOKE CONNECT ON DATABASE risk_db FROM PUBLIC;
GRANT CONNECT ON DATABASE risk_db
    TO :"risk_app_user";

REVOKE CONNECT ON DATABASE audit_db FROM PUBLIC;
GRANT CONNECT ON DATABASE audit_db
    TO :"audit_app_user";

REVOKE CONNECT ON DATABASE notification_db FROM PUBLIC;
GRANT CONNECT ON DATABASE notification_db
    TO :"notification_app_user";

\connect transaction_db

REVOKE CREATE ON SCHEMA public FROM PUBLIC;

GRANT USAGE ON SCHEMA public
    TO :"transaction_app_user";

ALTER DEFAULT PRIVILEGES
    FOR ROLE :"transaction_migrator_user"
    IN SCHEMA public
    GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES
    TO :"transaction_app_user";

\connect risk_db

REVOKE CREATE ON SCHEMA public FROM PUBLIC;

GRANT USAGE ON SCHEMA public
    TO :"risk_app_user";

ALTER DEFAULT PRIVILEGES
    FOR ROLE :"risk_migrator_user"
    IN SCHEMA public
    GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES
    TO :"risk_app_user";


\connect audit_db

REVOKE CREATE ON SCHEMA public FROM PUBLIC;

GRANT USAGE ON SCHEMA public
    TO :"audit_app_user";

ALTER DEFAULT PRIVILEGES
    FOR ROLE :"audit_migrator_user"
    IN SCHEMA public
    GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES
    TO :"audit_app_user";


\connect notification_db

REVOKE CREATE ON SCHEMA public FROM PUBLIC;

GRANT USAGE ON SCHEMA public
    TO :"notification_app_user";

ALTER DEFAULT PRIVILEGES
    FOR ROLE :"notification_migrator_user"
    IN SCHEMA public
    GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES
    TO :"notification_app_user";

EOSQL