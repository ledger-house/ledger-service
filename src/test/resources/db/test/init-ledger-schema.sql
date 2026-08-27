CREATE SCHEMA ledger AUTHORIZATION ledger_app;

ALTER ROLE ledger_app
SET
  search_path = ledger;
