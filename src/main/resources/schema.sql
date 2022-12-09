CREATE TABLE IF NOT EXISTS appointment (
  id bigserial PRIMARY KEY NOT NULL,
  date date NOT NULL,
  time time NOT NULL,
  canceled boolean NOT NULL DEFAULT false,
  cancellation_reason text,
  patient_name varchar(100)
);