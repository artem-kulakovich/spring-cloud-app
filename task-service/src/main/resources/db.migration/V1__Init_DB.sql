CREATE TABLE public.task
(
    id bigserial NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(2048),
    estimated_time timestamp with time zone,
    remaining_time timestamp with time zone,
    spent_time timestamp with time zone,
    status_id integer,
    account_id bigint,
    comment_id integer,
    PRIMARY KEY (id)
);

CREATE TABLE public.status
(
    id serial NOT NULL,
    name character varying(50) NOT NULL,
    PRIMARY KEY (id)
);

