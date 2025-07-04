DROP DATABASE IF EXISTS Batch_25_06;
CREATE DATABASE Batch_25_06;
USE Batch_25_06;

SELECT * FROM BATCH_JOB_EXECUTION;
SELECT * FROM BATCH_JOB_EXECUTION_CONTEXT;
SELECT * FROM BATCH_JOB_EXECUTION_PARAMS;
SELECT * FROM BATCH_JOB_EXECUTION_SEQ;
SELECT * FROM BATCH_JOB_INSTANCE;
SELECT * FROM BATCH_JOB_SEQ;
SELECT * FROM BATCH_STEP_EXECUTION;
SELECT * FROM BATCH_STEP_EXECUTION_CONTEXT;
SELECT * FROM BATCH_STEP_EXECUTION_SEQ;

DROP DATABASE IF EXISTS Batch_25_06_backup;
CREATE DATABASE Batch_25_06_backup;
USE Batch_25_06_backup;

SHOW TABLES;