CREATE TABLE public.accounts (
                                 id UUID PRIMARY KEY,
                                 status VARCHAR(30) NOT NULL
                                     CHECK (status IN ('ACTIVE', 'SUSPENDED', 'CLOSED')),
                                 created_at TIMESTAMPTZ NOT NULL
);