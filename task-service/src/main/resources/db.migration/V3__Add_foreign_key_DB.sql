ALTER TABLE "task"
ADD FOREIGN KEY (status_id) REFERENCES "status" (id);

ALTER TABLE "task"
ADD FOREIGN KEY (comment_id) REFERENCES "comment" (id);