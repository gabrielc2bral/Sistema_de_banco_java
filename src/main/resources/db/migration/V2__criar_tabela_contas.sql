CREATE TABLE contas(
    id SERIAL PRIMARY KEY,
    saldo DECIMAL(15, 2) DEFAULT 0.00,
    titular_id INTEGER NOT NULL,
    CONSTRAINT fk_titular FOREIGN KEY (titular_id) REFERENCES titulares (id)

);