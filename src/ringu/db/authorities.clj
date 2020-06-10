(ns ringu.db.authorities
  (:require
   [hugsql.core :as hugsql]))

(hugsql/def-db-fns "ringu/db/sql/authorities.sql")

