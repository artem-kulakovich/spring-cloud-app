CREATE TABLE public.account
(
    id bigserial NOT NULL,
    first_name character varying(50),
    last_name character varying(50),
    user_name character varying(100) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(1024) NOT NULL,
    PRIMARY KEY (id)
);

