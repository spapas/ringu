
-- :name all-suppliers :? :*
-- :doc Get all suppliers
select * from suppliers order by id
--~ (when (:limit params) "limit :limit offset :offset")

-- :name supplier-by-id :? :1
-- :doc Get supplier by id
select * from supplier
where id = :id

-- :name suppliers-by-name-like :? :*
-- :doc Get suppliers by name like, :name-like should include % wildcards
select * from suppliers
where lower(name) like lower(:name-like) order by id 
--~ (when (:limit params) "limit :limit offset :offset")

-- :name count-suppliers-by-name-like :? :1
-- :doc Get suppliers by name like, :name-like should include % wildcards
select count(*) from suppliers
where lower(name) like lower(:name-like) 

-- :name insert-supplier :! :n
-- :doc Insert a single supplier returning affected row count
insert into suppliers (name)
values (:name)

-- :name insert-supplier-return-key :insert :raw
insert into suppliers (name)
values (:name)