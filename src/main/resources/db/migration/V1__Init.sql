-- Table: public.account

-- DROP TABLE IF EXISTS public.account;

CREATE TABLE IF NOT EXISTS public.account
(
    id bigint NOT NULL,
    created_on timestamp(6) without time zone,
    external_id character varying(255) COLLATE pg_catalog."default",
    last_login_date timestamp(6) without time zone,
    CONSTRAINT account_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.account
    OWNER to monitor;

-- Table: public.blood_pressure

-- DROP TABLE IF EXISTS public.blood_pressure;

CREATE TABLE IF NOT EXISTS public.blood_pressure
(
    id bigint NOT NULL,
    created_on timestamp(6) without time zone,
    diastolic integer,
    heartrate integer,
    systolic integer,
    account_id bigint,
    CONSTRAINT blood_pressure_pkey PRIMARY KEY (id),
    CONSTRAINT fkrcsw395vrcuaxaffd9sl0kl6a FOREIGN KEY (account_id)
        REFERENCES public.account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.blood_pressure
    OWNER to monitor;

alter table if exists account alter column created_on set data type timestamp(6);
alter table if exists account alter column last_login_date set data type timestamp(6);
alter table if exists blood_pressure alter column created_on set data type timestamp(6);
create sequence account_SEQ start with 1 increment by 50;
create sequence blood_pressure_SEQ start with 1 increment by 50;