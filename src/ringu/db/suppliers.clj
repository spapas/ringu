(ns ringu.db.suppliers
  (:require
   [hugsql.core :as hugsql]))

(hugsql/def-db-fns "ringu/db/sql/suppliers.sql")

(defn insert-get-id [db data]
  ((keyword "last_insert_rowid()")
    (insert-supplier-return-key db data)))

(defn search-by-name-like [db name]
  (suppliers-by-name-like db {:name-like (str "%" name "%")}))