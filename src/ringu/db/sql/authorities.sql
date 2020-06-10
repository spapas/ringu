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