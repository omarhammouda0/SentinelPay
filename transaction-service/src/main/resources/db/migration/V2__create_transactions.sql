CREATE TABLE public.transactions
(
    id         UUID PRIMARY KEY,
    account_id UUID           NOT NULL REFERENCES public.accounts (id) ON DELETE CASCADE,
    amount     NUMERIC(20, 2) NOT NULL,
    currency   VARCHAR(3)     NOT NULL,
    status     VARCHAR(30)    NOT NULL
        CHECK (status IN ('PENDING_RISK', 'APPROVED', 'REJECTED', 'CANCELLED')),
    created_at TIMESTAMPTZ    NOT NULL
);


