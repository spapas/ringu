-- :name all-suppliers :? :*
-- :doc Get all suppliers
select * from suppliers

-- :name supplier-by-id :? :1
-- :doc Get supplier by id
select * from supplier
where id = :id

-- :name suppliers-by-name-like :?
-- :doc Get suppliers by name like, :name-like should include % wildcards
select * from suppliers
where name like :name-like

-- :name insert-supplier :! :n
-- :doc Insert a single supplier returning affected row count
insert into suppliers (name)
values (:name)

-- :name insert-supplier-return-key :insert :raw
insert into suppliers (name)
values (:name)