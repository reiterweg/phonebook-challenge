CREATE SCHEMA phonebook;

CREATE TABLE phonebook.contacts (
    id SERIAL NOT NULL,
    first_name VARCHAR(80) NOT NULL,
    last_name VARCHAR(80) NOT NULL,
    phone VARCHAR(80) NOT NULL,
    CONSTRAINT pk_contacts PRIMARY KEY (id)
);

INSERT INTO phonebook.contacts (id, first_name, last_name, phone) VALUES (NEXTVAL('phonebook.contacts_id_seq'), 'Arun', 'Kart', '415-8679089');
INSERT INTO phonebook.contacts (id, first_name, last_name, phone) VALUES (NEXTVAL('phonebook.contacts_id_seq'), 'Juan', 'Torus', '301-2390930');
INSERT INTO phonebook.contacts (id, first_name, last_name, phone) VALUES (NEXTVAL('phonebook.contacts_id_seq'), 'Nolux', 'Fernandez', '310-2930291');
