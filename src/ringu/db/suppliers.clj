(ns ringu.db.suppliers
  (:require
   [hugsql.core :as hugsql]))

(hugsql/def-db-fns "ringu/db/sql/suppliers.sql")

(defn insert-get-id [db data]
  ((keyword "last_insert_rowid()")
    (insert-supplier-return-key db data)))

(defn search-by-name-like [db name page]
  (suppliers-by-name-like db {
                              :name-like (str "%" name "%")
                              :limit 10
                              :offset (* 10 (dec page))}))

(defn total-pages-by-name-like [db name]
  (-> (count-suppliers-by-name-like db {:name-like (str "%" name "%")})
      ((keyword "count(*)"))
      (quot 10)
      (inc)
   ))
  


(comment
  (require '[ringu.db.core :as db])
  (all-suppliers (db/db-connection) )
  (suppliers-by-name-like (db/db-connection) 
                          {:name-like "%%" })
  (search-by-name-like (db/db-connection) "1" 1)
  (total-pages-by-name-like (db/db-connection) "1" )
  )