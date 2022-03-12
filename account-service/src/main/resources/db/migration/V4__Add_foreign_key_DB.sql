ALTER TABLE "account_role"
ADD FOREIGN KEY (role_id) REFERENCES role (id);

ALTER TABLE "account_role"
ADD FOREIGN KEY (account_id) REFERENCES account (id);