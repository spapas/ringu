(ns ringu.db.core
  (:require [clojure.java.jdbc :as j])
  )

(def pg-db {:dbtype "postgresql"
            :dbname "phxcrd_dev"
            :host "localhost"
            :user "postgres"
            :password ""
            })