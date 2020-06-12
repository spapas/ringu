-- :name all-authorities :? :*
-- :doc Get all authorities
select * from authorities

-- :name authority-by-id :? :1
-- :doc Get character by id
select * from authorities
where id = :id

-- :name characters-by-name-like :?
-- :doc Get authorities by name like, :name-like should include % wildcards
select * from authorities
where name like :name-like

-- :name insert-authority :! :n
-- :doc Insert a single character returning affected row count
insert into authorities (name)
values (:name)

-- :name insert-authority-return-key :insert :raw
insert into authorities (name)
values (:name)